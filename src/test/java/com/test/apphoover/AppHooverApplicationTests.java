package com.test.apphoover;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.hoover.HooverApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HooverApplication.class)
public class AppHooverApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testHooverMovement() throws Exception{
		mockMvc.perform(
	            MockMvcRequestBuilders.post("/move")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(new String("{\"roomSize\" : [5, 5],\"coords\" : [1, 2],\"patches\" : [  [1, 0],  [2, 2],  [2, 3]],\"instructions\" : \"NNESEESWNWW\"}"))
	        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.patches").value(1));
	}

}
