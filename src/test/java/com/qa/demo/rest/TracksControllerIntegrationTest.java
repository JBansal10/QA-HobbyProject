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
import com.qa.hobbyproject.persistence.domain.Tracks;
import com.qa.hobbyproject.persistence.dto.TracksDTO;

@SpringBootTest(classes = HobbyProjectApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql" ,"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles="test")
public class TracksControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private TracksDTO mapToDTO(Tracks model) {
		return this.mapper.map(model, TracksDTO.class);
	}
	
	private final int TEST_ID = 1;
	
	@Test
	public void createTrack() throws Exception {
		Time time = Time.valueOf("00:03:29");
		//'00:03:29', 'Driver', 'Pendulum', 1
		Tracks testTrackDomain = new Tracks(3L, time, "All Night", "Millbrook/Selin", null);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/tracks/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testTrackDomain))
				.accept(MediaType.APPLICATION_JSON);
		
		testTrackDomain.setId(3L);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString
						(mapToDTO(testTrackDomain)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readAll() throws Exception {
		Time time = Time.valueOf("00:03:29");
		
		List<TracksDTO> trackList = new ArrayList<>();
		trackList.add(new TracksDTO(1L, time, "Pendulum", "Driver"));
		trackList.add(new TracksDTO(2L, time, "Fourward", "Ascension"));
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/tracks/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(trackList))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(trackList));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void readUno() throws Exception {
		Time time = Time.valueOf("00:03:29");
		TracksDTO trackRead = new TracksDTO(1L, time, "Pendulum", "Driver");
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/tracks/read/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				// .content(this.jsonifier.writeValueAsString(updateTrack))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(trackRead));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void updateTest() throws Exception {
		Time time = Time.valueOf("00:04:29");
		TracksDTO updateTrack = new TracksDTO(1L, time, "Millbrook/Selin", "All Night");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/tracks/update/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(updateTrack))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(updateTrack));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);		
	}
	
	@Test
	public void deleteTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/tracks/delete/" + TEST_ID);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
