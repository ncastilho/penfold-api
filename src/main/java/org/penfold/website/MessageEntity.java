package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("messages")
public class MessageEntity extends DomainEntity {

    private String contactId;
    private String content;
    private String scheduledTime;
}
