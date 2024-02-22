package org.vaadin.example.model;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

	@Nonnull
	private String name;
	@Nonnull
	private int birth;

	public Person() {
	}

	public Person(String firstName, int yyyy) {
		super();
		this.name = firstName;
		this.birth = yyyy;
	}
}