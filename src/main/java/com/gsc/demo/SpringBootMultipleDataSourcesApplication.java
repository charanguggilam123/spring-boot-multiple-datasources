package com.gsc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gsc.demo.client.repo.ClientRepo;
import com.gsc.demo.domain.Client;
import com.gsc.demo.domain.TODO;
import com.gsc.demo.todo.repo.TodoRepo;

@SpringBootApplication
public class SpringBootMultipleDataSourcesApplication implements CommandLineRunner {
//	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private TodoRepo todoRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDataSourcesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Client cl = new  Client();
		cl.setUsername("sa");
		cl.setPassword("sa");
		
		clientRepo.save(cl);
		
		TODO td = new TODO();
		td.setData("soething");
		todoRepo.save(td);
		
	}

}
