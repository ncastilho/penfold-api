package org.penfold.website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends PagingAndSortingRepository<PreferencesEntity, String> {
    PreferencesEntity findByContactId(String contactId);
    void deleteAllByContactId(String contactId);
}
