package com.jida.domain;

import lombok.Getter;

@Getter
public class Attraction {
	private long attractionId;
	private long typeId;
	private String name;
	private String address;
	private String image1;
	private String image2;
	private double latitude;
	private double longitude;
	private int sidoCode;
	private long planCnt;
}
