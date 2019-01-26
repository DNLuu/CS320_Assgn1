// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

// This class prompts the user and proccesses input
import java.util.Scanner;

public class Input {
	
	// Prompts user with input string as message. Returns their responding input as a string.
    public static String prompt(String input) {
    	System.out.print(input);
		Scanner scanner = new Scanner(System.in);
		String output = scanner.nextLine();
		return output;
    }
    
    // Prompts user for a route number. Parses the input string, ignoring all non-digit characters
    // and all digits after the first 3 found. Returns 3 digit route number as a string.
    public static String promptForRoute() {
    	String inputLine = prompt("Please enter a route ID: ");
		String routeNumber = "";
		inputLine = inputLine.replaceAll("[^0-9.]", ""); 
		for (int i = 0; i < 3 && i < inputLine.length(); i++) {
			routeNumber += inputLine.charAt(i);
		}
		return routeNumber;
    }
}
