package com.cognixia.jump.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.model.EnrolleeInfo;
import com.cognixia.jump.service.EnrolleeService;

@RequestMapping("/api")
@RestController
public class EnrolleeController {

	@Autowired
	EnrolleeService service;

	@GetMapping("/enrollees")
	public List<Enrollee> getAllEnrollees() {
		System.out.println(service.getAllEnrollees());

		return service.getAllEnrollees();
	}

	@GetMapping("/enrollee/{id}")
	public Enrollee getEnrollee(@PathVariable ObjectId id) {

		Enrollee enrollee = service.getEnrolleeById(id);

		return enrollee;
	}

	@PostMapping("/enrollee/add")
	public Enrollee addEnrollee(@RequestBody Enrollee enrollee) {

		Enrollee newEnrollee = service.addEnrollee(enrollee);

		return newEnrollee;

	}

	@PostMapping("/enrollee/add/info/{id}")
	public Enrollee addEnrolleeInfo(@PathVariable ObjectId id, @RequestBody EnrolleeInfo info) {

		Enrollee enrollee = service.getEnrolleeById(id);
		service.addInfo(enrollee, info);
		return enrollee;

	}

	@DeleteMapping("/enrollee/delete/info/{id}")
	public Enrollee deleteEnrolleeInfo(@PathVariable ObjectId id, @RequestBody EnrolleeInfo info) {

		Enrollee enrollee = service.getEnrolleeById(id);
		service.deleteInfo(enrollee, info);
		return enrollee;

	}

	@DeleteMapping("/enrollee/delete/{id}")
	public ResponseEntity<String> deleteEnrollee(@PathVariable ObjectId id) {

		Enrollee enrollee = service.getEnrolleeById(id);
		service.delete(enrollee);
		return ResponseEntity.status(200).body("Deleted registration with id = " + id);

	}

	@PutMapping("/enrollee/update/{id}")
	public @ResponseBody String updateEnrollee(@PathVariable("id") ObjectId id, @RequestBody Enrollee enrollee) {
		return service.update(id, enrollee);
	}

	@PutMapping("/enrollee/update/info/{id}")
	public EnrolleeInfo updateEnrolleeInfo(@PathVariable ObjectId id, @RequestBody EnrolleeInfo info) {

		EnrolleeInfo enrolleeInfo = service.getEnrolleeInfoById(id);
		service.updateInfo(enrolleeInfo, info);
		return info;

	}

}
