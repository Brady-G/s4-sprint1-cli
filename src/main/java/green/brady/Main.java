package green.brady;

import green.brady.cli.pages.*;
import green.brady.cli.ErrorManager;

import java.util.*;
import java.util.function.Supplier;

public class Main {

    private static final Map<String, Supplier<Page>> PAGES = new LinkedHashMap<>(){{
        put("Cities", CitiesPage::new);
        put("Airports", AirportsPage::new);
        put("Air Crafts", AirCraftsPage::new);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Supplier<Page>> pages = new ArrayList<>();
        StringBuilder options = new StringBuilder();
        options.append("Options:\n");
        for (var entry : PAGES.entrySet()) {
            pages.add(entry.getValue());
            options.append(pages.size()).append(". ").append(entry.getKey()).append("\n");
        }
        options.append(pages.size() + 1).append(". Exit");

        while (true) {
            System.out.println(options);

            System.out.println("Enter an option:");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 1 || option > pages.size() + 1) {
                System.out.println("Invalid option");
            } else if (option == pages.size() + 1) {
                System.out.println("Exiting...");
                return;
            } else {
                try {
                    pages.get(option - 1).get().display();
                    System.out.println("Press enter to continue...");
                    scanner.nextLine();
                } catch (PageError error) {
                    System.out.println("An error occurred: " + error.getMessage());
                    ErrorManager.logError(error.getCause());
                } catch (Exception e) {
                    System.out.println("An error occurred, you can read the errors.log for more information: " + e.getMessage());
                    ErrorManager.logError(e);
                }
            }
        }
    }
}