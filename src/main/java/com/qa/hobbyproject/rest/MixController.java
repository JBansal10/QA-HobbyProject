package com.qa.hobbyproject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.persistence.domain.Mixes;
import com.qa.hobbyproject.persistence.dto.MixesDTO;
import com.qa.hobbyproject.services.MixesService;

@RestController
@RequestMapping("/mixes")
public class MixController {

	private MixesService services;

	@Autowired
	public MixController(MixesService services) {
		super();
		this.services = services;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<MixesDTO>> readAll() {
		return ResponseEntity.ok(this.services.readAll());
	}

	@GetMapping("/read/{Id}")
	public ResponseEntity<MixesDTO> readMix(@PathVariable("Id") Long Id) {
		return ResponseEntity.ok(this.services.readUno(Id));
	}

	@PostMapping("/create")
	public ResponseEntity<MixesDTO> create(@RequestBody Mixes mix) {
		return new ResponseEntity<MixesDTO>(this.services.create(mix), HttpStatus.CREATED);
	}

	@PutMapping("/update/{Id}")
	public ResponseEntity<MixesDTO> update(@PathVariable("Id") Long Id, @RequestBody Mixes updatedMix) {
		return new ResponseEntity<>(this.services.update(Id, updatedMix), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<MixesDTO> deleteMix(@PathVariable Long Id) {
		return this.services.delete(Id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
