package com.example.platforme_iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "spaces")
public class Space {

    @Id
    private String id;
    private String userId;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getSensorList() {
		return sensorList;
	}
	public void setSensorList(List<String> sensorList) {
		this.sensorList = sensorList;
	}
	private String name;
    private String description;
    private List<String> sensorList;

    // Constructors, getters, and setters
}
