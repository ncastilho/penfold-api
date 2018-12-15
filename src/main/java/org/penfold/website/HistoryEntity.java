package org.penfold.website;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Document
public class HistoryEntity {
    public enum Status {
        SENT, RECEIVED, FAILED
    }

    @Id
    private String id;
    private String contactId;
    private String mobile;
    private String content;
    private String scheduledTime;
    private List<HistoryEvent> events = new ArrayList<>();

    @Data
    @AllArgsConstructor
    private class HistoryEvent {
        private Status status;
        private LocalDateTime timestamp;
    }

    public void setSentStatus() {
        if(getStatus() == Status.SENT || getStatus() == Status.RECEIVED) {
            throw new IllegalStateException();
        }

        setStatus(Status.SENT);
    }

    public void setReceivedStatus() {
        if(getStatus() == Status.RECEIVED) {
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
                .orElseThrow(() -> new IllegalStateException());
    }

    private void setStatus(Status status) {
        events.add(new HistoryEvent(status, LocalDateTime.now()));
    }
}
