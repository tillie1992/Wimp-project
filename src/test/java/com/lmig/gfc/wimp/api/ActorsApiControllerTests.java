package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Actor;

import com.lmig.gfc.wimp.repositories.ActorRepository;

public class ActorsApiControllerTests {
	private ActorsApiController controller;
	private ActorRepository repo;
	private Actor actor;
	private List<Actor> actors;
	private ActorView view;

	@Before
	public void setUp() {
		repo = mock(ActorRepository.class);
		controller = new ActorsApiController(repo);
		actor = new Actor("Ricky", "Bobby", null, null);
		actors = new ArrayList<Actor>();

	}

	@Test
	public void getAll_returns_list_of_actors() {
		// Arrange
		when(repo.findAll()).thenReturn(actors);

		// Act
		List<Actor> actual = controller.getAll();

		// Assert
		assertThat(actual).isSameAs(actors);
		verify(repo).findAll();

	}

	@Test
	public void getOne_returns_an_actor_for_a_valid_id() {
		// Arrange
		when(repo.findOne(0L)).thenReturn(actor);

		// Act
		ActorView actual = controller.getOne(0L);

		// Assert
		assertThat(actual.getFirstName()).isEqualTo("Ricky");
		assertThat(actual.getLastName()).isEqualTo("Bobby");
		verify(repo).findOne(0L);
	}

	@Test
	public void create_the_actorview_and_returns_it() {
		// Arrange

		when(repo.save(actor)).thenReturn(actor);
		// Act
		ActorView actual = controller.create(actor);

		// Assert
		assertThat(actual.getFirstName()).isEqualTo("Ricky");
		assertThat(actual.getLastName()).isEqualTo("Bobby");
		verify(repo).save(actor);

	}
	
	@Test 
	public void update_set_id_of_actor_and_calls_save_and_returns_actor() {
		//Arrange
		when(repo.findOne(22L)).thenReturn(actor);		
		//Act
		ActorView actual=controller.update(actor, 22L);
		
		//Assert
		assertThat(actual.getFirstName()).isEqualTo("Ricky");
		assertThat(actual.getLastName()).isEqualTo("Bobby");
		assertThat(actor.getId()).isEqualTo(22L);
	
	}
	
	@Test
	public void delete_gets_the_actor_and_deletes_it_from_the_repo_and_returns_it() {
		when(repo.findOne(0L)).thenReturn(actor);
		
		
		//Act
		ActorView actual= controller.delete(0L);
		
		
		//Assert
		assertThat(actual.getFirstName()).isEqualTo("Ricky");
		assertThat(actual.getLastName()).isEqualTo("Bobby");
		verify(repo).findOne(0L);
		verify(repo).delete(0L);
		
	}
	
	

}
