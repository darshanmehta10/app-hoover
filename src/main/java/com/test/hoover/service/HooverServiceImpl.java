package com.test.hoover.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.test.hoover.dto.RequestDto;
import com.test.hoover.dto.ResponseDto;
import com.test.hoover.exception.InvalidRequestException;

/**
 * 
 * @author Darshan Mehta
 * This class is a part of service layer of the application. It implements move method of HooverService interface which is used by controller.
 */
@Component
public class HooverServiceImpl implements HooverService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HooverServiceImpl.class);

	/**
	 * Moves the hoover as per the request parameters and returns final position and number of patches swept 
	 * 
	 * @param request the request object
	 * @return response the reponse containing final coordinates and number of places swept
	 * @throws exception the exception if request is invalid or there is an error while moving the hoover
	 */
	@Override
	public ResponseDto move(RequestDto request) throws Exception {
		validate(request);
		List<Integer> coords = new ArrayList<>();
		int patches = 0;
		int[][] grid = getGrid(request);
		//Now, start hoovering
		
		//Convert x and y coordinates to array dimensions
		int posX = grid.length - 1 - request.getCoords().get(1);
		int posY = request.getCoords().get(0);
		
		//Check whether starting point is correct
		if(posX >= grid.length || posY >= grid[posX].length){
			throw new InvalidRequestException("Invalid starting point");
		}
		
		//Increment the patches if start position is a patch
		if(isPatch(grid, posX, posY)){
			patches++;
		}
		
		for(char c : request.getInstructions().toCharArray()){
			//Handle different cases and increment/decrement coordinates accordingly
			switch(c){
			case 'N' : 
				if(posX != 0){
					posX--;
					patches = sweep(grid, posX, posY, patches);
				}
				break;
				
			case 'E' :
				if(posY < grid[posX].length){
					posY++;
					patches = sweep(grid, posX, posY, patches);
				}
				break;
				
			case 'W' :
				if(posY != 0){
					posY--;
					patches = sweep(grid, posX, posY, patches);
				}
				break;
				
			case 'S' : 
				if(posX < grid.length){
					posX++;
					patches = sweep(grid, posX, posY, patches);
				}
				break;
				
				default:
					break;
			}
		}
		//Once hoovering is done, convert array dimensions back to x and y coordinates
		coords.add(posY);
		coords.add(grid.length - 1 - posX);
		return new ResponseDto(coords, patches);
	}
	
	/**
	 * Checks whether given location is a patch of dirt
	 * @param grid the grid array
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if there is a patch of dirt on a given position
	 */
	private boolean isPatch(int[][] grid, int x, int y){
		return grid[x][y] == 1;
	}
	
	/**
	 * Sweeps the patch of dirt if the hoover is standing on a patch of dirt
	 * @param grid the grid array
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param patches the patch count
	 * @return number of patches swept
	 */
	private int sweep(int[][] grid, int x, int y, int patches){
		int result = patches;
		if(grid[x][y] == 1){
			grid[x][y] = 0;
			result = patches + 1;
		}
		return result;
	}
	
	/**
	 * Constructs grid array from request and sets the values for patches of dirt
	 * @param request the request object
	 * @return the 2D grid array
	 * @throws Exception error if any position is out of bounds
	 */
	private int[][] getGrid(RequestDto request) throws Exception{
		int[][] grid = new int[request.getRoomSize().get(0)][request.getRoomSize().get(1)];
		int gridLength = grid.length;
		try{
			request.getPatches()
			.forEach(p -> {
				int x = p.get(0);
				int y = p.get(1);
				grid[gridLength - 1 - y][x] = 1; 
			});
		}catch(Exception e){
			LOGGER.error("Error while initialising grid", e);
			throw new InvalidRequestException("Invalid location of patches");
		}
		return grid;
	}
	
	/**
	 * Validates request and throws exception if anything is not valid
	 * @param request the request object
	 * @throws Exception the exception object
	 */
	private void validate(RequestDto request) throws Exception {
		String error = null;
		
		if(null == request.getRoomSize() 
				|| request.getRoomSize().size() != 2
				|| null == request.getCoords()
				|| request.getCoords().size() != 2){
			error = "Invalid room size and/or coordinates";
		}else if(StringUtils.isEmpty(request.getInstructions())
				|| !request.getInstructions().matches("^[NEWS]+$")){
			error = "Invalid Instructions";
		}
		
		if(null != error){
			throw new InvalidRequestException(error);
		}
	}

}
