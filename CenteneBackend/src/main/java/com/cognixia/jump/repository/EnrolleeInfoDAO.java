package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Enrollee;

@Repository
public interface EnrolleeInfoDAO extends MongoRepository<Enrollee, Long> {

}
