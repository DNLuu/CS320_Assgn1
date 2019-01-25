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
        
        while((inputLine = input.readLine()) != null) {
        	text += inputLine + "\n";
        }
        
        input.close();
        
//        Pattern pattern = Pattern.compile("<td>(.*)\\s*</td>\\s*<td>(.*)\\s*</td>\\s*<td>(.*)\\s*</td>");
//        Pattern pattern = Pattern.compile("<td>.*<\td>");
//        Pattern pattern = Pattern.compile(">(\\d{3})(\\s.)?</a>");
//        Matcher matcher = pattern.matcher(text);
//        int i = 0;
//        while (matcher.find() && i < 100) {
//        	System.out.println("FOUND");
//        	System.out.println(matcher.group(1));
//        	System.out.println(matcher.group(2));
//        	System.out.println(matcher.group(3));
//        	System.out.println(matcher.toString());
//        	i++;
//        }

        
        
//        Pattern pattern1 = Pattern.compile(">(\\d{3})(\\s.)?");
//        Matcher matcher1 = pattern1.matcher(text);
//        int j = 0;
//        while (matcher1.find() && j < 8) {
//        	System.out.println(matcher1.group(1));
//        	j++;
//        }
        
        
        // get cities
        ArrayList<String> cityList = new ArrayList<String>();
        char c = 'G';
        String searchPattern = "<h3>(" + c + ".*)</h3>";
        Pattern pattern = Pattern.compile(searchPattern); //"<h3>(B.*)</h3>");
        Matcher matcher = pattern.matcher(text);
        System.out.println(searchPattern);
        int i = 0;
        while (matcher.find() && i < 8) {
        	System.out.println("FOUND");
        	cityList.add(matcher.group(1));
        	
        	i++;
        }
        System.out.println(cityList.toString());
        
//        for (String city : cityList) {
//			pattern = Pattern.compile("");
//			matcher = pattern.matcher(text);
//			
//			i = 0;
//			while (matcher.find() && i < 3) {
//				
//			}
//        }
        
        // get bus numbers of a specific city
        
//        pattern = Pattern.compile(">(\\d{3}/)?(\\d{3})(\\s.)?</a>");
        ArrayList<String> routeList = new ArrayList<String>();
        pattern = Pattern.compile("[a-z0-9.]*<*>*>(\\d{3}/)?(\\d{3})(\\s.)?</a>");
        pattern = Pattern.compile("Arlington</h3>\\s*\\w*<*>*/*.*:*");
//        pattern = Pattern.compile("<strong><a");
        matcher = pattern.matcher(text);
        i = 0;
        while (matcher.find() && i < 8) {
        	System.out.print("FOUND: ");
        	System.out.println(matcher.toString());
//        	String s = "";
//        	if (matcher.group(1) != null) {
//        		s = matcher.group(1);
//        		s = s.replaceAll("[^0-9.]", "");
//        		System.out.println(s);
//        		routeList.add(s);
//        	}
//        	s = matcher.group(2);
//        	System.out.println(s);
//        	routeList.add(s);
//        	i++;
        }
        System.out.println(routeList.toString());
        
        
        
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
