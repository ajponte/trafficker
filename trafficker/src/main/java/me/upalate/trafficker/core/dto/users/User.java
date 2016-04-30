package me.upalate.trafficker.core.dto.users;

import me.upalate.trafficker.core.dto.food.Food;

import java.util.Date;

/**
 * Resource to represent a User in our system.
 * A user in our system has similar fields 
 * accross all our features.
 */

public class User {
	private long id;
	private String firstName;
	private String lastName;
	private Date dob;
	private char sex;
	private double weight;
	private double height;
	private Food[] favoriteFoods;

	public User(long id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	public User(long id,
				String firstName,
				String lastName,
				Date dob,
				double weight,
				double height,
				char sex,
				Food[] favoriteFoods) {
		this.id = id;
		this.firstName = firstName;
		this.dob = dob;
		this.weight = weight;
		this.height = height;
		this.sex = sex;
		this.favoriteFoods = favoriteFoods;
	}

	public long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDob() {
		return dob;
	}
	
	public char getSex() {
		return sex;
	}
	
	public double getWeight() {
		return weight;
	}

	public Food[] getFavoriteFoods() {
		return favoriteFoods;
	}					
			
}
