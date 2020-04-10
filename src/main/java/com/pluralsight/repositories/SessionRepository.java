package com.pluralsight.repositories;

import com.pluralsight.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> { } //CRUD access
