package com.jaksa.it355.repository;

import com.jaksa.it355.entity.ProizvodiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodRepository extends JpaRepository<ProizvodiEntity, Integer> {
}
