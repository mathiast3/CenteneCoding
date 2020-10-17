package com.cognixia.jump.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Enrollee;

@Repository
public interface EnrolleeDAO extends MongoRepository<Enrollee, String> {

	Enrollee findBy_id(ObjectId _id);
}
