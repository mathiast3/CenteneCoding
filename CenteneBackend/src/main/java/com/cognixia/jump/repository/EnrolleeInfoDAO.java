package com.cognixia.jump.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.EnrolleeInfo;

@Repository
public interface EnrolleeInfoDAO extends MongoRepository<EnrolleeInfo, Long> {

	EnrolleeInfo findBy_id(ObjectId id);

}
