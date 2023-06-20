package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.util.TObjectCreator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class VetServiceMockitoTest {

	private VetService vetService;

    @Mock //Bean
    private VetRepository repository;

    @BeforeEach
    void setUp() {
        this.vetService = new VetServiceImpl(this.repository);
    }
    @Test
    public void testFindVetById() {

        Vet vetExpected = TObjectCreator.getVet();
        Mockito.when(this.repository.findById(vetExpected.getId()))
                .thenReturn((Optional.of(vetExpected)));
        try {
            vetExpected = this.vetService.findById(vetExpected.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("" + vetExpected);
        assertEquals(vetExpected.getFirstName(), vetExpected.getFirstName());

    }
    @Test
    public void testFindPetByFirstName() {

        String FIND_FIRSTNAME = "James";

        List<Vet> vetsExpected = TObjectCreator.getVetsForFindByFirstName();

        Mockito.when(this.repository.findByFirstName(FIND_FIRSTNAME))
                .thenReturn(vetsExpected);

        List<Vet> vets = this.vetService.findByFirstName(FIND_FIRSTNAME);

        assertEquals(vetsExpected.size(), vets.size());
    }
    
    @Test
    public void testFindPetByLastName() {

        String FIND_LASTNAME = "Leary";

        List<Vet> vetsExpected = TObjectCreator.getVetsForFindByFirstName();

        Mockito.when(this.repository.findByLastName(FIND_LASTNAME))
                .thenReturn(vetsExpected);

        List<Vet> vets = this.vetService.findByLastName(FIND_LASTNAME);

        assertEquals(vetsExpected.size(), vets.size());
    }
    @Test
    public void testUpdateVet() {

        String UP_FIRSTNAME = "Marco";
        String UP_LASTNAME = "Gerard";

        Vet newVet = TObjectCreator.newVetForUpdate();
        Vet newVetCreate = TObjectCreator.newVetCreatedForUpdate();

        // ------------ Create ---------------

        Mockito.when(this.repository.save(newVet))
                .thenReturn(newVetCreate);

        Vet vetCreated = this.vetService.create(newVet);
        log.info("{}" , vetCreated);

        // ------------ Update ---------------

        // Prepare data for update
        vetCreated.setFirstName(UP_FIRSTNAME);
        vetCreated.setLastName(UP_LASTNAME);

        // Create
        Mockito.when(this.repository.save(vetCreated))
                .thenReturn(vetCreated);

        // Execute update
        Vet upgradeVet = this.vetService.update(vetCreated);
        log.info("{}" + upgradeVet);

        //            EXPECTED           ACTUAL
        assertEquals(UP_FIRSTNAME, upgradeVet.getFirstName());
        assertEquals(UP_LASTNAME, upgradeVet.getLastName());
    }
    
    @Test
    public void testDeleteVet() {

        Vet newVet = TObjectCreator.newVetForDelete();
        Vet newVetCreate = TObjectCreator.newVetCreatedForDelete();

        // ------------ Create ---------------

        Mockito.when(this.repository.save(newVet))
                .thenReturn(newVetCreate);

        Vet vetCreated = this.vetService.create(newVet);
        log.info("{}" ,vetCreated);

        // ------------ Delete ---------------

        Mockito.doNothing().when(this.repository).delete(newVetCreate);
        Mockito.when(this.repository.findById(newVetCreate.getId()))
                .thenReturn(Optional.of(newVetCreate));

        try {
            this.vetService.delete(vetCreated.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validate ---------------

        Mockito.when(this.repository.findById(newVetCreate.getId()))
                .thenReturn(Optional.ofNullable(null));

        try {
            this.vetService.findById(vetCreated.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }

    }
}
