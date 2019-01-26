// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

// This application parses the King County metro Community Transit website and extracts a list of 
// destinations their corresponding bus routes, and the bus routes' corresponding stops. The 
// application then allows the user to query the information they wish to see from this data.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class BusSchedule {
    public static void main(String[] args) throws Exception {
    	URLConnection transitSchedules = new URL("https://www.communitytransit.org/busservice/schedules/").openConnection();
        transitSchedules.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) "
				+ "AppleWebKit/537.11 (KHTML, like Gecko) "
				+ "Chrome/23.0.1271.95 Safari/537.11"); 
        
        BufferedReader input = new BufferedReader(new InputStreamReader(transitSchedules.getInputStream()));
        String textLine = "";
        String text = ""; 
        
        while ((textLine = input.readLine()) != null) {
            text += textLine + "\n";
        }
        input.close();
        
        boolean complete = false;

        while (!complete) {
            printOptions();
            String inputLine = Input.prompt("Type in your option: ");
            
            switch (inputLine.charAt(0)) {
                case '1':
                	Searcher.searchByRoute(text);
                    break;
                case '2':
                    Searcher.searchByCity(text);
                    break;
                case '3':
                    complete = true;
                    break;
                default:
                    System.out.println("Option not valid, please try again.");
            }
        }
        System.out.println("Terminating Program");
    }
    
    
    private static void printOptions() {
    	 System.out.println("***********************************************************");
         System.out.println("                 1. Search for Route ID                    ");
         System.out.println("               2. Search for Destination                   "); 
         System.out.println("                        3. Quit                            ");
    	 System.out.println("***********************************************************");
    }
}
