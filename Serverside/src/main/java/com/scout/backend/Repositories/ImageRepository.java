package com.scout.backend.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scout.backend.Models.imageModel;

public interface ImageRepository extends JpaRepository<imageModel, Long> {
	Optional<imageModel> findByName(String name);
}
