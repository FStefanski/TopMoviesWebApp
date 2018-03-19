package com.stefanski.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
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

		return null;
	}

}
