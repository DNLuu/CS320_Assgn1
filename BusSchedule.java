// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.*;
import java.net.*;

public class BusSchedule {
    public static void main(String[] args) throws Exception {
        boolean complete = false;
        complete = true;

        while (!complete) {
            printOptions();
            String inputLine = Input.prompt("Type in your option: ");

            switch (inputLine.charAt(0)) {
                case '1':
                    System.out.println("CASE 1");
                    break;
                case '2':
                    System.out.println("CASE 2");
                    break;
                case '3':
                    complete = true;
                    break;
                default:
                    System.out.println("Option not valid, please try again.");
            }
        }


        URLConnection transitSchedules = new URL("https://www.communitytransit.org/busservice/schedules/").openConnection();

        // No idea if this line works
        transitSchedules.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        BufferedReader input = new BufferedReader(new InputStreamReader(transitSchedules.getInputStream()));

// ******************************* ORACLE DOCUMENTATION AVAILABLE**************************************************************************
        String inputLine = "";
        String text = "";

        while ((inputLine = input.readLine()) != null) {
            text += inputLine + "\n";
        }

        input.close();

        Searcher.searchByCity();

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
