package com.applnventive.movie.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.applnventive.movie.entities.Movie;
import com.applnventive.movie.exceptions.GenericException;
import com.applnventive.movie.models.MoviesRequestData;
import com.applnventive.movie.models.MoviesResponseData;
import com.applnventive.movie.repositories.MoviesRepository;
import com.applnventive.movie.services.MoviesServiceImpl;

@RunWith(PowerMockRunner.class)
@SpringBootTest
class MovieServiceImplTest {

	@InjectMocks
	private MoviesServiceImpl movieServiceImpl;

	@Mock
	private MoviesRepository moviesRepository;

	@Rule
	private ExpectedException expectedExceptionRule = ExpectedException.none();

	public MovieServiceImplTest() {

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteMovieThrowsGenericException() {
		expectedExceptionRule.expect(GenericException.class);
		when(moviesRepository.findById(12)).thenReturn(Optional.empty());
		movieServiceImpl.deleteMovie(12);
	}

	@Test
	public void deleteMovie() {
		Movie movie = new Movie(12, "lovestory", 2.9, "good");
		when(moviesRepository.findById(12)).thenReturn(Optional.of(movie));
		movieServiceImpl.deleteMovie(12);
		verify(moviesRepository).delete(movie);
	}

	@Test
	public void findMovieByIdThrowsGenericException() {
		expectedExceptionRule.expect(GenericException.class);
		when(moviesRepository.findById(12)).thenReturn(Optional.empty());
		movieServiceImpl.findMovieById(12);
	}

	@Test
	public void findMovieById() {
		MoviesResponseData response = new MoviesResponseData(12, "Action movie", "dil", 4.4);
		Movie movie = new Movie(12, "Action movie", 4.4, "dil");
		when(moviesRepository.findById(12)).thenReturn(Optional.of(movie));
		assertEquals(response.getId(), movieServiceImpl.findMovieById(12).getId());
	}

	@Test
	public void updateMovieThrowsGenericException() {
		expectedExceptionRule.expect(GenericException.class);
		MoviesRequestData requestData = new MoviesRequestData("Action movie", "dil", 4.4);
		when(moviesRepository.findById(12)).thenReturn(Optional.empty());
		movieServiceImpl.updateMovie(12, requestData);
	}

	@Test
	public void updateMovie() {
		MoviesRequestData requestData = new MoviesRequestData("Action movie", "dil", 4.4);
		Movie movie = Movie.of(requestData);
		when(moviesRepository.findById(12)).thenReturn(Optional.of(movie));
		movieServiceImpl.updateMovie(12, requestData);
		verify(moviesRepository).save(movie);
	}

}