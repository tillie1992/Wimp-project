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
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;

public class AwardsApiControllerTests {
	private AwardsApiController controller;
	@Mock private ActorRepository actorRepo;
	@Mock private AwardRepository awardRepo;
	
	@Before
	public void setUP() {
		MockitoAnnotations.initMocks(this);	
		controller =new AwardsApiController(awardRepo, actorRepo);
		
	}
	
	@Test 
	public void create_saves_actor_when_actor_has_no_award() {
		//Arrange
		Actor actor =new Actor();
		actor.setAwards(new ArrayList<Award>());
		Award award = new Award();
		when(actorRepo.findOne(22L)).thenReturn(actor);
		when(awardRepo.findOne(44L)).thenReturn(award);
		
		//Act
		Actor actual = controller.create(22L, 44L);
		
		//Assert
		assertThat(actual).isSameAs(actor);
		assertThat(actor.getAwards()).contains(award);
		verify(actorRepo).save(actor);
		verify(actorRepo).findOne(22L);
		verify(awardRepo).findOne(44L);
		
		
		
		
		
	}
	
}
