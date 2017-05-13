package com.test.hoover.dto;

import java.util.List;

/**
 * 
 * @author Darshan Mehta
 * Data Transfer Class used to serialise the resposne
 */
public class ResponseDto {
	
	private List<Integer> coords;
	private int patches;

	public ResponseDto(List<Integer> coords, int patches){
		this.coords = coords;
		this.patches = patches;
	}
	
	public List<Integer> getCoords() {
		return coords;
	}
	public void setCoords(List<Integer> coords) {
		this.coords = coords;
	}
	public int getPatches() {
		return patches;
	}
	public void setPatches(int patches) {
		this.patches = patches;
	}
}