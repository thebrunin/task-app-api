package com.task.api;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskApiApplicationTests {
	@Autowired
	private ModelMapper modelMapper;
	@Test
	void contextLoads() {
	}

}
