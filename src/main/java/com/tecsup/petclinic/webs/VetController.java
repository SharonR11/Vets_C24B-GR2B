package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.VetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.services.VetService;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class VetController {

    private final VetService vetService;
    private final VetMapper mapper;

    public VetController(VetService vetService, VetMapper mapper) {
        this.vetService = vetService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/vets")
    public ResponseEntity<List<VetTO>> findAllVets() {
        List<Vet> vets = vetService.findAll();
        log.info("vets: {}", vets);
        List<VetTO> vetsTO = mapper.toVetTOList(vets);
        log.info("vetsTO: {}", vetsTO);
        return ResponseEntity.ok(vetsTO);
    }

    @PostMapping(value = "/vets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VetTO> createVet(@RequestBody VetTO vetTO) {
        Vet newVet = mapper.toVet(vetTO);
        VetTO newVetTO = mapper.toVetTO(vetService.create(newVet));
        return ResponseEntity.status(HttpStatus.CREATED).body(newVetTO);
    }

    @GetMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> findVetById(@PathVariable Integer id) {
        VetTO vetTO;
        try {
            Vet vet = vetService.findById(id);
            vetTO = mapper.toVetTO(vet);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vetTO);
    }

    @PutMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> updateVet(@RequestBody VetTO vetTO, @PathVariable Integer id) {
        VetTO updatedVetTO;
        try {
            Vet updatedVet = vetService.findById(id);
            
            updatedVet.setFirstName(vetTO.getFirstName());
            updatedVet.setLastName(vetTO.getLastName());
            vetService.update(updatedVet);
            updatedVetTO = mapper.toVetTO(updatedVet);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVetTO);
    }

    @DeleteMapping(value = "/vets/{id}")
    public ResponseEntity<String> deleteVet(@PathVariable Integer id) {
        try {
            vetService.delete(id);
            return ResponseEntity.ok("Deleted ID: " + id);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

