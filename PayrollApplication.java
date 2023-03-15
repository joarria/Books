package com.example.demo;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Book{
	private @Id @GeneratedValue Long id;
	private String name;
	private String author;

	Book(String name, String author){
		this.name = name;
		this.author = author;
	}

	public Long getId(){
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Book))
			return false;
		Book book = (Book) o;
		return Objects.equals(this.id, book.id) && Objects.equals(this.name, book.name)
				&& Objects.equals(this.author, book.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.author);
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.author + '\'' + '}';
	}
}


