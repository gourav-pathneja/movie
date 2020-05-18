package com.applnventive.movie.controller;

import static com.applnventive.movie.constants.RestConstants.APP_INVENTIVE_API;
import static com.applnventive.movie.constants.RestConstants.moviesDetails.MOVIES;
import static com.applnventive.movie.constants.RestConstants.moviesDetails.MOVIES_BY_ID;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.applnventive.movie.exceptions.GenericException;
import com.applnventive.movie.models.BaseResponse;
import com.applnventive.movie.models.MoviesRequestData;
import com.applnventive.movie.models.MoviesResponseData;
import com.applnventive.movie.services.MoviesService;

@RestController
@RequestMapping(value = APP_INVENTIVE_API,produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {

	@Autowired
	MoviesService moviesService;

	@GetMapping(MOVIES)
	public ResponseEntity<BaseResponse<Page<MoviesResponseData>>> getAllMovies(
			@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize)
			throws GenericException {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<MoviesResponseData> collect = moviesService.findAll(pageable).stream()
				.map(obj -> new MoviesResponseData(obj.getId(),obj.getTitle(),obj.getCategory(),obj.getRating())).collect(Collectors.toList());
		Page<MoviesResponseData> moviesAllData = new PageImpl<MoviesResponseData>(collect);
		return new ResponseEntity<>(new BaseResponse<Page<MoviesResponseData>>(moviesAllData), HttpStatus.OK);
	}

	@PostMapping(MOVIES)
	@Transactional
	public ResponseEntity<BaseResponse<Integer>> createMovie(@Valid @RequestBody MoviesRequestData data)
			throws GenericException, IOException {
		return new ResponseEntity<>(new BaseResponse<>(moviesService.createMovie(data)), HttpStatus.CREATED);
	}

	@PutMapping(MOVIES_BY_ID)
	@Transactional
	public ResponseEntity<BaseResponse<Integer>> updateIdea(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody MoviesRequestData data) throws GenericException, IOException {
		moviesService.updateMovie(id, data);
		return new ResponseEntity<>(new BaseResponse<>(id), HttpStatus.OK);
	}

	@DeleteMapping(MOVIES_BY_ID)
	@Transactional
	public ResponseEntity<BaseResponse<Integer>> delete(@PathVariable("id") final Integer id) {
		moviesService.deleteMovie(id);
		return new ResponseEntity<>(new BaseResponse<>(id), HttpStatus.OK);
	}

	@GetMapping(MOVIES_BY_ID)
	public ResponseEntity<BaseResponse<MoviesResponseData>> getIdeaById(@PathVariable(value = "id") Integer id)
			throws GenericException, IOException {
		return new ResponseEntity<>(new BaseResponse<>(moviesService.findMovieById(id)), HttpStatus.OK);
	}

}
