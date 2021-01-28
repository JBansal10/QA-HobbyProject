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
import com.qa.hobbyproject.persistence.domain.Tracks;
import com.qa.hobbyproject.persistence.dto.TracksDTO;
import com.qa.hobbyproject.persistence.repos.TracksRepo;
import com.qa.hobbyproject.services.TracksService;

@SpringBootTest(classes = HobbyProjectApplication.class)
public class TracksServiceUnitTest {

	@Autowired
	private TracksService service;

	@MockBean
	private TracksRepo repoMock;

	private List<Tracks> trackList;
	private Time time;
	private TracksDTO trackDTO;
	private Tracks trackTest;
	private Long Id;

	@BeforeEach
	void init() {
		this.trackList = new ArrayList<>();
		this.time = Time.valueOf("00:03:29");
		this.trackDTO = new TracksDTO(Id, time, "Pendulum", "Driver");
		this.trackTest = new Tracks(Id, time, "Driver", "Pendulum", null);
		this.Id = 1L;
		this.trackList.add(trackTest);
	}

	@Test
	public void createTest() {
		Mockito.when(this.repoMock.save(Mockito.any(Tracks.class))).thenReturn(trackTest);

		TracksDTO result = this.service.create(trackTest);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(trackTest);

		Mockito.verify(this.repoMock, Mockito.times(1)).save(trackTest);
	}

	@Test
	public void readUnoTest() {
		Mockito.when(this.repoMock.findById(Id)).thenReturn(Optional.of(trackTest));

		TracksDTO result = this.service.readUno(Id);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(trackDTO);

		Mockito.verify(this.repoMock, Mockito.times(1)).findById(Id);
	}

	@Test
	public void readAllTest() {
		Mockito.when(this.repoMock.findAll()).thenReturn(trackList);

		List<TracksDTO> result = this.service.readAll();
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(trackList);

		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}

	@Test
	public void updateTest() {
		trackTest.setId(1L);
		Tracks updatedTrack = new Tracks(1L, time, "Track Name", "Track Artist", null);
		updatedTrack.setId(1L);

		TracksDTO updatedDTO = new TracksDTO(1L, time, "Track Artist", "Track Name");

		Mockito.when(this.repoMock.findById(Id)).thenReturn(Optional.of(trackTest));
		Mockito.when(this.repoMock.save(Mockito.any(Tracks.class))).thenReturn(updatedTrack);

		TracksDTO result = this.service.update(Id, updatedTrack);

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(Id);
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
