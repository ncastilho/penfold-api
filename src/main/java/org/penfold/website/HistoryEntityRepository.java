package org.penfold.website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryEntityRepository extends PagingAndSortingRepository<HistoryEntity, String> {
    Iterable<HistoryEntity> findAllByContactId(String contactId);

    HistoryEntity findByMessageSid(String messageSid);
}
