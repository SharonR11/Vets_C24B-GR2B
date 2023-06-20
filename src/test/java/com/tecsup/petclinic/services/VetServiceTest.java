package com.tecsup.petclinic.services;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

@SpringBootTest
@Slf4j
public class VetServiceTest {
	
	 @Autowired
	    private VetService vetService;
	 @Test
	 public void testFindVetById() {

	        Integer ID = 1;
	        String FIRST_NAME = "James";
	        Vet vet=null;
	        
	        try {
	        	vet=this.vetService.findById(ID);
	        } catch (VetNotFoundException e) {
	        	fail(e.getMessage());
	        }
	        log.info("" + vet);
	        assertEquals(FIRST_NAME, vet.getFirstName());
	        //List<Vet> vets = this.vetService.findByFirstName(FIND_FIRSTNAME);
	        //assertEquals(SIZE_EXPECTED, vets.size());
	    }
	 
	 @Test
	 public void testFindVetByFirstName() {

	        String FIND_FIRSTNAME = "James";
	        int SIZE_EXPECTED = 1;

	        List<Vet> vets = this.vetService.findByFirstName(FIND_FIRSTNAME);

	        assertEquals(SIZE_EXPECTED, vets.size());
	    }
	 @Test
	 public void testFindVetByLastName() {

	        String FIND_LASTNAME = "Carter";
	        int SIZE_EXPECTED = 2;

	        List<Vet> vets = this.vetService.findByLastName(FIND_LASTNAME);

	        assertEquals(SIZE_EXPECTED, vets.size());
	    }
	 @Test
	    public void testCreateVet() {

	        String VET_FIRSTNAME = "Carla";
	        String VET_LASTNAME = "Mart";

	        Vet vet = new Vet(VET_FIRSTNAME, "Mart");

	        Vet vetCreated = this.vetService.create(vet);

	        log.info("VET CREATED :" + vetCreated);

	        assertNotNull(vetCreated.getId());
	        assertEquals(VET_FIRSTNAME, vetCreated.getFirstName());
	        assertEquals(VET_LASTNAME, vetCreated.getLastName());

	    }
	 @Test
	 public void testUpdateVet() {
		 String VET_FIRSTNAME = "Julian";
		 String VET_LASTNAME="Perl";
		 
		 String UP_VET_FIRSTNAME="Julian2";
		 String UP_VET_LASTNAME="Perl2";
		 
		 Vet vet=new Vet(VET_FIRSTNAME,VET_LASTNAME);
		 //creando
		 log.info(">" + vet);
		 Vet vetCreated = this.vetService.create(vet);
		 log.info(">>" + vetCreated);
		 
		 //Actualizando
		 vetCreated.setFirstName(UP_VET_FIRSTNAME);
		 vetCreated.setLastName(UP_VET_LASTNAME);
		 
		 Vet upgradeVet = this.vetService.update(vetCreated);
		 log.info(">>>>" + upgradeVet);
		 assertEquals(UP_VET_FIRSTNAME, upgradeVet.getFirstName());
		 assertEquals(UP_VET_LASTNAME,upgradeVet.getLastName());
	 }
	 @Test
	 public void testDeleteVet() {
		 String VET_FIRSTNAME = "Walter";
		 String VET_LASTNAME = "Tornt";
		 //CREATE
		 Vet  vet = new Vet(VET_FIRSTNAME,VET_LASTNAME);
		 vet = this.vetService.create(vet);
		 log.info("" + vet);
		 //delete
		 try {
			 this.vetService.delete(vet.getId());
		 }catch(VetNotFoundException e) {
			 fail(e.getMessage());
		 }
		 
		 //Validando
		 try {
			 this.vetService.findById(vet.getId());
			 assertTrue(false);
		 }catch(VetNotFoundException e) {
			 assertTrue(true);
		 }
	 }
}
