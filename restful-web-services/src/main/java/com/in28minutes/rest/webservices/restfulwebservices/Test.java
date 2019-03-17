package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
	
	private String id;
	public Test(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private String name;
	
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

	@RequestMapping(method=RequestMethod.GET,path="/hello")
	public Test mymethod() {
		
		return new Test("12","MyName");
	}
	
	
 

}
