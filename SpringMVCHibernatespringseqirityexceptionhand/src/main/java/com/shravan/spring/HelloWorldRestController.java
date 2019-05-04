package com.shravan.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shravan.spring.model.Person;
import com.shravan.spring.service.PersonService;

@RestController
public class HelloWorldRestController {

	@Autowired
	PersonService userService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> listAllUsers() {
		List<Person> users = userService.listPersons();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		Person user = userService.getPersonById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Person user,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		userService.addPerson(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}")
				.buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Person> updateUser(@PathVariable("id") int id,
			@RequestBody Person user) {
		System.out.println("Updating User " + id);

		Person currentUser = userService.getPersonById(id);

		currentUser.setName(user.getName());
		currentUser.setCard(user.getCard());
		currentUser.setPassword(user.getPassword());

		userService.updatePerson(currentUser);
		return new ResponseEntity<Person>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Person> deleteUser(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Person user = userService.getPersonById(id);

		userService.removePerson(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

}