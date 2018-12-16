package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("messages")
public class MessageEntity {
    @Id
    private String id;
    private String contactId;
    private String content;
    private String scheduledTime;
}
