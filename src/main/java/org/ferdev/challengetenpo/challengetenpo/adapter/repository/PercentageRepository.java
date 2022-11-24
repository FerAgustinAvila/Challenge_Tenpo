package org.ferdev.challengetenpo.challengetenpo.adapter.repository;

import org.ferdev.challengetenpo.challengetenpo.entity.Percentage;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PercentageRepository extends JpaRepository<Percentage, Long> {
    Percentage findFirstByOrderByIdDesc();
}
