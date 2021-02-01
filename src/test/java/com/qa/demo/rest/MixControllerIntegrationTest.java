package com.qa.demo.rest;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobbyproject.HobbyProjectApplication;
import com.qa.hobbyproject.persistence.domain.Mixes;
import com.qa.hobbyproject.persistence.dto.MixesDTO;

@SpringBootTest(classes = HobbyProjectApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles="test")
public class MixControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private MixesDTO mapToDTO(Mixes model) {
		return this.mapper.map(model, MixesDTO.class);
	}
	
	private final int TEST_ID = 1;
	
	@Test
	public void createMix() throws Exception {
		Time time = Time.valueOf("00:57:39");
		Mixes testMixDomain = new Mixes(3L, "2020 Round Up", time, null);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/mixes/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testMixDomain))
				.accept(MediaType.APPLICATION_JSON);
		
		testMixDomain.setId(3L);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString
						(mapToDTO(testMixDomain)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void readAll() throws Exception {
		Time time = Time.valueOf("00:58:58");
		Time time2 = Time.valueOf("00:59:34");
		
		List<MixesDTO> mixList = new ArrayList<>();
		mixList.add(new MixesDTO(1L, time, "2020 September Drum and Bass - PENDULUM RETURNS", new ArrayList<>()));
		mixList.add(new MixesDTO(2L, time2, "2021 FFA Vol.1", new ArrayList<>()));
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/mixes/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(mixList))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(mixList));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void readUno() throws Exception {
		Time time = Time.valueOf("00:58:58");
		Mixes testMixDomain = new Mixes(1L, "2020 September Drum and Bass - PENDULUM RETURNS", time, new ArrayList<>());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/mixes/read/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(testMixDomain));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void updateTest() throws Exception {
		Time time = Time.valueOf("00:57:39");
		MixesDTO testMixDTO = new MixesDTO(1L, time, "2020 Round Up", new ArrayList<>());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/mixes/update/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testMixDTO))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(testMixDTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void deleteMix() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/mixes/delete/" + TEST_ID);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
				
		
}
