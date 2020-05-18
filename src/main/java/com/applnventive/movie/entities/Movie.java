package com.applnventive.movie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.applnventive.movie.models.MoviesRequestData;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false)
	private double rating;

	@Column(nullable = false)
	private String category;

	public static Movie of(MoviesRequestData requestData) {
		Movie movie = new Movie();
		movie.setTitle(requestData.getTitle());
		movie.setCategory(requestData.getCategory());
		movie.setRating(requestData.getRating());
		return movie;
	}

}
