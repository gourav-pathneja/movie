package com.applnventive.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applnventive.movie.entities.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Integer>{

}
