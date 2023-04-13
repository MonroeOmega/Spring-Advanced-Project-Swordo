package com.example.swordo.repository;

import com.example.swordo.models.entities.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster,Long> {
}
