package com.qa.hobbyproject.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.persistence.domain.Mixes;
import com.qa.hobbyproject.persistence.dto.MixesDTO;
import com.qa.hobbyproject.persistence.repos.MixesRepo;
import com.qa.hobbyproject.utils.MyBeanUtils;

@Service
public class MixesService {

	private MixesRepo repo;
	private ModelMapper mapper;

	@Autowired
	public MixesService(MixesRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private MixesDTO mapToDTO(Mixes model) {
		return this.mapper.map(model, MixesDTO.class);
	}

	// Create
	public MixesDTO create(Mixes model) {
		return mapToDTO(this.repo.save(model));
	}

	// Read
	public MixesDTO readUno(Long Id) {
		return this.mapToDTO(this.repo.findById(Id).orElseThrow());
	}

	public List<MixesDTO> readAll() {
		List<Mixes> mixList = this.repo.findAll();
		List<MixesDTO> mixListDTO = mixList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return mixListDTO;
	}

	// Update
	public MixesDTO update(Long Id, Mixes mixes) {
		Mixes updatedMix = this.repo.findById(Id).orElseThrow();
		MyBeanUtils.mergeNotNull(mixes, updatedMix);
		return this.mapToDTO(this.repo.save(updatedMix));
	}

	// Delete
	public boolean delete(Long Id) {
		this.repo.deleteById(Id);
		return !this.repo.existsById(Id);
	}

}
