package org.penfold.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class ContactEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String mobile;
}
