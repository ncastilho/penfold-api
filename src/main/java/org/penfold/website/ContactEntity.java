package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("contacts")
public class ContactEntity extends DomainEntity {
    private String name;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String mobile;
}
