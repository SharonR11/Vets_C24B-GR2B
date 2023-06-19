package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tecsup.petclinic.entities.Vet;
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
	
}
