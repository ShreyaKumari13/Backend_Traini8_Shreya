package com.backend.project.repositories;

import com.backend.project.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, String> {
    Boolean existsByCenterName(String centerName);
}
