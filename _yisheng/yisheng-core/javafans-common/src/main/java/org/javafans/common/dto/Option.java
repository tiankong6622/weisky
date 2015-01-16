package org.javafans.common.dto;

import java.io.Serializable;

public class Option implements Serializable{

	private static final long serialVersionUID = -9133204293816227943L;

	private String key;
	
	private String value;
	
	public Option(){
		super();
	}
	
	public Option(String key,String value){
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
