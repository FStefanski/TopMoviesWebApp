package com.stefanski.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Repository
public class MovieListDAOImpl {

	List<String> topMoviesIdList = new ArrayList<>();
	List<Movie> topMoviesList = new ArrayList<>();

	// non-arg constructor
	public MovieListDAOImpl() {
	}

	public List<String> getTopMoviesIdList() {
		return topMoviesIdList;
	}

	public void setTopMoviesIdList(List<String> topMoviesIdList) {
		this.topMoviesIdList = topMoviesIdList;
	}

	public List<Movie> getTopMoviesList() {
		return topMoviesList;
	}

	public void setTopMoviesList(List<Movie> topMoviesList) {
		this.topMoviesList = topMoviesList;
	}

	@Override
	public String toString() {
		return "MovieListDAOImpl [topMoviesIdList=" + topMoviesIdList + ", topMoviesList=" + topMoviesList + "]";
	}
}
