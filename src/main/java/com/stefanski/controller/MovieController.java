package com.stefanski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stefanski.dao.MovieDAO;
import com.stefanski.entity.Movie;
import com.stefanski.service.MovieService;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

	// need to inject the movie Service
	@Autowired
	private MovieService movieService;

	/* CRUD template: create, read, update and delete */
	/* CRUD: Create */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Movie theMovie = new Movie();

		// add empty movie attribute to the model
		theModel.addAttribute("movie", theMovie);

		return "movie-form";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie theMovie) {

		// save the movie using dao
		movieService.saveMovie(theMovie);

		return "redirect:/movie/list";
	}

	/* CRUD: Read */
	@GetMapping("/list") // only GET HTTP mapping
	public String listMovies(Model theModel) {

		// get Movie dao directly
		List<Movie> theMovies = movieService.getMovies();

		// add the movies to the model attribute - model binding for view form/tags
		theModel.addAttribute("movies", theMovies); // use the same name in view form/tags

		return "list-movies";
	}

	/* CRUD: Update */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("movieId") int theId, Model theModel) {

		// get the movie from the service
		Movie theMovie = movieService.getMovie(theId);

		// set movie as a model attribute to pre-populate the form
		theModel.addAttribute("movie", theMovie);

		// send over to our form
		return "movie-form";
	}

	/* CRUD: Delete */
	@GetMapping("/delete")
	public String dleteMovie(@RequestParam("movieId") int theId) {

		// delete the movie using dao
		movieService.deleteMovie(theId);

		return "redirect:/movie/list";
	}

	@PostMapping("/search")
	public String searchMovies(@RequestParam("theSearchedValue") String theSearchedValue, Model theModel) {

		// search Movies from the service
		List<Movie> theMovies = movieService.searchMovies(theSearchedValue);

		// add the Movies to the model
		theModel.addAttribute("movies", theMovies);
		return "list-movies";
	}

	/* Download top movies list & data */
	// Moved to a independent FetchMovieController
}
