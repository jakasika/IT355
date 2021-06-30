package com.jaksa.it355.repository;

import com.jaksa.it355.entity.KupacEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KupacRepository extends JpaRepository<KupacEntity, Integer> {
    KupacEntity findTopByOrderByIdDesc();
}
