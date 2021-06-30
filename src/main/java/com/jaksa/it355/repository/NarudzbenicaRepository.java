package com.jaksa.it355.repository;

import com.jaksa.it355.entity.NarudzbeniceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarudzbenicaRepository extends JpaRepository<NarudzbeniceEntity, Integer> {
    NarudzbeniceEntity findTopByOrderByIdDesc();
}
