package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("preferences")
public class PreferencesEntity extends DomainEntity {

    @Indexed(unique = true)
    private String contactId;
    private boolean mobileVerified;
    private boolean smsEnabled;
}
