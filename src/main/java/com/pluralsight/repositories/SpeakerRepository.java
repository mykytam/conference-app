package com.pluralsight.repositories;

import com.pluralsight.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> { } //CRUD access
