package br.com.elis.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Author implements Printeable {

	private long id;

	private String name;

	private String bio;

	private Date birthdate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String getHeader() {
		return "ID \t NAME \t BIO \t BIRTHDATE";
	}

	@Override
	public String getContent() {
		return String.format("%d \t %s \t %s \t %s", this.id, this.name, this.bio, new SimpleDateFormat("dd/MM/yyyy").format(this.birthdate));
	}

}
