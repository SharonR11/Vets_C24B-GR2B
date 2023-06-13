package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     *
     */
    @Test
    public void testFindVetById() {

        Integer ID = 1;
        String NAME = "Leo";
        Vet vet = null;

        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("" + vet);
        assertEquals(NAME, vet.getName());

    }

    /**
     *
     */
    @Test
    public void testFindVetByName() {

        String FIND_NAME = "Leo";
        int SIZE_EXPECTED = 1;

        List<Vet> vets = this.vetService.findByName(FIND_NAME);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    /**
     *
     */
    @Test
    public void testFindVetByTypeId() {

        int TYPE_ID = 5;
        int SIZE_EXPECTED = 2;

        List<Vet> vets = this.vetService.findByTypeId(TYPE_ID);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    /**
     *
     */
    @Test
    public void testFindVetByOwnerId() {

        int OWNER_ID = 10;
        int SIZE_EXPECTED = 2;

        List<Vet> vets = this.vetService.findByOwnerId(OWNER_ID);

        assertEquals(SIZE_EXPECTED, vets.size());

    }

    /**
     * To get ID generate , you need
     * setup in id primary key in your
     * entity this annotation :
     *
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Test
    public void testCreateVet() {

        String VET_NAME = "Ponky";
        int OWNER_ID = 1;
        int TYPE_ID = 1;

        Vet vet = new Vet(VET_NAME, 1, 1, null);

        Vet vetCreated = this.vetService.create(vet);

        log.info("VET CREATED :" + vetCreated);

        assertNotNull(vetCreated.getId());
        assertEquals(VET_NAME, vetCreated.getName());
        assertEquals(OWNER_ID, vetCreated.getOwnerId());
        assertEquals(TYPE_ID, vetCreated.getTypeId());

    }


    /**
     *
     */
    @Test
    public void testUpdateVet() {

        String VET_NAME = "Bear";
        int OWNER_ID = 1;
        int TYPE_ID = 1;

        String UP_VET_NAME = "Bear2";
        int UP_OWNER_ID = 2;
        int UP_TYPE_ID = 2;

        Vet vet = new Vet(VET_NAME, OWNER_ID, TYPE_ID, null);

        // ------------ Create ---------------

        log.info(">" + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info(">>" + vetCreated);

        // ------------ Update ---------------

        // Prepare data for update
        vetCreated.setName(UP_VET_NAME);
        vetCreated.setOwnerId(UP_OWNER_ID);
        vetCreated.setTypeId(UP_TYPE_ID);

        // Execute update
        Vet upgradeVet = this.vetService.update(vetCreated);
        log.info(">>>>" + upgradeVet);

        //            EXPECTED        ACTUAL
        assertEquals(UP_VET_NAME, upgradeVet.getName());
        assertEquals(UP_OWNER_ID, upgradeVet.getTypeId());
        assertEquals(UP_TYPE_ID, upgradeVet.getOwnerId());
    }

    /**
     *
     */
    @Test
    public void testDeleteVet() {

        String VET_NAME = "Bird";
        int OWNER_ID = 1;
        int TYPE_ID = 1;

        // ------------ Create ---------------

        Vet vet = new Vet(VET_NAME, OWNER_ID, TYPE_ID, null);
        vet = this.vetService.create(vet);
        log.info("" + vet);

        // ------------ Delete ---------------

        try {
            this.vetService.delete(vet.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validation ---------------

        try {
            this.vetService.findById(vet.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }

    }
}
