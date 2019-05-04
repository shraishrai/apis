package com.shravan.spring.service;

import java.util.List;

import com.shravan.spring.model.Person;
import com.shravan.spring.model.PersonDetail;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public PersonDetail getPersonDtlById(int id);
	public void removePerson(int id);
	
}
