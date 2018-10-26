package edu.iua.calculator;

import org.junit.jupiter.api.*;


public class StandardTests {

	@BeforeAll //Antes de todos los tests
	static void initAll() {
		
	}
	
	@BeforeEach //Antes de cada test
	void init() {
		
	}
	
	@Test
	void suceedingTest() {
	
	}
	
	@Test
	void failingTest() {
		
	}
	
	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		//not executed
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@AfterAll
	void tearDownAll() {
		
	}
	
}
