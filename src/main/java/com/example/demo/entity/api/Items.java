package com.example.demo.entity.api;

import lombok.Data;

@Data
public class Items {

	private String kind;
	private String id;
	private String etag;
	private String selfLink;
	private VolumeInfo volumeInfo;
}
