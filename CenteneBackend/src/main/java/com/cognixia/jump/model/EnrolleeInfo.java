package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class EnrolleeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// MongoDb ObjectId as PK
	private @Id ObjectId id;

	private String firstName;

	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	private String phone;

}
