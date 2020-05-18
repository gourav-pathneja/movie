package com.applnventive.movie.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.applnventive.movie.entities.Movie;
import com.applnventive.movie.exceptions.GenericException;
import com.applnventive.movie.models.MoviesRequestData;
import com.applnventive.movie.models.MoviesResponseData;

public interface MoviesService {
	Page<Movie> findAll(Pageable pageable);

	Integer createMovie(MoviesRequestData movie) throws GenericException;

	void updateMovie(Integer id, MoviesRequestData requestData) throws GenericException;

	void deleteMovie(Integer id) throws GenericException;

	MoviesResponseData findMovieById(Integer ideaId) throws GenericException;

}
