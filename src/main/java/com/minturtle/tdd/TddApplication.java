package com.minturtle.tdd;

import com.minturtle.tdd.baseball.NumberBaseBall;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TddApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddApplication.class, args);
	}



	@RequiredArgsConstructor
	public class MyCommandLineRunner implements CommandLineRunner{

		private final NumberBaseBall baseballGame;

		@Override
		public void run(String... args) throws Exception {

		}
	}
}
