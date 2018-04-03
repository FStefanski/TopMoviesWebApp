package com.stefanski.bgjobs;

import java.util.concurrent.Future;

import com.stefanski.entity.Movie;

public interface MovieRESTClient {

	Future<Movie> fetchMovieByIdConcurrently(Integer imdbPostion, String imdbID);

	Movie fetchMovieById(String imdbID);

	Movie fetchMovieById(Integer imdbPostion, String imdbID);

}
