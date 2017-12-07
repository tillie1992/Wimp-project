package com.lmig.gfc.wimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmig.gfc.wimp.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
