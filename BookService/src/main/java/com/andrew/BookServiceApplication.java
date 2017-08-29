package com.andrew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration;

@EnableDiscoveryClient
@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}
	
	@Bean
	public WatchFilter getWatchFilter() {
		return new WatchFilter();
	}
	
}
