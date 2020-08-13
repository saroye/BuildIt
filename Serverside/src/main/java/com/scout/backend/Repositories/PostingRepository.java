package com.scout.backend.Repositories;




import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.scout.backend.Models.Postings;

@EnableJpaRepositories
@Repository
public interface PostingRepository extends CrudRepository<Postings, Integer>{
	
	//List <Postings> getAllOrderByDateDesc(Date date);
	
}
