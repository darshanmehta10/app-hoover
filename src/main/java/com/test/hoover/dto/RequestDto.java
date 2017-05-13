package com.test.hoover.dto;

import java.util.List;

/**
 * 
 * @author Darshan Mehta
 * Data Transfer Class, used to deserialise request
 */
public class RequestDto {

	private List<Integer> roomSize;
	private List<Integer> coords;
	private List<List<Integer>> patches;
	private String instructions;
	
	public List<Integer> getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(List<Integer> roomSize) {
		this.roomSize = roomSize;
	}
	public List<Integer> getCoords() {
		return coords;
	}
	public void setCoords(List<Integer> coords) {
		this.coords = coords;
	}
	public List<List<Integer>> getPatches() {
		return patches;
	}
	public void setPatches(List<List<Integer>> patches) {
		this.patches = patches;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}