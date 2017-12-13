package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MovieActorApiControllertests {
	private MovieActorApiController controller;
	@Mock private ActorRepository actorRepo;
	@Mock private MovieRepository movieRepo;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieActorApiController( movieRepo, actorRepo);
		
	}
	
	@Test
	public void create_saves_movie_when_actor_is_not_in_the_movie() {
	
		//Arrange
		Movie movie = new Movie();
		movie.setActors(new ArrayList<Actor>());
		Actor actor = new Actor();
		actor.setMovies(new ArrayList<Movie>());
		when(movieRepo.findOne(22L)).thenReturn(movie);
		when(actorRepo.findOne(44L)).thenReturn(actor);
		
		//Act
		Movie actual = controller.create(22L, 44L);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		verify(actorRepo).save(actor);
		assertThat(actor.getMovies()).contains(movie);
		verify(movieRepo).findOne(22L);
		verify(actorRepo).findOne(44L);
		
	}
	

}
