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
	public List<Movie> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by
		Query<Movie> theQuery = currentSession.createQuery("from Movie order by id", Movie.class);
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

		// save the customer
		currentSession.saveOrUpdate(theMovie);
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

}
