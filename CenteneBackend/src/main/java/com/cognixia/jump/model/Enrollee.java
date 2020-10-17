package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Enrollee implements Serializable {

	private static final long serialVersionUID = 1L;
	// MongoDb ObjectId as PK
	private @Id ObjectId id;

	@DBRef
	private List<EnrolleeInfo> information;

	private boolean active;

	public Enrollee(ObjectId id, boolean active) {
		super();
		this.id = id;
		this.active = active;
	}

	// Default constructor
	public Enrollee() {
		this(new ObjectId(), false);
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<EnrolleeInfo> getInformation() {
		return information;
	}

	public void setInformation(List<EnrolleeInfo> information) {
		this.information = information;
	}

}
