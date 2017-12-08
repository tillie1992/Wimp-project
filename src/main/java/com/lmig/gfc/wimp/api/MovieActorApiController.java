package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies/{movieId}/actors")
public class MovieActorApiController {
	private ActorRepository ar;
	private MovieRepository mr;

	public MovieActorApiController(MovieRepository mr, ActorRepository ar) {
		this.ar = ar;
		this.mr = mr;

	}

	@PostMapping("")
	public Movie create(@PathVariable Long movieId, @RequestBody Long actorId) {
		Movie movie = mr.findOne(movieId);
		Actor actor = ar.findOne(actorId);

		if (!movie.getActors().contains(actor)) {
			actor.getMovies().add(movie);
			ar.save(actor);

		}
		return movie;
	}
}
