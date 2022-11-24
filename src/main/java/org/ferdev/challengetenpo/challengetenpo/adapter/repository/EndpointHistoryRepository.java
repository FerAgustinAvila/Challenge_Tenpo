package org.ferdev.challengetenpo.challengetenpo.adapter.repository;

import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@org.springframework.stereotype.Repository
public interface EndpointHistoryRepository extends JpaRepository<EndpointHistory, Long> {
    @Query(value = "FROM EndpointHistory E ORDER BY E.id")
    Page<EndpointHistory> findAllOrderById(@Param("pageable") Pageable pageable);
}