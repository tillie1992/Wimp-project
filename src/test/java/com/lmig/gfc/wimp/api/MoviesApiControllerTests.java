package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MoviesApiControllerTests {

	private MoviesApiController controller;

	private MovieRepository repo;

	@Before
	public void setUp() {
		repo = mock(MovieRepository.class);
		controller = new MoviesApiController(repo);
	}

	@Test
	public void getAll_returns_list_of_movies() {
		// Arrange
		// every time we create a new array list
		ArrayList<Movie> movie = new ArrayList<Movie>();
		// when(the method call used in the code being tested)
		// .thenReturn(a value that you created)
		when(repo.findAll()).thenReturn(movie);

		// Act will be calling the 'get All' method
		List<Movie> actual = controller.getAll();

		// Assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findAll();

	}

	@Test
	public void create_saves_the_movie_and_returns_it() {
		// Arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);

		// Act
		Movie actual = controller.create(movie);

		// Assert

		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);

	}

	@Test
	public void getOne_returns_a_movie_for_a_valid_id() {
		// Arrange
		Movie movie = new Movie();
		when(repo.findOne(0L)).thenReturn(movie);

		// invoke the method
		// Act
		Movie actual = controller.getOne(0L);

		// Assert
		assertThat(actual).isSameAs(movie);
		// if you need to verify that your method is called more than once

		verify(repo).findOne(0L);
	}

	@Test
	public void getOne_returns_a_movie_for_an_invalid_id() {
		// Arrange
		Movie movie = new Movie();
		// by default, a mock object returns null for a method that returns a
		// capital-letter thing

		// invoke the method
		// Act
		Movie actual = controller.getOne(0L);

		// Assert
		assertThat(actual).isNull();
		// if you need to verify that your method is called more than once
		// verify(repo (times (2)).findOne(0L);
		verify(repo).findOne(0L);

	}
}
