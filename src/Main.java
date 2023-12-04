import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min =0;
        int max= 100;
        boolean playAgain=true;

        while(playAgain){ //while loop
            int correctNumber = random.nextInt(max+1); // Generate a random number between 0 och 100

            int numberOfTries = 0;
            boolean hasWon = true;
            System.out.println("Guess a number between 0 and 100");
            System.out.println("What is your guess?");

            while(hasWon){  //while loop
                int guess = numberGuess();//call method
                numberOfTries++;

                String result = evaluateGuess(guess, correctNumber);//call method
                System.out.println(result);

                if(result.equals("Correct!")){

                    System.out.println("You needed " + numberOfTries + " to find the correct number!");
                    hasWon = false;
                }
            }
            System.out.println("Want to play again? (Yes/No)");
            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
    }
    public static int numberGuess() {          //method definition
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String evaluateGuess(int guess, int correctNumber) {   //method definition
        if (guess < correctNumber) {
            return "Wrong! My number is bigger!";//return value
        } else if (guess > correctNumber) {
            return "Wrong! My number is smaller!";//return value
        } else {
            return "Correct!"; //return value
        }
    }
}
