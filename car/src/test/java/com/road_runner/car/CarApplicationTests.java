package com.road_runner.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CarApplicationTests {
	
	@Autowired
	private MockMvc mvc;

	@Mock
	private CarRepo carRepo;

	@InjectMocks
	private CarController carController;

	private JacksonTester<Car> jsonCar;

	private JacksonTester<List<Car>> jsonCars;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(carController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getAll() throws Exception{
		Car car1= new Car(1,"civic","www","hello","shdfu his ", 20000);
		Car car2= new Car(1,"civic","www","hello","shdfu his ", 20000);

		List<Car> expectedCars = new ArrayList<>();

		expectedCars.add(car1);
		expectedCars.add(car2);


		when(carRepo.findAll()).thenReturn(expectedCars);

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.get("/cars/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCars.write(expectedCars).getJson()));

	}

	@Test
	public void getCarbyId() throws Exception{
		Car car1= new Car(1,"civic","www","hello","shdfu his ", 20000);
		// Car car2= new Car(1,"civic","www","hello","shdfu his ", 20000);

		// List<Car> expectedCars = new ArrayList<>();

		// expectedCars.add(car1);
		// expectedCars.add(car2);


		when(carRepo.findById(1L)).thenReturn(Optional.of(car1));

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.get("/cars/get/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCar.write(car1).getJson()));

	}

	@Test
	public void postCar() throws Exception{
		Car car1= new Car(1,"civic","www","hello","shdfu his ", 20000);
		// Car car2= new Car(1,"civic","www","hello","shdfu his ", 20000);

		// List<Car> expectedCars = new ArrayList<>();

		// expectedCars.add(car1);
		// expectedCars.add(car2);


		when(carRepo.save(car1)).thenReturn(car1);

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.post("/cars/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCar.write(car1).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
				

	}



}
