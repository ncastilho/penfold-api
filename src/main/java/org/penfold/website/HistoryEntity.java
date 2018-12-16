package org.penfold.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Document("history")
public class HistoryEntity {
    @Id
    private String id;
    private String contactId;
    private String messageId;
    private String mobile;
    private String content;
    private String scheduledTime;
    private String messageSid;
    private List<EventLog> events;

    @Data
    @AllArgsConstructor
    public static class EventLog {
        private MessageState messageState;
        private LocalDateTime timestamp;

        public static EventLog create(MessageState messageState) {
            return new EventLog(messageState, LocalDateTime.now());
        }
    }

    public MessageState getState() {
        return getLastEventLog().getMessageState();
    }

    public LocalDate getDate() {
        return getLastEventLog().getTimestamp().toLocalDate();
    }

    public LocalTime getTime() {
        return getLastEventLog().getTimestamp().toLocalTime();
    }

    private EventLog getLastEventLog() {
        return events.stream()
                .max(Comparator.comparing(EventLog::getTimestamp))
                .orElse(EventLog.create(MessageState.INITIAL));
    }

    public void setState(MessageState messageState) {
        MessageState newState = getState().transitionTo(messageState);
        addEventLog(EventLog.create(newState));
    }

    private void addEventLog(EventLog eventLog) {
        events.add(eventLog);
    }


}
