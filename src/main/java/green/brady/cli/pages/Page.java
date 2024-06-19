package green.brady.cli.pages;

public interface Page {

    void display();

    static void divider() {
        System.out.println("⎯".repeat(100));
    }
}
