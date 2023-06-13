package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Vet;

@SpringBootTest
@Slf4j
public class VetServiceTest {
	
	 @Autowired
	    private VetService vetService;
	 
	 
	 @Test
	 public void testFindVetByFirstName() {

	        String FIND_FIRSTNAME = "Leo";
	        int SIZE_EXPECTED = 1;

	        List<Vet> vets = this.vetService.findByFirstName(FIND_FIRSTNAME);

	        assertEquals(SIZE_EXPECTED, vets.size());
	    }
	 
	 @Test
	    public void testCreateVet() {

	        String VET_FIRSTNAME = "Carla";
	        String VET_LASTNAME = "Mart";

	        Vet vet = new Vet(VET_FIRSTNAME, VET_LASTNAME);

	        Vet vetCreated = this.vetService.create(vet);

	        log.info("VET CREATED :" + vetCreated);

	        assertNotNull(vetCreated.getId());
	        assertEquals(VET_FIRSTNAME, vetCreated.getFirstName());
	        assertEquals(VET_LASTNAME, vetCreated.getLastName());

	    }
	 
	 
}
