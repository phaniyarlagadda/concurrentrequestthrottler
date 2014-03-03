package com.rationalcoding.services;

import org.springframework.stereotype.Service;

import com.rationalcoding.dto.MovieInfoDTO;

@Service
public class MovieDataServicesImpl {
	
	public MovieInfoDTO getMovieRatings(String movieName){
		MovieInfoDTO movieInfoDto = new MovieInfoDTO();
		movieInfoDto.setMovieName(movieName);
		if(movieName.equals("ironman")){
			movieInfoDto.setImdbRating("7.8");
			movieInfoDto.setRottenTomateScore("79%");
		}else if(movieName.equals("darkknight")){
			movieInfoDto.setImdbRating("9.3");
			movieInfoDto.setRottenTomateScore("88%");
		}else if(movieName.equals("superman")){
			movieInfoDto.setImdbRating("6.7");
			movieInfoDto.setRottenTomateScore("55%");
		}
		
		// sleep has been introduced to simulate the tasks with many calculations
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return movieInfoDto;
	}

}
