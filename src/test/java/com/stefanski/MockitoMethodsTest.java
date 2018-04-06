package com.stefanski;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.stefanski.entity.Movie;

/**
 * 
 * @author Frederik Stefanski
 *
 */
@RunWith(MockitoJUnitRunner.class) // enable the use of annotations with Mockito tests
public class MockitoMethodsTest {

	@Mock
	private List<Movie> theMoviesList;

	@Mock
	private Movie movie;

	@Test
	public void findAllTopMovies_Test() {
		theMoviesList.add(movie);
		// OK if action performed only ONCE
		Mockito.verify(theMoviesList).add(movie);
		assertEquals(0, theMoviesList.size());

		Mockito.when(theMoviesList.size()).thenReturn(100);
		assertEquals(100, theMoviesList.size());
	}

	@Spy
	List<String> spiedList = new ArrayList<String>();

	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjected() {
		spiedList.add("one");
		spiedList.add("two");

		Mockito.verify(spiedList).add("one");
		Mockito.verify(spiedList).add("two");

		assertEquals(2, spiedList.size());

		Mockito.doReturn(100).when(spiedList).size();
		assertEquals(100, spiedList.size());
	}

	@Mock
	List mockedList;

	@Captor
	ArgumentCaptor argCaptor;

	@Test
	public void whenUseCaptorAnnotation_thenTheSam() {
		mockedList.add("one");
		Mockito.verify(mockedList).add(argCaptor.capture());

		assertEquals("one", argCaptor.getValue());
	}
}
