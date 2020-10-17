package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.repository.EnrolleeDAO;

@Service
public class EnrolleeService {
	@Autowired
	private EnrolleeDAO repository;

	// get mappings
	public Enrollee getEnrolleeById(Long id) {
		Optional<Enrollee> found = repository.findById(id);

		if (found.isPresent()) {
			return found.get();
		}
		return new Enrollee();
	}

	public List<Enrollee> getAllEnrollees() {
		return repository.findAll();
	}

	// post
	public Enrollee addEnrollee(Enrollee enrollee) {

		// zooDB.add(newAnimal);
		repository.save(new Enrollee(new ObjectId(), true));

		return enrollee;
	}

}
