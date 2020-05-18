package com.applnventive.movie.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoviesRequestData {

	@NotEmpty
	private String title;

	@NotEmpty
	private String category;

	@NotNull
	@DecimalMin("0.5")
	@DecimalMax("5.0")
	private double rating;

}
