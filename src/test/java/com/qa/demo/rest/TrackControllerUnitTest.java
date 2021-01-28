package com.qa.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.hobbyproject.HobbyProjectApplication;
import com.qa.hobbyproject.persistence.domain.Tracks;
import com.qa.hobbyproject.persistence.dto.TracksDTO;
import com.qa.hobbyproject.rest.TrackController;
import com.qa.hobbyproject.services.TracksService;

@SpringBootTest(classes = HobbyProjectApplication.class)
public class TrackControllerUnitTest {
	
	@Autowired
	private TrackController controller;
	
	@MockBean
	TracksService service;
	
	private List<Tracks> trackList;
	private TracksDTO trackDTO;
	private Tracks trackTest;
	private Long Id;
	private ModelMapper mapper = new ModelMapper();
	private Time time;
	
	private TracksDTO mapToDTO(Tracks model) {
		return this.mapper.map(model, TracksDTO.class);
	}
	
	@BeforeEach
	void init() {
		this.Id = 1L;
		this.trackList = new ArrayList<>();
		this.time = Time.valueOf("00:03:29");
		this.trackDTO = new TracksDTO(Id, time, "Pendulum", "Driver");
		this.trackTest = new Tracks(Id, time, "Driver", "Pendulum", null);
		
		this.trackList.add(trackTest);
		this.trackDTO = this.mapToDTO(trackTest);
	}
	
	@Test
	public void createTest() {
		Mockito.when(this.service.create(trackTest)).thenReturn(trackDTO);
		
		assertThat(new ResponseEntity<TracksDTO>(trackDTO, HttpStatus.CREATED))
					.usingRecursiveComparison().isEqualTo(controller.create(trackTest));
		
		Mockito.verify(this.service, Mockito.times(1)).create(trackTest);
	}
	
	@Test
	public void readUnoTest() {
		Mockito.when(this.service.readUno(Id)).thenReturn(trackDTO);
		
		assertThat(ResponseEntity.ok(this.service.readUno(Id)))
				.usingRecursiveComparison().isEqualTo(controller.readTrack(Id));
		
		Mockito.verify(this.service, Mockito.times(2)).readUno(Id);
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.service.readAll()).thenReturn(trackList.stream().map
				(this::mapToDTO).collect(Collectors.toList()));
		
		assertThat(ResponseEntity.ok(this.service.readAll()))
				.usingRecursiveComparison().isEqualTo(controller.readAll());
		
		Mockito.verify(this.service, Mockito.times(2)).readAll();
	}
	
	@Test
	public void updateTest() {
		Tracks updatedTrack = new Tracks(1L, time, "Ascension", "Fourward", null);
		TracksDTO updatedDTO = new TracksDTO(1L, time, "Fourward", "Ascension");
		
		Mockito.when(this.service.update(Id, updatedTrack)).thenReturn(updatedDTO);
		
		assertThat(new ResponseEntity<>(updatedDTO, HttpStatus.ACCEPTED))
					.usingRecursiveComparison()
					.isEqualTo(controller.update(Id, updatedTrack));
		
		Mockito.verify(this.service, Mockito.times(1)).update(Id, updatedTrack);
	}

	@Test
	public void deleteTest() {
		Mockito.when(this.service.delete(Id)).thenReturn(true);
		
		assertThat(new ResponseEntity<>(HttpStatus.NO_CONTENT))
				.usingRecursiveComparison().isEqualTo(controller.deleteTrack(Id));
		
		Mockito.verify(this.service, Mockito.times(1)).delete(Id);
	}	
	
	@Test
	public void deleteFailTest() {
		controller.deleteTrack(Id);
		
		Mockito.verify(this.service, Mockito.times(1)).delete(Id);
	}

}
