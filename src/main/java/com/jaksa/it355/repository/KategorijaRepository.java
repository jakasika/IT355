package com.jaksa.it355.repository;

import com.jaksa.it355.entity.KategorijeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategorijaRepository extends JpaRepository<KategorijeEntity, Integer> {

}
