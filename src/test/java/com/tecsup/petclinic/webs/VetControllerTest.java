package com.tecsup.petclinic.webs;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.VetTO;

import groovy.util.logging.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VetControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testFindAllPets() throws Exception {

		// int NRO_RECORD = 73;
		int ID_FIRST_RECORD = 1;
	this.mockMvc.perform(get("/vets"))
	        .andExpect(status().isOk())
	        .andExpect(content()
			    .contentType(MediaType.APPLICATION_JSON_VALUE))
	//		    .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
	.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
}
	
	/**
	 * 
	 * @throws Exception
	 * 
	 */
	 @Test 
	 public void testFindVetOK() throws Exception {
		 
		 String FIRST_NAME = "leo";
		 String LAST_NAME = "velazques";
		 

			mockMvc.perform(get("/vets/1"))  // Object must be BASIL
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$.last_name", is(FIRST_NAME)))
					.andExpect(jsonPath("$.first_name", is(LAST_NAME)));
					
		}
	
	@Test
	public void testCreatePet() throws Exception {

		String FIRST_NAME = "Beethoven";
		String LAST_NAME = "Gorls";
		
		VetTO newVetTO = new VetTO();
		newVetTO.setFirstName(FIRST_NAME);
		newVetTO.setLastName(LAST_NAME);
		;

		mockMvc.perform(post("/pets")
						.content(om.writeValueAsString(newVetTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.first_name", is(FIRST_NAME)))
				.andExpect(jsonPath("$.last_name", is(LAST_NAME)));
	
	}
	/**
     * 
     * @throws Exception
     */
	@Test
	public void testDeleteVet() throws Exception {

		String LAST_NAME = "Beethoven3";
		String FIRST_NAME = "Huanm";

		VetTO newVetTO = new VetTO();
		newVetTO.setFirstName(LAST_NAME);
		newVetTO.setLastName(FIRST_NAME);

		ResultActions mvcActions = mockMvc.perform(post("/vets")
						.content(om.writeValueAsString(newVetTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

		mockMvc.perform(delete("/vets/" + id ))
				/*.andDo(print())*/
				.andExpect(status().isOk());
	}

}
