// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class Searcher {
	public static void searchByCity() throws MalformedURLException, IOException {
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

        // get cities
        ArrayList<String> cityList = new ArrayList<String>();
        while (matcher.find()) {
            cityList.add(matcher.group(1));
        }
        for (String city : cityList) {
            System.out.println("City: " + city);
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
            System.out.println(routeList.toString());
            System.out.println();
        }
	}

	public static void searchByRoute() {

    }
}
