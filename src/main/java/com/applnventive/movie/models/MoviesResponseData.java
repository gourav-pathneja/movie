package com.applnventive.movie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoviesResponseData {

	private Integer id;
	private String title;
	private String category;
	private double rating;

}
