package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.model.EnrolleeInfo;
import com.cognixia.jump.service.EnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EnrolleeController.class)
class EnrolleeControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	@MockBean
	private EnrolleeService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllEnrollees() throws Exception {

		String uri = STARTING_URI + "/enrollees";

		List<Enrollee> allEnrollees = Arrays.asList(new Enrollee(new ObjectId(), new ArrayList<EnrolleeInfo>(), true));

		when(service.getAllEnrollees()).thenReturn(allEnrollees);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testGetEnrollee() throws Exception {

		String uri = STARTING_URI + "/enrollees";

		ObjectId id = new ObjectId();
		Enrollee enrollee = new Enrollee(id, new ArrayList<EnrolleeInfo>(), true);

		when(service.getEnrolleeById(id)).thenReturn(enrollee);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testAddEnrollee() throws Exception {

		String uri = STARTING_URI + "/enrollee/add";

		Enrollee enrollee = new Enrollee(new ObjectId(), new ArrayList<EnrolleeInfo>(), true);

		when(service.addEnrollee(Mockito.any(Enrollee.class))).thenReturn(enrollee);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(enrollee)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.information").value(enrollee.getInformation()))
				.andExpect(jsonPath("$.active").value(enrollee.isActive())).andExpect(status().isOk());

		verify(service, times(1)).addEnrollee(Mockito.any(Enrollee.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	void testUpdateEnrollee() throws Exception {

		ObjectId id = new ObjectId();
		String uri = STARTING_URI + "/enrollee/update/" + id;

		Enrollee enrollee = new Enrollee(id, new ArrayList<EnrolleeInfo>(), true);

		when(service.getEnrolleeById(id)).thenReturn(enrollee);
		when(service.addEnrollee(Mockito.any(Enrollee.class))).thenReturn(enrollee);

		mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(enrollee)))
				.andExpect(status().isOk()).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testDeleteEnrollee() throws Exception {

		ObjectId id = new ObjectId();
		String uri = STARTING_URI + "/enrollee/delete/" + id;

		Enrollee enrollee = new Enrollee(id, new ArrayList<EnrolleeInfo>(), true);

		when(service.getEnrolleeById(id)).thenReturn(enrollee);
		when(service.getEnrolleeById(id)).thenReturn(enrollee);

		mockMvc.perform(delete(uri, id)).andDo(print()).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
