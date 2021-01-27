package com.qa.demo.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.hobbyproject.HobbyProjectApplication;
import com.qa.hobbyproject.persistence.domain.Mixes;
import com.qa.hobbyproject.persistence.dto.MixesDTO;
import com.qa.hobbyproject.persistence.repos.MixesRepo;
import com.qa.hobbyproject.services.MixesService;

@SpringBootTest(classes = HobbyProjectApplication.class)
public class MixesServiceUnitTest {

	@Autowired
	private MixesService service;

	@MockBean
	private MixesRepo repoMock;

	private List<Mixes> mixList;
	private List<MixesDTO> mixListDTO;
	private Time time;
	private MixesDTO mixDTO;
	private Mixes mixTest;
	private Long Id;

	@BeforeEach
	void init() {
		this.mixList = new ArrayList<>();
		this.mixListDTO = new ArrayList<>();
		this.time = Time.valueOf("00:57:39");
		this.mixDTO = new MixesDTO(1L, time, "Mix number 1", null);
		this.mixTest = new Mixes(1L, "Mix number 1", time, null);
		this.mixList.add(mixTest);
		this.mixListDTO.add(mixDTO);
		this.Id = 1L;
	}

	@Test
	public void createTest() {
		Mockito.when(this.repoMock.save(Mockito.any(Mixes.class))).thenReturn(this.mixTest);

		MixesDTO result = this.service.create(this.mixTest);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.mixDTO);

		Mockito.verify(this.repoMock, Mockito.times(1)).save(this.mixTest);
	}

	@Test
	public void readAllTest() {

		Mockito.when(this.repoMock.findAll()).thenReturn(this.mixList);

		List<MixesDTO> result = this.service.readAll();
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.mixList);

		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}

	@Test
	public void readUnoTest() {
		Mockito.when(this.repoMock.findById(this.Id)).thenReturn(Optional.of(this.mixTest));

		MixesDTO result = this.service.readUno(this.Id);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.mixDTO);

		Mockito.verify(this.repoMock, Mockito.times(1)).findById(this.Id);
	}

	@Test
	public void updateTest() {
		this.Id = 1L;
		Time updateTime = Time.valueOf("00:58:21");

		Mixes domainUpdate = new Mixes(Id, "Re-Mix number 1", updateTime, null);
		domainUpdate.setId(Id);

		MixesDTO updatedMixDTO = new MixesDTO(Id, time, "Re-Mix number 1", null);
		this.mixTest.setId(Id);

		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(this.mixTest));
		Mockito.when(this.repoMock.save(domainUpdate)).thenReturn(domainUpdate);

		MixesDTO result = this.service.update(1L, domainUpdate);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedMixDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);

	}

	@Test
	public void deleteTest() {
		Mockito.when(this.repoMock.existsById(Id)).thenReturn(false);
		Assertions.assertThat(this.service.delete(Id)).isTrue();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(Id);
	}

	@Test
	public void deleteTestFail() {
		Mockito.when(this.repoMock.existsById(Id)).thenReturn(true);
		Assertions.assertThat(this.service.delete(Id)).isFalse();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(Id);
	}
}
