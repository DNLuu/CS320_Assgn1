// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class Searcher {
	public static void searchRoutes() throws MalformedURLException, IOException {
		String nextLine = Input.prompt("Please enter a letter that your destination starts with: ");
		char input = nextLine.toUpperCase().charAt(0); // Character input
		
        URLConnection transitSchedules = new URL("https://www.communitytransit.org/busservice/schedules/").openConnection();

        transitSchedules.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(transitSchedules.getInputStream()));
        
        String inputLine = "";
        
        String text = "";
        
        while((inputLine = buffer.readLine()) != null) {
        	text += inputLine + "\n";
        }
        
        buffer.close();
        
        
        String searchPattern = "<h3>(" +  input + ".*)</h3>";
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);
        int i = 0;
//        while (matcher.find() && i < 100) {
//        	System.out.println("FOUND");
//        	System.out.println(matcher.group(1));
//        	System.out.println(matcher.group(2));
//        	System.out.println(matcher.group(3));
//        	System.out.println(matcher.toString());
//        	i++;
//        }	
	}

}
