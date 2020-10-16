package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrollee implements Serializable {

	private static final long serialVersionUID = 1L;
	// Auto increment
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false)
	private boolean active;

	public Enrollee(Long id, boolean active) {
		super();
		this.id = id;
		this.active = active;
	}

	// Default constructor
	public Enrollee() {
		this(1L, false);
	}

}
