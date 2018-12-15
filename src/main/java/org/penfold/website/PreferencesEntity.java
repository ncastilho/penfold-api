package org.penfold.website;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class PreferencesEntity {
    @Id
    private String id;
    private String contactId;
    private boolean mobileVerified;
    private boolean smsEnabled;
}
