package green.brady.cli.pages;

import green.brady.cli.Prompt;

public interface Page {

    void display(Prompt prompt);

    static void divider() {
        System.out.println("⎯".repeat(100));
    }
}
