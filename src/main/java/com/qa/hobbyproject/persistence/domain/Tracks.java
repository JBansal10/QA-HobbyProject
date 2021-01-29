package com.qa.hobbyproject.persistence.domain;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Tracks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private Time trackLength;

	@NotNull
	private String trackArtist;

	@NotNull
	private String trackName;

	@ManyToOne
	private Mixes mixes;

	public Tracks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tracks(Long Id, Time trackLength, String trackName, String trackArtist, Mixes mixes) {
		super();
		this.Id = Id;
		this.trackLength = trackLength;
		this.trackName = trackName;
		this.trackArtist = trackArtist;
		this.mixes = mixes;

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

	public Mixes getMixes() {
		return mixes;
	}

	public void setMixes(Mixes mixes) {
		this.mixes = mixes;
	}

	@Override
	public String toString() {
		return "Tracks [Id=" + Id + ", trackLength=" + trackLength + ", trackArtist=" + trackArtist + ", trackName="
				+ trackName + ", mixes=" + mixes + "]";
	}

}
