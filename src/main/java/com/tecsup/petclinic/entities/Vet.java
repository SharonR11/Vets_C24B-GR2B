package com.tecsup.petclinic.entities;

import lombok.Data;
import javax.persistence.*;


@Entity(name = "vets")
@Data
public class Vet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	public Vet() {
		super();
	}

	public Vet(Integer id, String first_name, String last_name) {
		super();
		this.id = id;
		this.firstName = first_name;
		this.lastName = last_name;
	}

	public Vet(String first_name, String last_name) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
	}

	

}