package org.penfold.website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactEntityRepository extends PagingAndSortingRepository<ContactEntity, String> {

    Iterable<ContactEntity> findAllByOrderByCreatedDateDesc();
}
