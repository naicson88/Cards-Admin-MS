package com.naicson.yugioh;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminMsApplicationTests {

	@Test
	void contextLoads() {
		String teste = "teste";
		
		  assertNotNull(teste);
	}

}
