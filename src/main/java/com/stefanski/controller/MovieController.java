package com.stefanski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;

@Controller
@RequestMapping("/movie")
public class MovieController {

	// need to inject the movie dao
	@Autowired
	private MovieDAO movieDAO;

	/* CRUD template: create, read, update and delete */
	/* CRUD: Create */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(/* @RequestParam("movieId") int theId */) {

		// TODO

		return "redirect:/movie/list";
	}

	/* CRUD: Read */
	@GetMapping("/list") // only GET HTTP mapping
	public String listMovies(Model theModel) {

		// get customer dao directly
		List<Movie> theMovies = movieDAO.getCustomers();

		// add the movies to the model attribute - model binding for view form/tags
		theModel.addAttribute("movies", theMovies); // use the same name in view form/tags

		return "list-movies";
	}

	/* CRUD: Update */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(/* @RequestParam("movieId") int theId, Model theModel */) {

		// TODO

		return "movie-form";
	}

	/* CRUD: Delete */
	@GetMapping("/delete")
	public String dleteMovie(/* @RequestParam("movieId") int theId */) {

		// TODO

		return "redirect:/movie/list";
	}
}
