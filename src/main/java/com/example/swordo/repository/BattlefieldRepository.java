package com.example.swordo.repository;

import com.example.swordo.models.entities.Battlefield;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattlefieldRepository extends JpaRepository<Battlefield,Long> {
}
