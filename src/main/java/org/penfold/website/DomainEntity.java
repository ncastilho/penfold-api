package org.penfold.website;

import lombok.Data;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Data
public class DomainEntity {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private long version;

    protected DomainEntity() {
    }
}