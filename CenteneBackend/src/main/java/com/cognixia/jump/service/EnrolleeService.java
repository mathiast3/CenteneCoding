package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

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

		Optional<Enrollee> enrolleeOpt = repository.findBy_id(id);

		if (enrolleeOpt.isPresent()) {
			return enrolleeOpt.get();

		}
		return new Enrollee();
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
		enrollee.setId(ObjectId.get());
		repository.save(enrollee);

		return enrollee;
	}

	public Enrollee delete(ObjectId id) {
		// remove all dependents

		Optional<Enrollee> enrolleeOpt = repository.findBy_id(id);
		if (enrolleeOpt.isPresent()) {
			Enrollee enrollee = enrolleeOpt.get();
			List<EnrolleeInfo> info = enrollee.getInformation();
			for (EnrolleeInfo enrolleeInfo : info) {
				infoRepository.delete(enrolleeInfo);
			}

			repository.delete(enrollee);
			return enrollee;
		}
		return new Enrollee();

	}

	public String update(ObjectId id, Enrollee enrollee) {
		// insert enrollee dependents into db
		List<EnrolleeInfo> info = enrollee.getInformation();
		for (EnrolleeInfo enrolleeInfo : info) {
			infoRepository.save(enrolleeInfo);
		}
		// set id of the object we are changing
		enrollee.setId(id);
		repository.save(enrollee);

		return enrollee.toString();

	}

	public void addInfo(Enrollee enrollee, EnrolleeInfo info) {
		enrollee.getInformation().add(info);
		repository.save(enrollee);
		infoRepository.save(info);
	}

	public EnrolleeInfo deleteInfo(ObjectId infoId) {

		Optional<EnrolleeInfo> infoOpt = infoRepository.findBy_id(infoId);

		if (infoOpt.isPresent()) {
			infoRepository.delete(infoOpt.get());
			return infoOpt.get();
		}

		return new EnrolleeInfo();

	}

	public void updateInfo(EnrolleeInfo oldInfo, EnrolleeInfo newInfo) {
		oldInfo.setFirstName(newInfo.getFirstName());
		oldInfo.setLastName(newInfo.getLastName());
		oldInfo.setBirthday(newInfo.getBirthday());
		oldInfo.setPhone(newInfo.getPhone());
		infoRepository.save(oldInfo);
	}

	public EnrolleeInfo getEnrolleeInfoById(ObjectId id) {

		Optional<EnrolleeInfo> infoOpt = infoRepository.findBy_id(id);

		if (infoOpt.isPresent()) {
			return infoOpt.get();

		}
		return new EnrolleeInfo();
	}

}
