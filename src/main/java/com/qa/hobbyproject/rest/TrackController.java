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

import com.qa.hobbyproject.persistence.domain.Tracks;
import com.qa.hobbyproject.persistence.dto.TracksDTO;
import com.qa.hobbyproject.services.TracksService;

@RestController
@RequestMapping("/tracks")
public class TrackController {

	private TracksService service;

	@Autowired
	public TrackController(TracksService service) {
		super();
		this.service = service;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<TracksDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{Id}")
	public ResponseEntity<TracksDTO> readTrack(@PathVariable("Id") Long Id) {
		return ResponseEntity.ok(this.service.readUno(Id));
	}

	@PostMapping("/create")
	public ResponseEntity<TracksDTO> create(@RequestBody Tracks tracks) {
		return new ResponseEntity<TracksDTO>(this.service.create(tracks), HttpStatus.CREATED);
	}

	@PutMapping("/update/{Id}")
	public ResponseEntity<TracksDTO> update(@PathVariable("Id") Long Id, @RequestBody Tracks updatedTrack) {
		return new ResponseEntity<>(this.service.update(Id, updatedTrack), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<TracksDTO> deleteTrack(@PathVariable("Id") Long Id) {
		return this.service.delete(Id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
