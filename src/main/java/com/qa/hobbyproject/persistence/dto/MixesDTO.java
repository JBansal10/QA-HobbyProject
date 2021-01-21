package com.qa.hobbyproject.persistence.dto;

import java.sql.Time;
import java.util.List;

public class MixesDTO {

	private Long Id;
	private String mixName;
	private Time mixLength;
	private List<TracksDTO> trackList;

	public MixesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MixesDTO(Long id, String mixName, Time mixLength, List<TracksDTO> trackList) {
		super();
		Id = id;
		this.mixName = mixName;
		this.mixLength = mixLength;
		this.trackList = trackList;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMixName() {
		return mixName;
	}

	public void setMixName(String mixName) {
		this.mixName = mixName;
	}

	public Time getMixLength() {
		return mixLength;
	}

	public void setMixLength(Time mixLength) {
		this.mixLength = mixLength;
	}

	public List<TracksDTO> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<TracksDTO> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "MixesDTO [Id=" + Id + ", mixName=" + mixName + ", mixLength=" + mixLength + ", trackList=" + trackList
				+ "]";
	}
}
