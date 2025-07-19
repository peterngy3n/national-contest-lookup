package com.timothy.national_contest_lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NationalContestLookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationalContestLookupApplication.class, args);
	}

}
