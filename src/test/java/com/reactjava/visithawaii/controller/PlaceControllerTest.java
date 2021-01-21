package com.reactjava.visithawaii.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactjava.visithawaii.model.Place;
import com.reactjava.visithawaii.repo.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PlaceController.class)
class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PlaceRepository placeRepository;

    @Test
    void getAllPlaces() throws Exception {
        //assertThat(placeRepository).isNotNull();
        List<Place> placeList = new ArrayList<>();
        placeList.add(new Place("Honolulu", "Oahu", "", "", "", null, 99.99, true));

        Mockito.when(placeRepository.findAll()).thenReturn(placeList);
        MvcResult mvcResult = mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(placeList);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void getUnavailablePlaces() {
    }

    @Test
    void getPlaceById() {
    }

    @Test
    void createPlace() {
    }

    @Test
    void updatePlace() {
    }

    @Test
    void deletePlace() {
    }

    @Test
    void deleteAllPlaces() {
    }

    @Test
    void findByAvailable() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByUnavailable() {
    }
}