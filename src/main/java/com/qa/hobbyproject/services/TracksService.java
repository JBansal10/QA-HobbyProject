package com.qa.hobbyproject.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.persistence.domain.Tracks;
import com.qa.hobbyproject.persistence.dto.TracksDTO;
import com.qa.hobbyproject.persistence.repos.TracksRepo;
import com.qa.hobbyproject.utils.MyBeanUtils;

@Service
public class TracksService {

	private TracksRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TracksService(TracksRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TracksDTO mapToDTO(Tracks model) {
		return this.mapper.map(model, TracksDTO.class);
	}

	// Create
	public TracksDTO create(Tracks model) {
		return mapToDTO(this.repo.save(model));
	}

	// Read
	public TracksDTO readUno(Long Id) {
		return this.mapToDTO(this.repo.findById(Id).orElseThrow());
	}

	public List<TracksDTO> readAll() {
		List<Tracks> trackList = this.repo.findAll();
		List<TracksDTO> trackListDTO = trackList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return trackListDTO;
	}

	// Update
	public TracksDTO update(Long Id, Tracks tracks) {
		Tracks updatedTracks = this.repo.findById(Id).orElseThrow();
		MyBeanUtils.mergeNotNull(tracks, updatedTracks);
		return this.mapToDTO(this.repo.save(updatedTracks));
	}

	// Delete
	public boolean delete(Long Id) {
		this.repo.deleteById(Id);
		return !this.repo.existsById(Id);
	}
}
