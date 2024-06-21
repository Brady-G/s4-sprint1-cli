package green.brady.cli.pages;

import green.brady.cli.Prompt;
import green.brady.model.Airport;
import green.brady.model.City;
import green.brady.requests.RequestHandler;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AirportsPage implements Page {

    private final Map<Integer, String> cities;
    private final Map<Integer, Airport> airports;

    public AirportsPage(RequestHandler handler) {
        System.out.println("Getting airports...");
        airports = handler.getAirports()
                .getOrThrow(e -> new PageError("Failed to get airports", e))
                .stream()
                .collect(Collectors.toMap(Airport::id, Function.identity()));
        cities = handler.getCities()
                .getOrThrow(e -> new PageError("Failed to get cities", e))
                .stream()
                .collect(Collectors.toMap(City::id, City::name));
    }

    private String getCityName(Airport airport) {
        return cities.getOrDefault(airport.city(), "Unknown");
    }

    @Override
    public void display(Prompt prompt) {
        Page.divider();
        System.out.println("<id> Name (Code)");
        Page.divider();

        Map<String, List<Airport>> airportsByCity = airports.values()
                .stream()
                .sorted(Comparator.comparing(this::getCityName))
                .collect(Collectors.groupingBy(this::getCityName, LinkedHashMap::new, Collectors.toList()));

        for (var entry : airportsByCity.entrySet()) {
            System.out.println(entry.getKey());
            for (var airport : entry.getValue()) {
                System.out.printf("  <%d> %s (%s)%n", airport.id(), airport.name(), airport.code());
            }
        }
        Page.divider();
    }
}
