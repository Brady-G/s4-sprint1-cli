package green.brady.cli.pages.passengers;

import green.brady.cli.Prompt;
import green.brady.cli.pages.Page;
import green.brady.cli.pages.PageError;
import green.brady.model.Airport;
import green.brady.model.Passenger;
import green.brady.model.Route;
import green.brady.requests.RequestHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassengerFlightsPage implements Page {

    private final Passenger passenger;
    private final List<Route> routes;
    private final Map<Integer, String> airports;

    public PassengerFlightsPage(Passenger passenger) {
        this.passenger = passenger;
        this.routes = RequestHandler.getRoutesForPassenger(passenger.id())
                .getOrThrow(e -> new PageError("Failed to get passenger routes", e))
                .stream()
                .toList();
        this.airports = RequestHandler.getAirports()
                .getOrThrow(e -> new PageError("Failed to get airports", e))
                .stream()
                .collect(Collectors.toMap(Airport::id, Airport::name));
    }

    private String airportName(int id) {
        return airports.getOrDefault(id, "Unknown");
    }

    @Override
    public void display(Prompt prompt) {
        Page.divider();
        System.out.printf("Flights for %s %s (%s)%n", passenger.firstName(), passenger.lastName(), passenger.phoneNumber());
        Page.divider();
        for (var route : routes) {
            System.out.printf(
                    "%s -> %s%n",
                    airportName(route.origin()),
                    airportName(route.destination())
            );
        }
        Page.divider();
        prompt.waitForEnter();
    }
}
