package com.lmig.gfc.wimp.config;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@Configuration
public class MovieAndActorData {

	public MovieAndActorData(ActorRepository actorRepository, MovieRepository movieRepository) {

		movieRepository.save(new Movie("Rockin' With Java", null, 3000000L, "DreamWorks"));
		actorRepository.save(new Actor("Rory", "Rogers", null, null));

	}

}
