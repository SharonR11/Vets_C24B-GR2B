package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

public interface VetService {

	/**
	 * 
	 * @param vet
	 * @return
	 */
	Vet create(Vet vet);

	/**
	 * 
	 * @param vet
	 * @return
	 */
	Vet update(Vet vet);

	/**
	 * 
	 * @param id
	 * @throws PetNotFoundException
	 */
	void delete(Integer id) throws VetNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	Vet findById(Integer id) throws VetNotFoundException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Vet> findByFirstName(String firstName);

	/**
	 * 
	 * @param typeId
	 * @return
	 */
	List<Vet> findByLastName(int lastName);

	/**
	 * 
	 * @param ownerId
	 * @return
	 */
	List<Vet> findAll();
}