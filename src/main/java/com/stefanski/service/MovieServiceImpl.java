package com.stefanski.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	// need to inject the movie DAO
	@Autowired
	private MovieDAO movieDAO;

	@Transactional // defining transactions at Service layer
	@Override
	public List<Movie> getMovies() {
		return movieDAO.getMovies();
	}

	@Transactional
	@Override
	public void saveMovie(Movie theMovie) {
		movieDAO.saveMovie(theMovie);
	}

	@Transactional
	@Override
	public void saveAllMovies(List<Movie> theMoviesList) {
		movieDAO.saveAllMovies(theMoviesList);
	}

	@Transactional
	@Override
	public void deleteMovie(int theId) {
		movieDAO.deleteMovie(theId);
	}

	@Transactional
	@Override
	public Movie getMovie(int theId) {
		return movieDAO.getMovie(theId);
	}

	@Transactional
	@Override
	public List<Movie> searchMovies(String theSearchedValue) {
		return movieDAO.searchMovies(theSearchedValue);
	}

	@Transactional
	@Override
	public List<Movie> searchMoviesByImdbID(String movieId) {
		return movieDAO.searchMoviesByImdbID(movieId);
	}

}
