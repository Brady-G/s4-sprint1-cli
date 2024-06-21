package green.brady.cli;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

    private final Scanner scanner;

    public Prompt(InputStream source) {
        this.scanner = new Scanner(source);
    }

    public void waitForEnter() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public int promptInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(prompt(message));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public boolean promptYesNo(String message) {
        while (true) {
            String input = prompt(message);
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
