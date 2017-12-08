package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;

import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;

@RestController
@RequestMapping("/api/actors/{actorId}/awards")
public class AwardsApiController {

	private AwardRepository awardRepository;
	private ActorRepository actorRepository;

	public AwardsApiController(AwardRepository awardRepository, ActorRepository actorRepository) {
		this.awardRepository = awardRepository;
		this.actorRepository = actorRepository;
	}

	@PostMapping("")
	public Actor create(@PathVariable Long actorId, @RequestBody Long awardId) {
		Actor actor = actorRepository.findOne(actorId);
		Award award = awardRepository.findOne(awardId);

		if (!actor.getAwards().contains(award)) {
			actor.getAwards().add(award);
			award.setActor(actor);
			actorRepository.save(actor);

		}
		return actor;
	}
}
