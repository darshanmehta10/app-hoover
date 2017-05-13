package com.test.hoover.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.hoover.dto.RequestDto;
import com.test.hoover.dto.ResponseDto;
import com.test.hoover.exception.InvalidRequestException;
import com.test.hoover.service.HooverService;

/**
 * 
 * @author Darshan Mehta
 * This class is a part of controller layer. It gets the API request, calls service method
 * and returns appropriate response
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/move")
public class HooverController {
	
	@Autowired
	private HooverService hooverService;

	/**
	 * Calls 'move' method of service class and returns the response
	 * @param request the request object
	 * @return the response containing number of patches and final position. Error if anything 
	 * goes wrong
	 */
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Object> move(@RequestBody RequestDto request){
		try{
			ResponseDto moveResponse = hooverService.move(request);
			return new ResponseEntity<Object>(moveResponse, HttpStatus.OK);
		}catch(InvalidRequestException ire){
			Map<String, String> errorData = new HashMap<>();
			errorData.put("error", ire.getMessage());
			return new ResponseEntity<Object>(errorData, HttpStatus.BAD_REQUEST);
		}catch(Exception e){
			Map<String, String> errorData = new HashMap<>();
			errorData.put("error", e.getMessage());
			return new ResponseEntity<Object>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
