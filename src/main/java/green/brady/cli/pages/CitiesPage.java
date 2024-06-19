package green.brady.cli.pages;

import green.brady.model.City;
import green.brady.requests.RequestHandler;

import java.util.Comparator;
import java.util.List;

public class CitiesPage implements Page {

    private final List<City> cities;

    public CitiesPage() {
        System.out.println("Getting cities...");
        cities = RequestHandler.getCities()
                .getOrThrow(e -> new PageError("Failed to get cities", e))
                .stream()
                .sorted(Comparator.comparing(City::name))
                .toList();
    }

    @Override
    public void display() {
        Page.divider();
        System.out.println("<id> Name [State] (Population)");
        Page.divider();
        for (var city : cities) {
            System.out.printf("<%d> %s [%s] (%d)%n", city.id(), city.name(), city.state(), city.population());
        }
        Page.divider();
    }
}