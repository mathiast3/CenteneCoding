package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Enrollee implements Serializable {

	private static final long serialVersionUID = 1L;
	// MongoDb ObjectId as PK
	private @Id ObjectId _id;

	@DBRef
	private List<EnrolleeInfo> information;

	private boolean active;

	public Enrollee(ObjectId _id, List<EnrolleeInfo> information, boolean active) {
		super();
		this._id = _id;
		this.information = information;
		this.active = active;
	}

	// Default constructor
	public Enrollee() {
		this(new ObjectId(), new ArrayList<EnrolleeInfo>(), false);
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
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
