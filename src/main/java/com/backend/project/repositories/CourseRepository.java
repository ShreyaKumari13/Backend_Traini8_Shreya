package com.backend.project.repositories;

import com.backend.project.entities.Courses;
import com.backend.project.payload.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses, Integer> {
}
