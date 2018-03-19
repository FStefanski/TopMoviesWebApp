package com.stefanski.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stefanski.entity.Movie;

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

}
