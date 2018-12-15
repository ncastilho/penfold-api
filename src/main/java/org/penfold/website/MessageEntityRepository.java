package org.penfold.website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEntityRepository extends PagingAndSortingRepository<MessageEntity, String> {
    Iterable<MessageEntity> findAllByContactId(String contactId);
    void deleteAllByContactId(String contactId);

    Iterable<MessageEntity> findAllByScheduledTime(String scheduledTime);
}
