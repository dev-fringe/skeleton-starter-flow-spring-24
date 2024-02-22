package org.vaadin.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.example.model.Person;

@RestController
public class BaseController {

	@GetMapping("/hello")
	public String h() {
		return "Hello";
	}

	@GetMapping("/p")
	public List<Person> p(@RequestParam(required = false, name = "text") String text) {
		List<Person> p = Arrays.asList(new Person("Nicolaus Copernicus", 1543), new Person("Galileo Galilei", 1564),
				new Person("Johannes Kepler", 1571));
		if (text == null) {
			return p;
		} else {
			List<Person> p2 = new ArrayList<>();
			for (Person person : p) {
				if (person.getName().contains(text)) {
					p2.add(person);
				}
			}
			return p2;
		}
	}
}
