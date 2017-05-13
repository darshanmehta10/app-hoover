package com.test.hoover.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.test.hoover.dto.RequestDto;
import com.test.hoover.dto.ResponseDto;
import com.test.hoover.exception.InvalidRequestException;

public class HooverServiceImplTest {
	
	HooverService service = new HooverServiceImpl();

	@Test(expected = InvalidRequestException.class)
	public void testInvalidRequestRoomSize() throws Exception{
		RequestDto request = new RequestDto();
		service.move(request);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void testInvalidRequestRoomSizeTwo() throws Exception{
		RequestDto request = new RequestDto();
		request.setRoomSize(new ArrayList<>());
		service.move(request);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void testInvalidInstructions() throws Exception{
		RequestDto request = new RequestDto();
		request.setRoomSize(Arrays.asList(5, 5));
		service.move(request);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void testInvalidInstructionsTwo() throws Exception{
		RequestDto request = new RequestDto();
		request.setRoomSize(Arrays.asList(5, 5));
		request.setInstructions("NNWWEESSNNEEWWSSA");
		service.move(request);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void testInvalidPatches() throws Exception{
		RequestDto request = new RequestDto();
		request.setRoomSize(Arrays.asList(5, 5));
		request.setInstructions("NNWWEESSNNEEWWSS");
		List<Integer> patches = Arrays.asList(6,6);
		request.setPatches(Arrays.asList(patches));
		service.move(request);
	}
	
	@Test
	public void testLoop() throws Exception{
		RequestDto request = new RequestDto();
		request.setRoomSize(Arrays.asList(5, 5));
		request.setInstructions("NEWSNEWSNEWSNEWSNEWS");
		request.setPatches(Collections.emptyList());
		request.setCoords(Arrays.asList(1,1));
		ResponseDto response = service.move(request);
		assertEquals(response.getPatches(), 0);
		assertEquals(response.getCoords().size(), 2);
		assertEquals(response.getCoords().get(0).intValue(), 1);
		assertEquals(response.getCoords().get(1).intValue(), 1);
	}
	
	@Test
	public void teseExample() throws Exception {
		RequestDto request = new RequestDto();
		request.setRoomSize(Arrays.asList(5, 5));
		request.setInstructions("NNESEESWNWW");
		request.setPatches(Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2), Arrays.asList(2, 3)));
		request.setCoords(Arrays.asList(1,2));
		ResponseDto response = service.move(request);
		assertEquals(response.getPatches(), 1);
		assertEquals(response.getCoords().size(), 2);
		assertEquals(response.getCoords().get(0).intValue(), 1);
		assertEquals(response.getCoords().get(1).intValue(), 3);
	}
	
}
