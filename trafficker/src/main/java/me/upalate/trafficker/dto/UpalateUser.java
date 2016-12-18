package me.upalate.trafficker.dto;

/**
 * Represents a user in the Upalate system.
 */
public class UpalateUser {
	/**
	 * A new UpalateUser with a first name, last name, and ID.
	 *
	 * @param firstname
	 * @param lastname
	 * @param id
	 */
	public UpalateUser(String firstname,
					   String lastname,
					   Long id) {

		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Long getId() {
		return id;
	}
	private String firstname;
	private String lastname;
	private Long id;
}
