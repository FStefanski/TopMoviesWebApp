package com.stefanski.dao;

import java.util.List;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
public interface MovieListDAO {

	List<String> getTopMoviesIdList();

	void setTopMoviesIdList(List<String> topMoviesIdList);

	List<Movie> getTopMoviesList();

	void setTopMoviesList(List<Movie> topMoviesList);
}
