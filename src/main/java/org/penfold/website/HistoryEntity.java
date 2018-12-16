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
        private MessageStatus messageStatus;
        private LocalDateTime timestamp;

        public static EventLog create(MessageStatus messageStatus) {
            return new EventLog(messageStatus, LocalDateTime.now());
        }
    }

    public MessageStatus getStatus() {
        return getLastEventLog().getMessageStatus();
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
                .orElse(EventLog.create(MessageStatus.INITIAL));
    }

    public void setStatus(MessageStatus messageStatus) {
        MessageStatus newStatus = getStatus().dispatch(messageStatus);
        addEventLog(EventLog.create(newStatus));
    }

    private void addEventLog(EventLog eventLog) {
        events.add(eventLog);
    }


}
