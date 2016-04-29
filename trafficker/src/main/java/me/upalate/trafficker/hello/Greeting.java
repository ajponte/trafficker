package me.upalate.trafficker.hello;

/**
 * Greeting resource.
 */
public class Greeting {
	public long id;
	public String content;

	/**
    * A new Greeting.
    */
	public Greeting (long id, String content) {
		this.id = id;
		this.content = content;
	}

	/**
    * Returns the id of this Greeting.
	 */
	public long getId() {
		return id;
	}

	/**
    * Returns the content of this Greeting.
	 */
	public String getContent() {
		return content;
	}
}
