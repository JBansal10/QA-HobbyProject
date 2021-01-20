package com.qa.hobbyproject.persistence.dto;

public class MixesDTO {
	
	private Long Id;
	private String mixName;
	private Double mixLength;

	public MixesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MixesDTO(Long id, String mixName, Double mixLength) {
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
