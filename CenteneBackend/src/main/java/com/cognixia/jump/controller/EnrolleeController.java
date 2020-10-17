package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Enrollee;
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
	public Enrollee getEnrollee(@PathVariable Long id) {

		Enrollee enrollee = service.getEnrolleeById(id);

		return enrollee;
	}

	@PostMapping("/enrollee/add")
	public void addCourse(@RequestBody Enrollee enrollee) {
		service.addEnrollee(enrollee);
		System.out.println(enrollee);
	}

	/*
	 * @DeleteMapping("/courses/delete/{courseId}") public ResponseEntity<String>
	 * deleteRegistration(@PathVariable String courseId) {
	 * 
	 * Optional<Course> found = service.findById(courseId);
	 * 
	 * if (found.isPresent()) {
	 * 
	 * service.deleteById(courseId); return
	 * ResponseEntity.status(200).body("Deleted registration with id = " +
	 * courseId); } else { return
	 * ResponseEntity.status(400).header("registration id", courseId + "")
	 * .body("Registration with id = " + courseId + " not found"); }
	 * 
	 * }
	 */

}
