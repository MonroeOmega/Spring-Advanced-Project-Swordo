package com.example.swordo.repository;

import com.example.swordo.models.entities.Fighter;
import com.example.swordo.models.entities.FighterRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FighterRepository extends JpaRepository<Fighter,Long> {
    Optional<Fighter> findByUsernameAndPassword(String username, String password);
    List<Fighter> findAllByRole(FighterRoleEnum role);
}
