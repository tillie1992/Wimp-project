package com.lmig.gfc.wimp.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.repositories.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorsApiController {

	private ActorRepository actorRepository;

	public ActorsApiController(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	// Change all the return type references from Actor to ActorView
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepository.findAll();

	}

	@GetMapping("{id}")
	public ActorView getOne(@PathVariable Long id) {
		Actor actor = actorRepository.findOne(id);
		ActorView view = new ActorView(actor);
		return view;
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ActorView create(@RequestBody Actor actor) {
		actorRepository.save(actor);
		ActorView view = new ActorView(actor);
		return view;
		
	}

	@PutMapping("{id}")
	public ActorView update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		actorRepository.save(actor);
		ActorView view = new ActorView(actor);
		return view;

	}

	@DeleteMapping("{id}")
	public ActorView delete(@PathVariable long id) {
		Actor actor = actorRepository.findOne(id);
		actorRepository.delete(id);
		ActorView view = new ActorView(actor);
		return view;

	}
}
