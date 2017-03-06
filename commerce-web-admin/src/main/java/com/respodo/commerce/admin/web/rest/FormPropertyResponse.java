package com.respodo.commerce.admin.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.form.FormProperty;

public class FormPropertyResponse {
	
	private String id;
	private String name;
	private String type;
	private String value;
	private Boolean required;
	
	private Map<String, String> enumValues=new HashMap<String, String>();

	public FormPropertyResponse(){
		
	}
	
	public FormPropertyResponse(FormProperty property){
		setId(property.getId());
		setName(property.getName());
		setType(property.getType().getName());
		setValue(property.getValue());
		setRequired(property.isRequired());
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(Map<String, String> enumValues) {
		this.enumValues = enumValues;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	
	

}
