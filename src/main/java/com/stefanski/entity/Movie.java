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

	// Available only at imbd top movie list, check TopMoviesIdFinder Class
	@Column(name = "imdb_position")
	private Integer imdbPosition;

	@Column(name = "imdb_id")
	@JsonProperty("imdbID")
	private String imdbID;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((directors == null) ? 0 : directors.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((imdbID == null) ? 0 : imdbID.hashCode());
		result = prime * result + ((imdbPosition == null) ? 0 : imdbPosition.hashCode());
		result = prime * result + ((imdbRating == null) ? 0 : imdbRating.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userRating == null) ? 0 : userRating.hashCode());
		result = prime * result + ((wantToWatch == null) ? 0 : wantToWatch.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (directors == null) {
			if (other.directors != null)
				return false;
		} else if (!directors.equals(other.directors))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (imdbID == null) {
			if (other.imdbID != null)
				return false;
		} else if (!imdbID.equals(other.imdbID))
			return false;
		if (imdbPosition == null) {
			if (other.imdbPosition != null)
				return false;
		} else if (!imdbPosition.equals(other.imdbPosition))
			return false;
		if (imdbRating == null) {
			if (other.imdbRating != null)
				return false;
		} else if (!imdbRating.equals(other.imdbRating))
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userRating == null) {
			if (other.userRating != null)
				return false;
		} else if (!userRating.equals(other.userRating))
			return false;
		if (wantToWatch == null) {
			if (other.wantToWatch != null)
				return false;
		} else if (!wantToWatch.equals(other.wantToWatch))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", imdbID=" + imdbID + ", title=" + title + ", year=" + year + ", genre=" + genre
				+ ", actors=" + actors + ", directors=" + directors + ", imdbRating=" + imdbRating + ", poster="
				+ poster + ", userRating=" + userRating + ", wantToWatch=" + wantToWatch + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getImdbPosition() {
		return imdbPosition;
	}

	public void setImdbPosition(Integer imdbPosition) {
		this.imdbPosition = imdbPosition;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

}
