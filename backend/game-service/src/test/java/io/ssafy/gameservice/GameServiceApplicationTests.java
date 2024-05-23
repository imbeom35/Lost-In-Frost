package io.ssafy.gameservice;

import io.ssafy.gameservice.game.draw.service.DrawServiceImpl;
import io.ssafy.gameservice.repository.MemberRepository;
import io.ssafy.gameservice.game.costume.repository.CostumeRepository;
import io.ssafy.gameservice.repository.MyCostumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GameServiceApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private CostumeRepository costumeRepository;

	@Autowired
	private MyCostumeRepository myCostumeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetDrawResult() {
		RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("SecureRandom");
		RandomGenerator randomGenerator = factory.create();

		double randomNumber = randomGenerator.nextDouble(0.1,1.0);
		double[] probabilities = {0.7, 0.18, 0.1, 0.02};
		double cumulativeProbability = 0.0;

		int selectedValue = -1;

		for (int i = 0; i < probabilities.length; i++) {
			cumulativeProbability += probabilities[i];
			if (randomNumber <= cumulativeProbability) {
				selectedValue = i;
				break;
			}
		}

		String result = switch (selectedValue) {
			case 0 -> "normal";
			case 1 -> "epic";
			case 2 -> "unique";
			case 3 -> "legendary";
			default -> null;
		};

		assertEquals("normal", result);

	}

}