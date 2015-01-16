package org.javafans.common.dto;

import java.io.Serializable;

public class IdValueOption implements Serializable{

	private static final long serialVersionUID = -4578398096466122863L;
	
	private Long id;
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
