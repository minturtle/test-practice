package com.minturtle.tdd;

import com.minturtle.tdd.baseball.NumberBaseBall;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TddApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddApplication.class, args);
	}



	@RequiredArgsConstructor
	@Component
	public class MyCommandLineRunner implements CommandLineRunner{

		private final NumberBaseBall baseballGame;

		@Override
		public void run(String... args) throws Exception {
			final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


			System.out.println("----- game start ------");
			while(true){
				System.out.println("input 3 numbers");
				List<Integer> integers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toList();

				NumberBaseBall.Result result = baseballGame.play(integers.get(0), integers.get(1), integers.get(2));
				System.out.println(result);

				if(result.isGameEnd()){
					System.out.printf("game end, trial : %d\n", baseballGame.getTrial());
					System.out.println("1. restart 2.end");

					int gameEnd = Integer.parseInt(br.readLine());
					if(gameEnd == 1){ baseballGame.restart();}
					else break;
				}

			}

		}
	}
}
