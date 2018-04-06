package com.stefanski.service;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.stefanski.bgjobs.TopMoviesIdFinder;
import com.stefanski.dao.MovieDAO;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@RunWith(MockitoJUnitRunner.class) // enable the use of annotations with Mockito tests
public class MovieRESTServiceTest {

	@Mock // use to create and inject mocked instances
	private MovieDAO movieDAO;

	@Mock
	private TopMoviesIdFinder topMoviesIdFinder;

	@InjectMocks // to inject mock fields into the tested object automatically
	private MovieRESTServiceImpl movieRESTServiceImpl;

	@Before
	public void setup() {

	}

	// demonstrates the return of map values
	@Test
	public void findAllTopMovies_Test() {
		// Mapping movie imdb top movie position (key) with the imbd id (value)
		Map<Integer, String> topMoviesMap = new TreeMap<>();
		topMoviesMap.put(1, "tt0092067");

		Mockito.when(topMoviesIdFinder.findAllTopMovies()).thenReturn(topMoviesMap);
		Assert.assertEquals(topMoviesMap.get(1), movieRESTServiceImpl.findAllTopMovies().get(1));
	}

	// demonstrates the behavior if map value is empty
	@Test
	public void fetchAndSaveAllMoviesConcurrently_Test1() {
		// Mapping movie imdb top movie position (key) with the imbd id (value)
		Map<Integer, String> topMoviesMap = new TreeMap<>();

		Mockito.when(topMoviesIdFinder.findAllTopMovies()).thenReturn(topMoviesMap);
		Assert.assertTrue(movieRESTServiceImpl.findAllTopMovies().isEmpty());
	}

	// demonstrates the behavior if map is null
	@Test
	public void fetchAndSaveAllMoviesConcurrently_Test2() {

		Mockito.when(topMoviesIdFinder.findAllTopMovies()).thenReturn(null);
		Assert.assertTrue(movieRESTServiceImpl.findAllTopMovies().isEmpty());
	}
}
