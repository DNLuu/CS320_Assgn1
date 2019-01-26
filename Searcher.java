// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

// This class proccesses requested queries by the user.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class Searcher {
	// Prompts the user for the letter their destination starts with and then displays a list of 
	// available destinations and a corresponding lists of bus routes to and from that location.
	public static void searchByCity(String text) throws MalformedURLException, IOException {
		String nextLine = Input.prompt("Please enter a letter that your destination starts with: ");
		char input = nextLine.toUpperCase().charAt(0); // Character input

        String searchPattern = "<h3>(" +  input + ".*)</h3>";
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);

        // get cities
        ArrayList<String> cityList = new ArrayList<String>();
        while (matcher.find()) {
            cityList.add(matcher.group(1));
        }
        for (String city : cityList) {
            System.out.println("Destination: " + city);
            ArrayList<String> routeList = new ArrayList<String>();
            pattern = Pattern.compile("<h3>" + city + "</h3>\\s*" +
                    "((<.*>\\s*){3}<a href=\".*\\s*(<.*>\\s*){3})*");
            matcher = pattern.matcher(text);

            String citySchedText = "";
            if (matcher.find()) {
                citySchedText = matcher.toString();
            }

            pattern = Pattern.compile("[a-z0-9.]*<*>*>(\\d{3}/)?(\\d{3})(\\s.)?</a>");
            matcher = pattern.matcher(citySchedText);

            while (matcher.find()) {
                String s = "";
                if (matcher.group(1) != null) {
                    s = matcher.group(1);
                    s = s.replaceAll("[^0-9.]", "");
                    routeList.add(s);
                }
                s = matcher.group(2);
                routeList.add(s);
            }
//            System.out.println(routeList.toString());
            for (String bus : routeList) {
            	System.out.println("Bus Number: " + bus);
            }
            System.out.println("+++++++++++++++++++++++++");
        }
        System.out.println();
	}
	
	// Prompts user for a desired route ID number, ignoring all non-digit characters and all digits
	// after the first 3. Will then display the route going both directions, all the stops along
	// that route, and the final destination.
	public static void searchByRoute(String text) throws IOException {
		String inputRoute = Input.promptForRoute(); 
		
		Pattern pattern = Pattern.compile("<a href=\"(/schedules/route.*)\">.*" + inputRoute);
		Matcher matcher = pattern.matcher(text);
		
		if (matcher.find()) {
			String scheduleUrl = "https://www.communitytransit.org/busservice" + matcher.group(1);
			
	    	URLConnection transit = new URL(scheduleUrl).openConnection();
	        transit.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) "
	        								+ "AppleWebKit/537.11 (KHTML, like Gecko) "
	        								+ "Chrome/23.0.1271.95 Safari/537.11");
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(transit.getInputStream()));
	        String textLine = "", scheduleText = "";
	        
	        while ((textLine = in.readLine()) != null) {
	            scheduleText += textLine + "\n";
	        }
	        in.close();	
			
	        System.out.println();
			System.out.println("The link for your route is:" + scheduleUrl);
			System.out.println();
			
			// Get route destinations
			pattern = Pattern.compile("h2>(.*) to (.*)<");
			matcher = pattern.matcher(scheduleText);
			
			String location0 = "", location1 = "";
			location1 = matcher.group(1);
			location0 = matcher.group(2);
			
			// Get bus routes stops information for both directions
			pattern = Pattern.compile("<div id=\"Weekday.*>\\s*(<.*>\\s*)*\\s*");
			matcher = pattern.matcher(scheduleText);
			
			ArrayList<String> stopTables = new ArrayList<String>();
			while (matcher.find()) {
				stopTables.add(matcher.toString());
			}
			
			// Print the list of stops and their respective numbers/letters for both directions
			ArrayList<String> stopNumbersList0 = new ArrayList<String>();
			ArrayList<String> stopNumbersList1= new ArrayList<String>();
			ArrayList<String> stopNamesList0 = new ArrayList<String>();
			ArrayList<String> stopNamesList1 = new ArrayList<String>();
			
			pattern = Pattern.compile("1x\">(.*)</strong>");
			appendMatcherToList(matcher, pattern, stopTables.get(0), stopNumbersList0);
			appendMatcherToList(matcher, pattern, stopTables.get(1), stopNumbersList1);

			pattern = Pattern.compile("<p>(.*)</p>");
			appendMatcherToList(matcher, pattern, stopTables.get(0), stopNamesList0);
			appendMatcherToList(matcher, pattern, stopTables.get(1), stopNamesList1);
			
			printRouteList(location0, stopNumbersList0, stopNamesList0);
			printRouteList(location1, stopNumbersList1, stopNamesList1);
		} else {
			System.out.println("No matching route found.");
		}
		System.out.println();
    }
	
	// Adds pattern/matcher results to a list
	private static void appendMatcherToList(Matcher matcher, Pattern pattern, 
										    String text, ArrayList<String> list) {
			matcher = pattern.matcher(text);
			while (matcher.find()) {
				list.add(matcher.group(1));
			}
	}
	
	// Prints the final destination, list of stops, and their corresponding numbers/letters
	private static void printRouteList(String location, ArrayList<String> stopNumbers, 
									   ArrayList<String> stopNames) {
		System.out.println("Destination: To " + location);
		for (int i = 0; i < stopNumbers.size(); i++) {
			System.out.println("Stop number: " + stopNumbers.get(i) + 
								" is " + stopNames.get(i));
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");	
	}
}
