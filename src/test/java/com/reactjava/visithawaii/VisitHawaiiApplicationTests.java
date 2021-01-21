package com.reactjava.visithawaii;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactjava.visithawaii.repo.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VisitHawaiiApplicationTests {

	@MockBean
	PlaceRepository placeRepository;

	@Test
	void contextLoads() {
		assertThat(placeRepository).isNotNull();
	}



}
