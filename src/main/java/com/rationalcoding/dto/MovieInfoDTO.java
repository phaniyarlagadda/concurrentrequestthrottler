package com.rationalcoding.dto;

public class MovieInfoDTO {
	
	private String movieName;
	private String imdbRating;
	private String rottenTomateScore;
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getRottenTomateScore() {
		return rottenTomateScore;
	}
	public void setRottenTomateScore(String rottenTomateScore) {
		this.rottenTomateScore = rottenTomateScore;
	}

}
