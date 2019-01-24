// Dominic Luu
// CS 320
// 01-23-2019
// Assignment 1: Bus Route Schedules


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;


public class BusSchedule {
    public static void main(String[] args) throws Exception {
//        URLConnection bc = new URL("https://www.bellevuecollege.edu/courses/exams/").openConnection();
//
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(bc.getInputStream()));
//
//        String inputLine = "";
//
//        String text = "";
//        while ((inputLine = in.readLine()) != null) {
//            text += inputLine + "\n";
//
//        }
//        in.close();
//
//        Pattern pattern = Pattern.compile("<td>(.*)\\s*</td>\\s*<td>(.*)\\s*</td>\\s*<td>(.*)\\s*</td>");
//        Matcher matcher = pattern.matcher(text);
//
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//        }
        URLConnection transitSchedules = new URL("https://wwww.communitytransit.org/busservice/schedules/").openConnection();

        // No idea if this line works
        transitSchedules.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        System.out.println();


    }
}
