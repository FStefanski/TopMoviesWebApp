package com.stefanski.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // name as in the db
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "year")
	private String year;

	@Column(name = "genre")
	private String genre;

	@Column(name = "actors")
	private String actors;

	@Column(name = "directors")
	private String directors;

	@Column(name = "imdb_rating")
	private String imdbRating;

	@Column(name = "user_rating")
	private String userRating;

	@Column(name = "want_to_watch")
	private String wantToWatch;

	// non args constructor - not needed but add it as best practice
	public Movie() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	public String getWantToWatch() {
		return wantToWatch;
	}

	public void setWantToWatch(String wantToWatch) {
		this.wantToWatch = wantToWatch;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genre=" + genre + ", actors=" + actors
				+ ", directors=" + directors + ", imdbRating=" + imdbRating + ", userRating=" + userRating
				+ ", wantToWatch=" + wantToWatch + "]";
	}

}
