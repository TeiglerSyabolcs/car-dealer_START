package com.programozzteis.cardealer.cardealer.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
