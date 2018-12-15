package org.penfold.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Document
public class HistoryEntity {
    public enum Status {
        UNKNOWN, SENT, RECEIVED, FAILED
    }

    @Id
    private String id;
    private String contactId;
    private String mobile;
    private String content;
    private String scheduledTime;
    private String callbackToken;
    private List<HistoryEvent> events;

    @Data
    @AllArgsConstructor
    private class HistoryEvent {
        private Status status;
        private LocalDateTime timestamp;
    }

    public void setSentStatus() {
        if(getStatus() != Status.UNKNOWN && getStatus() != Status.FAILED) {
            throw new IllegalStateException();
        }

        setStatus(Status.SENT);
    }

    public void setReceivedStatus() {
        if(getStatus() == Status.UNKNOWN || getStatus() == Status.RECEIVED) {
            throw new IllegalStateException();
        }

        setStatus(Status.RECEIVED);
    }

    public void setFailedStatus() {
        if(getStatus() == Status.RECEIVED) {
            throw new IllegalStateException();
        }

        setStatus(Status.FAILED);
    }

    public Status getStatus() {
        return getLastHistoryEvent().getStatus();
    }

    public LocalDateTime getTimestamp() {
        return getLastHistoryEvent().getTimestamp();
    }

    private HistoryEvent getLastHistoryEvent() {
        return events.stream()
                .max(Comparator.comparing(HistoryEvent::getTimestamp))
                .orElse(createHistoryEvent(Status.UNKNOWN, LocalDateTime.now()));
    }

    private void setStatus(Status status) {
        events.add(createHistoryEvent(status, LocalDateTime.now()));
    }

    private HistoryEvent createHistoryEvent(Status status, LocalDateTime timestamp) {
        return new HistoryEvent(status, timestamp);
    }
}
