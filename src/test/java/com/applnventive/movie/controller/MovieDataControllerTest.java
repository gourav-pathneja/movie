package com.applnventive.movie.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.applnventive.movie.controller.MoviesController;
import com.applnventive.movie.models.MoviesRequestData;
import com.applnventive.movie.models.MoviesResponseData;
import com.applnventive.movie.services.MoviesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
public class MovieDataControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MoviesService movieService;

	@Test
	void test_For_MovieById_Return_MovieId_If_Exists() throws Exception {
		int id = Integer.valueOf(2);
		MoviesResponseData moviesResponseData = new MoviesResponseData(id, "Dance", "action", 3.0);
		when(movieService.findMovieById(id)).thenReturn(moviesResponseData);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result.title").value("Dance"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result.rating").value("3.0"));
	}

	@Test
	public void test_For_Delete_Movie_By_Id() throws Exception {
		doNothing().when(movieService).deleteMovie(Integer.valueOf((1)));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/movies/1");
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testForCreateMovieForGivenMovieData() throws JsonProcessingException, Exception {
		MoviesRequestData request = new MoviesRequestData("krish", "Action", 3.0);
		Integer id = new Integer(2);
		when(movieService.createMovie(request)).thenReturn(id);
		ObjectMapper mapper = new ObjectMapper();
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/movies").content(mapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isCreated()).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").isNumber());
	}

	@Test
	void testForUpdateMovieWithGivenId() throws JsonProcessingException, Exception {
		MoviesRequestData request = new MoviesRequestData("krish", "Action", 3.0);
		doNothing().when(movieService).updateMovie(2, request);
		ObjectMapper mapper = new ObjectMapper();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/api/v1/movies/2").content(mapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(2));
	}

}
