import java.util.ArrayList;
import java.util.Random;

public class KenoBackend {

	// By default total draws is set to 20, amount $1, Won $0
	private static int totalSubDraws = 20, amountPlayed = 1, totalAmountWon = 0, drawCounter = 0;

	// Stores the 20 randomly generated drawings
	private static int[] result = new int[20];

	// Stores selected nodes
	private static ArrayList<String> userInput = new ArrayList<String>();

	// Stores matched outcomes
	private static ArrayList<String> list = new ArrayList<String>();

	// Compares no. of spots with total selected buttons
	static boolean checkSpots(String inputText) {
		int nNodes = userInput.size();

		if ((inputText == "1" && nNodes == 1) || (inputText == "4" && nNodes == 4) || (inputText == "8" && nNodes == 8)
				|| (inputText == "10" && nNodes == 10)) {
			return true;
		}

		return false;
	}

	// Adds user input or random input to userInput list
	static void storeSelectedSpots(String text, boolean buttonStatus) {
		if (buttonStatus == true) {
			// add
			userInput.add(text);
		} else {
			// remove
			userInput.remove(text);
		}
	}

	// Returns userInput list size which determines no. of selected spots
	static int getTotalSelectedSpots() {
		return userInput.size();
	}

	// 20 sub-draw = 1 draw, 40 sub-draw = 2 draw, ...
	static void setNoOfDraws(int value) {
		totalSubDraws = value;
	}

	// Decrement sub-draws by 1, 1 drawing completes
	static void decrementNoOfDraws() {
		totalSubDraws--;
	}

	// total sub-draws remaining
	static int getNoOfDraws() {
		return totalSubDraws;
	}

	// Returns which Draw is executing
	static int getCurrentDraw() {
		return drawCounter;
	}

	// increments drawCounter when 1 draw completes a.k.a 20 sub-draws
	static void nextDraw() {
		drawCounter++;
	}

	// Bet
	static void setAmount(int value) {
		amountPlayed = value;
	}

	// returns user's bet
	static String getUserAmount() {
		return Integer.toString(amountPlayed);
	}

	// Random draw execution, 20 sub-draws
	static void drawExecute() {

		int[] numbers = new int[80];

		for (int i = 0; i < 80; i++) {
			numbers[i] = i + 1;
		}

		Random rand = new Random();

		// Randomly swap array values
		for (int i = 0; i < numbers.length; i++) {
			int index = rand.nextInt(numbers.length);
			int temp = numbers[index];
			numbers[index] = numbers[i];
			numbers[i] = temp;
		}

		for (int i = 0; i < 20; i++) {
			result[i] = numbers[i];
		}

	}

	/*
	 * Returns the draw result, Example result[0] returns the first sub-draw value
	 * 20 sub-draws are stored in the array result
	 */
	static String getDrawResult(int index) {
		return Integer.toString(result[index]);
	}

	// Resets the game
	static void resetGame() {
		totalSubDraws = 20;
		amountPlayed = 1;
		userInput.clear();
		list.clear();
		drawCounter = 0;
		totalAmountWon = 0;
	}

	// Only the matched list cleared, rest remains the same
	static void resetDraw() {
		list.clear();
	}

	// Returns amount won by the user in a single draw
	static String getAmountResult() {
		String out;

		for (int i = 0; i < 20; i++) {
			list.add(i, Integer.toString(result[i]));
		}
		list.retainAll(userInput);

		out = KenoResult.gameChart(userInput.size(), list.size(), amountPlayed);
		totalAmountWon += Integer.parseInt(out);
		return out;
	}

	// Random input for user
	static void setRandom(String ptext) {
		int[] rnumbers = new int[80];

		userInput.clear();

		for (int i = 0; i < 80; i++) {
			rnumbers[i] = i + 1;
		}

		Random rand = new Random();

		// Randomly swap array values
		for (int i = 0; i < rnumbers.length; i++) {
			int index = rand.nextInt(rnumbers.length);
			int temp = rnumbers[index];
			rnumbers[index] = rnumbers[i];
			rnumbers[i] = temp;
		}

		for (int i = 0; i < Integer.parseInt(ptext); i++) {
			userInput.add(i, Integer.toString(rnumbers[i]));
		}

	}

	// Returns matched items as a String
	static String getMatchedList() {

		if (list.size() == 0) {
			return "NILL";
		}
		StringBuilder listString = new StringBuilder();

		for (String s : list)
			listString.append(s + ", ");

		return listString.toString();
	}

	// Returns chosen or random input as a String
	static String getSelectedList() {
		StringBuilder listString = new StringBuilder();

		for (String s : userInput)
			listString.append(s + ", ");

		return listString.toString();
	}

	// Returns total matched items in a single draw
	static String getMatchedSize() {
		return Integer.toString(list.size());
	}

	// Returns total amount won throughout the game
	static String getTotalAmountWon() {
		return Integer.toString(totalAmountWon);
	}

}
