package com.rationalcoding.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rationalcoding.customdatastractures.TooManyConcurrentRequestsException;
import com.rationalcoding.dto.MovieInfoDTO;
import com.rationalcoding.services.MovieDataServicesImpl;

@Controller
@RequestMapping("getdata")
public class GetMovieRatings {
	private static final Logger logger = Logger.getLogger(GetMovieRatings.class);
	
	@Autowired
	private MovieDataServicesImpl movieDataServices;

	@RequestMapping(value = "/{movieName}", method = RequestMethod.GET)
	@ResponseBody
	public MovieInfoDTO getData(@PathVariable String movieName) {
		logger.info("Request being processed for movie "+movieName);
		MovieInfoDTO movieInfo = movieDataServices.getMovieRatings(movieName);
		return movieInfo;
	}	
	
	@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Too many requests")  
  @ExceptionHandler(TooManyConcurrentRequestsException.class)
  public void forbidden() {
    // Nothing to do
  }

}
