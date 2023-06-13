package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 
public class VetTO {
	private Integer id;
	private String name;
	private int typeid;
	private int ownerId;
	private String birthDate;

}
