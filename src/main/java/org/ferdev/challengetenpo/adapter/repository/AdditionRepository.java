package org.ferdev.challengetenpo.adapter.repository;

import org.ferdev.challengetenpo.entity.Addition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionRepository extends JpaRepository<Addition, Long> {
}