package green.brady.cli.pages;

import green.brady.cli.Pair;
import green.brady.cli.Prompt;
import green.brady.model.AirCraft;
import green.brady.model.Airport;
import green.brady.model.Route;
import green.brady.requests.RequestHandler;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirCraftsPage implements Page {

    private final List<AirCraft> aircrafts;
    private final Map<Integer, List<Route>> routes;
    private final Map<Integer, String> airports;

    public AirCraftsPage() {
        System.out.println("Getting aircrafts...");
        aircrafts = RequestHandler.getAircrafts()
                .getOrThrow(e -> new PageError("Failed to get aircrafts", e))
                .stream()
                .sorted(Comparator.comparing(AirCraft::airline))
                .toList();
        routes = RequestHandler.getRoutes()
                .getOrThrow(e -> new PageError("Failed to get routes", e))
                .stream()
                .collect(Collectors.groupingBy(Route::airCraft));
        airports = RequestHandler.getAirports()
                .getOrThrow(e -> new PageError("Failed to get airports", e))
                .stream()
                .collect(Collectors.toMap(Airport::id, Airport::name));
    }

    private String getDestination(Route route) {
        return airports.getOrDefault(route.destination(), "Unknown");
    }

    private String getOrigin(Route route) {
        return airports.getOrDefault(route.origin(), "Unknown");
    }

    @Override
    public void display(Prompt prompt) {
        Page.divider();
        System.out.println();

        for (AirCraft aircraft : aircrafts) {
            System.out.printf("%s (%d):%n", aircraft.airline(), aircraft.id());
            System.out.printf("  Type: %s%n", aircraft.type());
            System.out.printf("  Capacity: %d%n", aircraft.capacity());
            System.out.println("  Take off/landings:");
            Map<String, Pair<Integer, Integer>> takeOffLandings = new LinkedHashMap<>();
            for (Route route : routes.getOrDefault(aircraft.id(), List.of())) {
                var takeOff = takeOffLandings.getOrDefault(getOrigin(route), new Pair<>(0, 0));
                var landing = takeOffLandings.getOrDefault(getDestination(route), new Pair<>(0, 0));

                takeOffLandings.put(getOrigin(route), new Pair<>(takeOff.left() + 1, takeOff.right()));
                takeOffLandings.put(getDestination(route), new Pair<>(landing.left(), landing.right() + 1));
            }

            for (var entry : takeOffLandings.entrySet()) {
                System.out.printf("    %s: %d/%d%n", entry.getKey(), entry.getValue().left(), entry.getValue().right());
            }
            System.out.println();
        }
        Page.divider();
    }
}
