package com.tecsup.petclinic.webs;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.services.VetService;
import com.tecsup.petclinic.util.TObjectCreator;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class VetControllerMockitoTest {
	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VetRepository vetRepository;

	@MockBean
	private VetService vetService;

	VetMapper mapper = Mappers.getMapper(VetMapper.class);

	@BeforeEach
	void setUp() {
		// Initialize RestAssured
		RestAssuredMockMvc.mockMvc(mockMvc);
	}

	@AfterEach
	void tearDown() {
	}
	@Test
	public void testFindAllVets() throws Exception {

		int NRO_RECORD = 5;
		int ID_FIRST_RECORD = 1;

		List<VetTO> vetTOs  = TObjectCreator.getAllVetTOs();

		List<Vet> vets  = this.mapper.toVetList(vetTOs);

		Mockito.when(vetService.findAll())
				.thenReturn(vets);

		this.mockMvc.perform(get("/vets"))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.size()", is(NRO_RECORD)))
				.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));

	}
	

	@Test
	public void testFindVetOK() throws Exception {

		VetTO vetTO  = TObjectCreator.getVetTO();

		Vet vet  = this.mapper.toVet(vetTO);

		Mockito.when(vetService.findById(vet.getId()))
				.thenReturn(vet);

		mockMvc.perform(get("/vets/1"))  // Object must be BASIL
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(vetTO.getId())))
				.andExpect(jsonPath("$.first_name", is(vetTO.getFirstName())))
				.andExpect(jsonPath("$.last_name", is(vetTO.getLastName())));
			
	}


	@Test
	public void testFindPetKO() throws Exception {

		Integer ID_NOT_EXIST = 666;

		Mockito.when(this.vetService.findById(ID_NOT_EXIST))
				.thenThrow(new VetNotFoundException("Record not found...!"));

		mockMvc.perform(get("/vets/666"))
				.andExpect(status().isNotFound());
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testCreateVet() throws Exception {

		VetTO newVetTO  = TObjectCreator.newVetTO();

		Vet newVet  = this.mapper.toVet(newVetTO);

		Mockito.when(vetService.create(newVet))
				.thenReturn(newVet);

		mockMvc.perform(post("/vets")
				.content(om.writeValueAsString(newVetTO))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.first_name", is(newVetTO.getFirstName())))
				.andExpect(jsonPath("$.last_Name", is(newVetTO.getLastName())));
				

	}


	/**
     * 
     * @throws Exception
     */
	@Test
	public void testDeletePet() throws Exception {

		// ------------ Create ---------------

		VetTO newVetTO  = TObjectCreator.newVetTOForDelete();

		Vet newVet  = this.mapper.toVet(newVetTO);

		Mockito.when(vetService.create(newVet))
				.thenReturn(newVet);

		ResultActions mvcActions = mockMvc.perform(post("/vets")
				.content(om.writeValueAsString(newVetTO))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();


		// ------------ Delete ---------------

		Mockito.doNothing().when(this.vetService).delete(newVet.getId());

		Integer id = JsonPath.parse(response).read("$.id");

		mockMvc.perform(delete("/vets/" + id ))
				/*.andDo(print())*/
				.andExpect(status().isOk());
	}
    
}
    