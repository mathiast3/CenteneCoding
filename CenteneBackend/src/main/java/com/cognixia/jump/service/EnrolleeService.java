package com.cognixia.jump.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.model.EnrolleeInfo;
import com.cognixia.jump.repository.EnrolleeDAO;
import com.cognixia.jump.repository.EnrolleeInfoDAO;

@Service
public class EnrolleeService {
	@Autowired
	private EnrolleeDAO repository;

	@Autowired
	private EnrolleeInfoDAO infoRepository;

	// get mappings
	public Enrollee getEnrolleeById(ObjectId id) {
		Enrollee enrollee = repository.findBy_id(id);

		return enrollee;
	}

	public List<Enrollee> getAllEnrollees() {
		return repository.findAll();
	}

	// post
	public Enrollee addEnrollee(Enrollee enrollee) {

		// insert enrollee dependents into db
		List<EnrolleeInfo> info = enrollee.getInformation();
		for (EnrolleeInfo enrolleeInfo : info) {
			infoRepository.save(enrolleeInfo);
		}
		// zooDB.add(newAnimal);
		repository.save(enrollee);

		return enrollee;
	}

}
