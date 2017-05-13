package com.test.hoover.service;

import com.test.hoover.dto.RequestDto;
import com.test.hoover.dto.ResponseDto;

/**
 * 
 * @author Darshan Mehta
 * This interface can be used by any class to perform 'move' operation
 */
public interface HooverService {
	
	/**
	 * Moves the hoover as per the request parameters and returns final position and number of patches swept 
	 * 
	 * @param request the request object
	 * @return response the reponse containing final coordinates and number of places swept
	 * @throws exception the exception if request is invalid or there is an error while moving the hoover
	 */
	public ResponseDto move(RequestDto request) throws Exception;

}
