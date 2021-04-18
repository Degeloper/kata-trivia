package trivia;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class GameTest {

	private final PrinterMock printerMock = new PrinterMock();

	@Test
	public void caracterizationTest() {
		for (int seed = 1; seed < 10_000; seed++) {
			final String expectedOutput = extractOutput(new Random(seed), new Game());
			final String actualOutput = extractOutput(new Random(seed), new GameBetter(printerMock));
			assertEquals(expectedOutput, actualOutput);
		}
	}

	private String extractOutput(final Random rand, final Game aGame) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (final PrintStream inmemory = new PrintStream(baos)) {
			System.setOut(inmemory);
			playGame(rand, aGame);
		}
		return baos.toString();
	}

	private String extractOutput(final Random rand, final GameBetter aGame) {
		playGame(rand, aGame);
		return printerMock.flushAndReturnOutput();
	}

	private void playGame(final Random rand, final IGame aGame) {
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		boolean notAWinner;
		do {
			aGame.roll(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7)
				notAWinner = aGame.wrongAnswer();
			else
				notAWinner = aGame.wasCorrectlyAnswered();

		} while (notAWinner);
	}
}
