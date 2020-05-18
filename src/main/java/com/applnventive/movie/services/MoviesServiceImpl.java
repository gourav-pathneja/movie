package com.applnventive.movie.services;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.applnventive.movie.entities.Movie;
import com.applnventive.movie.exceptions.GenericException;
import com.applnventive.movie.models.MoviesRequestData;
import com.applnventive.movie.models.MoviesResponseData;
import com.applnventive.movie.repositories.MoviesRepository;

@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesRepository;

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		return moviesRepository.findAll(pageable);
	}

	@Override
	public Integer createMovie(MoviesRequestData moviesRequestData) {
		Movie movies = new Movie();
		movies.setTitle(moviesRequestData.getTitle());
		movies.setCategory(moviesRequestData.getCategory());
		movies.setRating(moviesRequestData.getRating());
		Movie movie = moviesRepository.save(movies);

		return movie.getId();
	}

	@Override
	public void updateMovie(Integer id, MoviesRequestData requestData) throws GenericException {
		Movie movies = moviesRepository.findById(id)
				.orElseThrow((Supplier<GenericException>) () -> new GenericException(HttpStatus.NOT_FOUND));
		movies.setTitle(requestData.getTitle());
		movies.setCategory(requestData.getCategory());
		movies.setRating(requestData.getRating());
		moviesRepository.save(movies);
	}

	@Override
	public void deleteMovie(Integer id) throws GenericException {
		Movie movies = moviesRepository.findById(id)
				.orElseThrow((Supplier<GenericException>) () -> new GenericException(HttpStatus.NOT_FOUND));
		moviesRepository.delete(movies);
	}

	@Override
	public MoviesResponseData findMovieById(Integer ideaId) throws GenericException {
		Movie movie = moviesRepository.findById(ideaId)
				.orElseThrow((Supplier<GenericException>) () -> new GenericException(HttpStatus.NOT_FOUND));
		return new MoviesResponseData(movie.getId(), movie.getTitle(), movie.getCategory(), movie.getRating());
	}

}
