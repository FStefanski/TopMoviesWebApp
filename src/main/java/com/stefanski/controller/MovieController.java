package com.stefanski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stefanski.dao.MovieDAO;

@Controller
@RequestMapping("/movie")
public class MovieController {

	// CustomerDAO will be used via CustomerServiceImplem
	// need to inject the movie dao
	@Autowired
	private MovieDAO movieDAO;

	@GetMapping("/list") // only GET HTTP mapping
	public String listMovies(/* Model theModel */) {

		// // get customer dao directly
		// List<Movie> theMovies = movieDAO.getCustomers();
		//
		// // add the customers to the model
		// theModel.addAttribute("movies", theMovies);

		return "list-movies";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(/* @RequestParam("movieId") int theId, Model theModel */) {

		// TODO

		return "movie-form";
	}

	@GetMapping("/delete")
	public String dleteMovie(/* @RequestParam("movieId") int theId */) {

		// TODO

		return "redirect:/movie/list";
	}
}
