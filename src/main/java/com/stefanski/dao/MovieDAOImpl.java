package com.stefanski.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Repository
public class MovieDAOImpl implements MovieDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Movie> getMovies() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by
		Query<Movie> theQuery = currentSession.createQuery("from Movie order by imdbPosition", Movie.class);
		// org.hibernate.query.Query hibernate 5.2

		// execute query and get result list
		List<Movie> movie = theQuery.getResultList();

		// return the results
		return movie;
	}

	@Transactional
	@Override
	public void saveMovie(Movie theMovie) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		if (theMovie != null) {
			// save the movie
			currentSession.saveOrUpdate(theMovie);
		}
	}

	@Transactional
	@Override
	public void saveAllMovies(List<Movie> theMoviesList) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// added movies caounter
		int addedMoviesCounter = 0;
		// save only if the title doesn't exists
		String theSearchedValue = null;
		// set empty Query
		Query theQuery = null;

		for (Movie movie : theMoviesList) {

			// save only if the title doesn't exists
			theSearchedValue = movie.getTitle();

			// set empty Query
			theQuery = null;

			// search thru title column ... case insensitive
			theQuery = currentSession.createQuery("from Movie where lower(title) like :theValue", Movie.class);

			theQuery.setParameter("theValue", "%" + theSearchedValue.toLowerCase() + "%");

			if (theQuery.getResultList().isEmpty()) {
				// save the movie
				currentSession.saveOrUpdate(movie);

				addedMoviesCounter++;

			} else {
				System.out.println("\t\t>> Movie: " + theSearchedValue + " already exists!");
			}
		}
		System.out.println("\t>> Saved to data base " + addedMoviesCounter + " of " + theMoviesList.size() + " movies");
	}

	@Transactional
	@Override
	public Movie getMovie(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// new retrieve/read from database using the primary key
		Movie theMovie = currentSession.get(Movie.class, theId);

		return theMovie;
	}

	@Transactional
	@Override
	public void deleteMovie(int theId) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Movie where id=:movieId");

		theQuery.setParameter("movieId", theId);

		theQuery.executeUpdate();
	}

	@Transactional
	@Override
	public List<Movie> searchMovies(String theSearchedValue) {

		// get the current hibernate session & set empty Query
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		// search only if theSearchedValue is not empty
		if (theSearchedValue != null && theSearchedValue.trim().length() > 0) {

			// search thru all columns ... case insensitive
			theQuery = currentSession.createQuery(
					"from Movie where lower(title) like :theValue " + "or year like :theValue or genre like :theValue "
							+ "or lower(actors) like :theValue or lower(directors) like :theValue "
							+ "or imdbRating like :theValue or userRating like :theValue "
							+ "or lower(wantToWatch) like :theValue " /* + "order by imdbPosition asc" */,
					Movie.class);

			theQuery.setParameter("theValue", "%" + theSearchedValue.toLowerCase() + "%");
		} else {

			// if theSearchedValue is empty ... get all movies as in default list
			theQuery = currentSession.createQuery("from Movie order by imdbPosition", Movie.class);
		}

		// execute query and get result list
		List<Movie> movies = theQuery.getResultList();

		return movies;
	}

	@Transactional
	@Override
	public List<Movie> searchMoviesByImdbID(String movieId) {
		// get the current hibernate session & set empty Query
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		// search only if movieId is not empty
		if (movieId != null && movieId.trim().length() > 0) {

			// search thru all columns ... case insensitive
			theQuery = currentSession.createQuery("from Movie where lower(imdbID) like :theValue ", Movie.class);

			theQuery.setParameter("theValue", "%" + movieId.toLowerCase() + "%");
		}

		// execute query and get result list
		List<Movie> movies = theQuery.getResultList();

		return movies;
	}

}
