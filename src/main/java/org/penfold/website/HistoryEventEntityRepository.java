package org.penfold.website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryEventEntityRepository extends PagingAndSortingRepository<HistoryEventEntity, String> {
    Iterable<HistoryEventEntity> findAllByHistoryId(String historyId);
}
