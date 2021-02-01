package com.qa.hobbyproject.persistence.dto;

import java.sql.Time;

public class TracksDTO {

	private Long Id;
	private Time trackLength;
	private String trackArtist;
	private String trackName;

	public TracksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TracksDTO(Long Id, Time trackLength, String trackArtist, String trackName) {
		super();
		this.Id = Id;
		this.trackLength = trackLength;
		this.trackArtist = trackArtist;
		this.trackName = trackName;

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackArtist() {
		return trackArtist;
	}

	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}

	public Time getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(Time trackLength) {
		this.trackLength = trackLength;
	}

	@Override
	public String toString() {
		return "Tracks [Id=" + Id + ", trackName=" + trackName + ", trackArtist=" + trackArtist + ", trackLength="
				+ trackLength + "]";
	}

}
