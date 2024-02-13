import java.util.Scanner;

public class ShapePrintProject {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String answer;
		do {
			//Display menu and prompt user for their choice 
			answer = getUserAnswer(sc);
			//Different cases based on user's input
			switch(answer) {
			case "1":
				option1(sc);
				break;
			case "2":
				option2(sc);
				break;
			case "3": 
				option3(sc);
				break;
			}	
		} while(!(answer.equals("4")));
		System.out.println("Program terminated");
		sc.close();
	}
	public static String getUserAnswer(Scanner sc) {
		String answer;
		do {
			System.out.printf("Options: \nItem 1: Traingle patterns\nItem 2: Pyramid patterns\nItem 3: Diamond patterns\nItem 4: Exit\n");
			System.out.print("Enter your menu choice: ");
			answer = sc.nextLine().strip();
		//Check if valid input(greater or lesser than given menu or not a length of 1)
			if(answer.charAt(0) > '4' || answer.charAt(0) < '1' || answer.length() != 1) {
				System.out.println("Invalid menu choice. Please enter a number between 1 and 4.");
			}
		} while(answer.charAt(0) > '4' || answer.charAt(0) < '1' || answer.length() != 1);
		return answer;
	}
	public static int prompt(Scanner sc){
		String answer = "";
		int answerInt;
		do {
			System.out.print("Enter a size for your triangle between 1 and 9: ");
			answer = sc.nextLine().strip();
			if(answer.length() > 1 || answer.charAt(0) > '9' || answer.charAt(0) < '1') {
				System.out.println("Invalid answer. Please enter one number between 1 and 9.");
			}
			answerInt = (int) answer.charAt(0) - '0';
		} while(answerInt > 9 || answerInt < 1);
		return answerInt;
	}
	public static void option1(Scanner sc) {
		int input = prompt(sc);
		System.out.print("1A)\n\n");
		printFirstTriangle(input);
		System.out.print("1B)\n\n");
		printSecondTriangle(input);
		System.out.print("1C)\n\n");
		printThirdTriangle(input);
		System.out.print("1D)\n\n");
		printFourthTriangle(input);
	}
	public static void option2(Scanner sc) {
		int input = prompt(sc);
		System.out.print("2A)\n\n");
		printFirstPyramid(input);
		System.out.print("2B)\n\n");
		printSecondPyramid(input);
		System.out.print("2C)\n\n");
		printThirdPyramid(input);
		System.out.print("2D)\n\n");
		printFourthPyramid(input);
	}
	public static void option3(Scanner sc) {
		int input = prompt(sc);
		System.out.println("3A)\n\n");
		printFirstDiamond(input);
		System.out.println("3B)\n\n");
		printSecondDiamond(input);
		System.out.println("3C)\n\n");
		printThirdDiamond(input);
		System.out.println("3D)\n\n");
		printFourthDiamond(input);
	}
	public static void printPattern(int input, boolean rightAligned, boolean ascending, boolean numbersReversed, boolean pyramid, boolean differentPyramidPattern, boolean differentPyramidPattern2, boolean topHalfOfDiamond) {
		//Storing in variables rather than ram everything in the loop conditions (more readable this way)
		int iStartingIndex = ascending ? 1 : input;
		int iIncrement = ascending ? 1 : -1;	    
		int jStartingIndex = pyramid ? 0 : 1;
		//Create a first loop to go through each number in the range, we manipulate the conditions of this loop depending on if the triangle is ascending
		for(int i = iStartingIndex; (ascending ? i <= input: i >= 1); i += iIncrement) {//conditional is based on the ascending parameter
			//Spaces are determined based on if it's right or left aligned
			int spaces = rightAligned ? input - i : 0; //Spaces are chosen based on rightAlgined parameter
			//A nested loop to print the spaces based on the alignment and shape
			for(int j = jStartingIndex; (pyramid ? j < input - i : j <= spaces); j++ ) {//conditional is based on pyramid parameter
				System.out.print("  ");
			}
			int kStartingIndex = differentPyramidPattern2 ? i : 1;
			int kIncrement = differentPyramidPattern2 ? -1 : 1;
			//Another nestled loop to print the correct amount of the number given
			//k conditional is decided by pyramid pattern and by if it's a pyramid or not
			for(int k = kStartingIndex; (differentPyramidPattern2 ? k >= 1  : (pyramid ? k <= 2 * i - 1: k <= i)); k += kIncrement) {
				//If its the top half of a diamond, skip the last line so you can print the bottom half without repeating the middle
				if(topHalfOfDiamond && i == input) {
					break;
				}
				//Print correct number for second different pyramid pattern
				if(differentPyramidPattern2) {
					System.out.printf("%d ",numbersReversed? input - k + 1:  k);
				}
				//If it's the alternate pyramid pattern, print accordingly
				else if(differentPyramidPattern) {
					System.out.printf("%d ", (k <= i ? k : (2 * i - k)));
				}
				//If not any of the prior conditions, print normally
				else {
					System.out.printf("%d ", numbersReversed? input - i + 1 : i);
				}
			}
			if(differentPyramidPattern2) {
				//Check if this loop is
				if(topHalfOfDiamond && input == i) {
					break;
				}
				//loop starting from 2 needed to print second half of pyramid 
				for(int l = 2; l <= i; l++) {
					System.out.printf("%d ", numbersReversed ? input - l + 1 : l);
				}
			}
			//Don't print extra white space if about to print the second half of the diamond
			if(topHalfOfDiamond ? i < input : i <= input) {
				System.out.println();
			}
		}
		//If not the top half of the diamond, print a whitespace (more for visual purposes
		//so the next menu isn't right under the last diamond
		if(!topHalfOfDiamond) {
			System.out.println();
		}
	}
	//Wrapper functions
	public static void printFirstTriangle(int input) {
		printPattern(input, false, true, false, false, false, false, false);
	}
	public static void printSecondTriangle(int input) {
		printPattern(input, true, true, false, false, false, false, false);
	}
	public static void printThirdTriangle(int input) {
		printPattern(input, false, false, false, false, false, false, false);
	}
	public static void printFourthTriangle(int input) {
		printPattern(input, true, true, true, false, false, false, false);
	}
	public static void printFirstPyramid(int input) {
		printPattern(input, true, true, false, true, false, false, false);
	}
	public static void printSecondPyramid(int input) {
		printPattern(input, true, true, false, true, true, false,false);
	}
	public static void printThirdPyramid(int input) {
		printPattern(input, true, false, false, true, false, false, false);
	}
	public static void printFourthPyramid(int input) {
		printPattern(input, true, false, false, true, true, false, false);
	}
	public static void printFirstDiamond(int input) {
		printPattern(input, true, true, false, true, true, false, true);
		printPattern(input, true, false, false, true, true,false, false);
	}
	public static void printSecondDiamond(int input) {
		printPattern(input, true, true, true, true, false, true, true);
		printPattern(input, true, false, true, true, false, true, false);
	}
	public static void printThirdDiamond(int input) {
		printPattern(input, true, true, false, true, false, false, true);
		printPattern(input, true, false, false, true, false, false, false);
	}
	public static void printFourthDiamond(int input) {
		printPattern(input, true, true, false, true, false, true, true);
		printPattern(input, true, false, false, true, false, true, false);
	}
	
}