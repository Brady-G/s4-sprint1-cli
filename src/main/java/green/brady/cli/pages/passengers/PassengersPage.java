package green.brady.cli.pages.passengers;

import green.brady.cli.Prompt;
import green.brady.cli.pages.Page;
import green.brady.cli.pages.PageError;
import green.brady.model.Passenger;
import green.brady.requests.RequestHandler;

import java.util.List;

public class PassengersPage implements Page {

    private final RequestHandler handler;
    private final List<Passenger> passengers;

    public PassengersPage(RequestHandler handler) {
        this.handler = handler;
        this.passengers = handler.getPassengers()
                .getOrThrow(e -> new PageError("Failed to get passengers", e))
                .stream()
                .toList();
    }

    private Passenger getPassenger(Prompt prompt) {
        while (true) {
            int id = prompt.promptInt("Enter the id of the passenger you would like to view, or 0 to cancel:");
            if (id == 0) return null;

            Passenger passenger = passengers.stream().filter(p -> p.id() == id)
                    .findFirst()
                    .orElse(null);
            if (passenger == null) {
                System.out.println("Passenger not found");
            } else {
                return passenger;
            }
        }
    }

    @Override
    public void display(Prompt prompt) {
        while (true) {
            Page.divider();
            System.out.println("<id> First Last (Phone)");
            Page.divider();
            for (var passenger : passengers) {
                System.out.printf("<%d> %s %s (%s)%n", passenger.id(), passenger.firstName(), passenger.lastName(), passenger.phoneNumber());
            }
            Page.divider();
            Passenger passenger = getPassenger(prompt);
            if (passenger == null) return;
            new PassengerFlightsPage(handler, passenger).display(prompt);
        }
    }
}
