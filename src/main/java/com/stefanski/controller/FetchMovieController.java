package com.stefanski.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stefanski.service.MovieRESTService;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@Controller
@RequestMapping("/fetchMovie")
public class FetchMovieController {

	// need to inject the movie movieRESTService
	@Autowired
	private MovieRESTService movieRESTService;

	/* Download top movies list & data */
	@GetMapping("/fetchTopMovieList") // only GET HTTP mapping
	public String fetchTopMovieListFromServer(Model theModel) {

		System.out.println(">> Inside FetchMovieController.fetchTopMovieListFromServer() ...");
		// parse the top movie list web site for actual list with movies ids
		Map<Integer, String> topMoviesMap = movieRESTService.findAllTopMovies();

		// use the fetched id list to consume a 3rd party REST server
		// AND fetch the movies details
		// AND save the movies using service after each fetched movie
		// concurrently
		movieRESTService.fetchAndSaveAllMoviesConcurrently(topMoviesMap);
		// or one by one
		// movieRESTService.fetchAndSaveAllMoviesOneByOne(topMoviesMap);

		return "redirect:/movie/list";
	}
}
