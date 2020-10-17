package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class EnrolleeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// MongoDb ObjectId as PK
	private @Id Integer id;

	private String firstName;

	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	private String phone;

	public EnrolleeInfo(Integer id, String firstName, String lastName, LocalDate birthday, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phone = phone;
	}

	public EnrolleeInfo() {
		this(-1, "N/A", "N/A", LocalDate.parse("2020-01-08"), "N/A");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
