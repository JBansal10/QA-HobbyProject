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
import com.qa.hobbyproject.persistence.domain.Mixes;
import com.qa.hobbyproject.persistence.dto.MixesDTO;
import com.qa.hobbyproject.rest.MixController;
import com.qa.hobbyproject.services.MixesService;

@SpringBootTest(classes = HobbyProjectApplication.class)
public class MixControllerUnitTest {

	@Autowired
	private MixController controller;
	
	@MockBean
	private MixesService service;
	
	private List<Mixes> mixList;
	private MixesDTO mixDTO;
	private Mixes testMix;
	private Long Id;
	private ModelMapper mapper = new ModelMapper();
	private Time time;
	
	private MixesDTO mapToDTO(Mixes model) {
		return this.mapper.map(model, MixesDTO.class);
	}
	
	@BeforeEach
	void init() {
		this.Id = 1L;
		this.mixList = new ArrayList<>();
		this.time = Time.valueOf("00:58:58");
		this.testMix = new Mixes(Id, "2020 September Drum and Bass - PENDULUM RETURNS", time, null);
		this.mixDTO = new MixesDTO(Id, time, "2020 September Drum and Bass - PENDULUM RETURNS", null);
		
		this.mixList.add(testMix);
		this.mixDTO = this.mapToDTO(testMix);
	}
	
	@Test
	public void createTest() {
		Mockito.when(this.service.create(testMix)).thenReturn(mixDTO);
		
		assertThat(new ResponseEntity<MixesDTO>(mixDTO, HttpStatus.CREATED))
				.usingRecursiveComparison().isEqualTo(controller.create(testMix));
		
		Mockito.verify(this.service, Mockito.times(1)).create(testMix);
	}
	
	@Test
	public void readUnoTest() {
		Mockito.when(this.service.readUno(Id)).thenReturn(mixDTO);
		
		assertThat(ResponseEntity.ok(this.service.readUno(Id)))
				.usingRecursiveComparison().isEqualTo(controller.readMix(Id));
		
		Mockito.verify(this.service, Mockito.times(2)).readUno(Id);
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.service.readAll()).thenReturn(mixList.stream().map
				(this::mapToDTO).collect(Collectors.toList()));
		
		assertThat(ResponseEntity.ok(this.service.readAll()))
				.usingRecursiveComparison().isEqualTo(controller.readAll());
		
		Mockito.verify(this.service, Mockito.times(2)).readAll();
	}
	
	@Test
	public void updateTest() {
		Mixes updatedMix = new Mixes(Id, "2020 Round Up", time, null);
		MixesDTO updatedDTO = new MixesDTO(Id, time, "2020 Round Up", null);
		
		Mockito.when(this.service.update(Id, updatedMix)).thenReturn(updatedDTO);
		
		assertThat(new ResponseEntity<>(updatedDTO, HttpStatus.ACCEPTED))
				.usingRecursiveComparison().isEqualTo
				(controller.update(Id, updatedMix));
		
		Mockito.verify(this.service, Mockito.times(1)).update(Id, updatedMix);		
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(this.service.delete(Id)).thenReturn(true);
		
		assertThat(new ResponseEntity<>(HttpStatus.NO_CONTENT))
				.usingRecursiveComparison().isEqualTo(controller.deleteMix(Id));
		
		Mockito.verify(this.service, Mockito.times(1)).delete(Id);
	}
	
	@Test
	public void deleteFailTest() {
		controller.deleteMix(Id);
		
		Mockito.verify(this.service, Mockito.times(1)).delete(Id);	
	}
	
}
