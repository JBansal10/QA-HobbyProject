package com.qa.hobbyproject.persistence.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Mixes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String mixName;

	@NotNull
	private Double mixLength;

	public Mixes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mixes(Long id, @NotNull String mixName, @NotNull Double mixLength) {
		super();
		Id = id;
		this.mixName = mixName;
		this.mixLength = mixLength;
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

	public Double getMixLength() {
		return mixLength;
	}

	public void setMixLength(Double mixLength) {
		this.mixLength = mixLength;
	}

	@Override
	public String toString() {
		return "Mixes [Id=" + Id + ", mixName=" + mixName + ", mixLength=" + mixLength + "]";
	}

}
