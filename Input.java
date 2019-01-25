import java.util.Scanner;

public class Input {
    public static String prompt(String input) {
    	System.out.println(input);
		Scanner scanner = new Scanner(System.in);
		String output = scanner.nextLine();
		scanner.close();
		return output;
    }
}
