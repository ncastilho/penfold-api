package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("history-event")
public class HistoryEventEntity extends DomainEntity {

    private String historyId;
    private String messageSid;
    private String messageStatus;
    private String message;

}
