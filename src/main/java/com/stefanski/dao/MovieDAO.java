package com.stefanski.dao;

import java.util.List;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
public interface MovieDAO {

	List<Movie> getCustomers();

	void saveMovie(Movie theMovie);

	void deleteMovie(int theId);

	Movie getMovie(int theId);
}
