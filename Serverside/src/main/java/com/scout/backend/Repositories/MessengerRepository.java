package com.scout.backend.Repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scout.backend.Models.Messenger;


@EnableJpaRepositories
@Repository
public interface MessengerRepository extends CrudRepository<Messenger, Integer> {

}
