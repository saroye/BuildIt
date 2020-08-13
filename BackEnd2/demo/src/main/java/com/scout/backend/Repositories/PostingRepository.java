package com.scout.backend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.scout.backend.models.Postings;

@Repository
public interface PostingRepository extends CrudRepository<Postings, String>{

	
}