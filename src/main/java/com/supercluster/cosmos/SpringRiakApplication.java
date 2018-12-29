package com.supercluster.cosmos;

import java.util.WeakHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories(mapType = WeakHashMap.class)
public class SpringRiakApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringRiakApplication.class, args);
	}
	
}

