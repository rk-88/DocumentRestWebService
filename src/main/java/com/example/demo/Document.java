package com.example.demo;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.utils.DocumentGenerator;

@Entity
public class Document {
	
	@Id 
	private String id;
	private String value;
	private String emp_dept;

	//For Json deserialization
	public Document() {
		
	}

	public Document(String value, String emp_dept) throws IOException {
		this.id = DocumentGenerator.getAlphaNumericString();
		this.value = value;
		this.emp_dept = emp_dept;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEmp_dept() {
		return emp_dept;
	}

	public void setEmp_dept(String emp_dept) {
		this.emp_dept = emp_dept;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", value=" + value + ", emp_dept=" + emp_dept + "]";
	}

	

	
	
	

}
