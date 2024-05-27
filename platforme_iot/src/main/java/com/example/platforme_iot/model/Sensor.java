package com.example.platforme_iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "sensors")
public class Sensor {
    @Id
    private String id;
    private String name;
    private String description;
    private String spaceId;
    private String uniqueId;
    private String type ;
    private List<values> values;

    // Constructors, getters, setters
    
    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", spaceId='" + spaceId +"type='" + type + '\'' +
                // Add other properties as needed
                '}';
    }
    public Sensor() {}

    public Sensor(String name, String description, String spaceId, String uniqueId,String type ,List<values> values) {
        this.name = name;
        this.description = description;
        this.spaceId = spaceId;
        this.uniqueId = uniqueId;
        this.type = type;
        this.values = values;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public List<values> getData() {
		return values;
	}

	public void setValues(List<values> values) {
		this.values = values;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

   
}

class values {
    private Object value;
    private long time;

    // Constructors, getters, setters
    public values() {}

    public values(Object value, long time) {
        this.value = value;
        this.time = time;
    }

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

    
}
