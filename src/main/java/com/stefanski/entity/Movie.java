package com.stefanski.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movie")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	// 9 inputs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // name as in the db
	private int id;

	@Column(name = "title")
	@JsonProperty("Title")
	private String title;

	@Column(name = "year")
	@JsonProperty("Year")
	private String year;

	@Column(name = "genre")
	@JsonProperty("Genre")
	private String genre;

	@Column(name = "actors")
	@JsonProperty("Actors")
	private String actors;

	@Column(name = "directors")
	@JsonProperty("Director")
	private String directors;

	@Column(name = "imdb_rating")
	@JsonProperty("imdbRating")
	private String imdbRating;

	@Column(name = "poster")
	@JsonProperty("Poster")
	private String poster;

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

	public void setTitle(String Title) {
		this.title = Title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String Year) {
		this.year = Year;
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
