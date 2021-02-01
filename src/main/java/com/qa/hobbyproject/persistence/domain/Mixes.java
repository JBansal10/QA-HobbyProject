package com.qa.hobbyproject.persistence.domain;

import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Mixes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String mixName;

	@NotNull
	private Time mixLength;

	@OneToMany(mappedBy = "mixes", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Tracks> trackList;

	public Mixes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mixes(Long id, String mixName, Time mixLength, List<Tracks> trackList) {
		super();
		Id = id;
		this.mixLength = mixLength;
		this.mixName = mixName;
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

	public List<Tracks> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Tracks> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "Mixes [Id=" + Id + ", mixLength=" + mixLength + ", mixName=" + mixName + ", trackList=" + trackList
				+ "]";
	}

}
