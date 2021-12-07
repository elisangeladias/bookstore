package br.com.elis.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Printeable {

	private Long id;

	private String title;

	private String isbn;

	private String description;

	private Date releaseDate;

	private Author author;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String getHeader() {
		return "ID \t TITLE \t ISBN \t RELEASE_DATE \t AUTHOR_NAME \t DESCRIPTION";
	}

	@Override
	public String getContent() {
		return String.format("%d \t %s \t %s \t %s \t %s \t %s", this.id, this.title, this.isbn,
				new SimpleDateFormat("dd/MM/yyyy").format(this.releaseDate), this.author.getName(), this.description);
	}

}
