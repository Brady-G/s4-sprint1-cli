package green.brady.cli;

import green.brady.cli.pages.*;
import green.brady.cli.pages.passengers.PassengersPage;
import green.brady.requests.RequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CliApp {

    private static final Map<String, Function<RequestHandler, Page>> PAGES = new LinkedHashMap<>(){{
        put("Cities", CitiesPage::new);
        put("Airports", AirportsPage::new);
        put("Air Crafts", AirCraftsPage::new);
        put("Passengers/Flights", PassengersPage::new);
    }};

    public static void start(Prompt prompt, RequestHandler handler) {
        List<Function<RequestHandler, Page>> pages = new ArrayList<>();
        StringBuilder options = new StringBuilder();
        options.append("Options:\n");
        for (var entry : PAGES.entrySet()) {
            pages.add(entry.getValue());
            options.append(pages.size()).append(". ").append(entry.getKey()).append("\n");
        }
        options.append(pages.size() + 1).append(". Exit");

        while (true) {
            System.out.println(options);

            int option = prompt.promptInt("Enter an option: ");

            if (option < 1 || option > pages.size() + 1) {
                System.out.println("Invalid option");
            } else if (option == pages.size() + 1) {
                System.out.println("Exiting...");
                return;
            } else {
                try {
                    pages.get(option - 1).apply(handler).display(prompt);
                    prompt.waitForEnter();
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
