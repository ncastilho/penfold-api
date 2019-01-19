package org.penfold.website;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Document("history")
public class HistoryEntity extends DomainEntity {

    private String contactId;
    private String messageId;
    private String mobile;
    private String content;
    private String scheduledTime;
    @Indexed(unique = true)
    private String messageSid;
//    private List<EventLog> events;
//
//    @Data
//    @AllArgsConstructor
//    public static class EventLog {
//        private State state;
//        private String message;
//        private LocalDateTime timestamp;
//
//        public static EventLog create(State state, String message) {
//            return new EventLog(state, message, LocalDateTime.now());
//        }
//    }
//
//    public State getState() {
//        return getLastEventLog().getState();
//    }
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//    public LocalDate getDate() {
//        return getLastEventLog().getTimestamp().toLocalDate();
//    }
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
//    public LocalTime getTime() {
//        return getLastEventLog().getTimestamp().toLocalTime();
//    }
//
//    private EventLog getLastEventLog() {
//        return events.stream()
//                .max(Comparator.comparing(EventLog::getTimestamp))
//                .orElse(EventLog.create(State.INITIAL, null));
//    }
//
//    public void transitionTo(State state, String message) {
//        System.out.println(state);
//        System.out.println(message);
//
//        State newState = getState().validateState(state);
//        System.out.println(newState);
//        addEventLog(EventLog.create(newState, message));
//    }
//
//    private void addEventLog(EventLog eventLog) {
//        events.add(eventLog);
//    }
}
