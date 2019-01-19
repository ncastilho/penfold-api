package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("history")
public class HistoryEntity extends DomainEntity {

    private String contactId;
    private String messageId;
    private String mobile;
    private String content;
    private String scheduledTime;
    private String messageSid;
}
