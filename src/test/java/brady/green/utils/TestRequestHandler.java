package brady.green.utils;

import green.brady.model.*;
import green.brady.requests.RequestHandler;
import green.brady.requests.Response;

import java.util.List;

public class TestRequestHandler implements RequestHandler {

    @Override
    public Response<List<Airport>> getAirports() {
        return Response.of(
                List.of(
                        new Airport(1, "JFK", 1, "John F. Kennedy International Airport"),
                        new Airport(2, "LAX", 2, "Los Angeles International Airport"),
                        new Airport(3, "ORD", 2, "O'Hare International Airport")
                )
        );
    }

    @Override
    public Response<List<City>> getCities() {
        return Response.of(
                List.of(
                        new City(1, "New York", "NY", 10000000),
                        new City(2, "Los Angeles", "CA", 4000000),
                        new City(3, "Chicago", "IL", 3000000),
                        new City(4, "Houston", "TX", 2000000),
                        new City(5, "Phoenix", "AZ", 1500000)
                )
        );
    }

    @Override
    public Response<List<Route>> getRoutes() {
        return Response.of(
                List.of(
                        new Route(1, 1, 2, 1),
                        new Route(2, 1, 2, 1),
                        new Route(3, 2, 3, 2),
                        new Route(4, 2, 3, 2),
                        new Route(5, 3, 1, 3),
                        new Route(6, 3, 2, 3)
                )
        );
    }

    @Override
    public Response<List<AirCraft>> getAircrafts() {
        return Response.of(
                List.of(
                        new AirCraft(1, "Boeing 737", "American Airlines", 150),
                        new AirCraft(2, "Boeing 747", "Delta Airlines", 300),
                        new AirCraft(3, "Airbus A320", "United Airlines", 200)
                )
        );
    }

    @Override
    public Response<List<Passenger>> getPassengers() {
        return Response.of(
                List.of(
                        new Passenger(1, "John", "Doe", "(123) 456-7890"),
                        new Passenger(2, "Jane", "Doe", "(123) 456-7890"),
                        new Passenger(3, "Alice", "Smith", "(123) 456-7890"),
                        new Passenger(4, "Bob", "Smith", "(123) 456-7890")
                )
        );
    }

    @Override
    public Response<List<Route>> getPassengerRoutes(int passenger) {
        return switch (passenger) {
            case 1, 2 -> Response.of(
                    List.of(
                            new Route(1, 1, 2, 1),
                            new Route(2, 1, 2, 1)
                    )
            );
            case 3 -> Response.of(
                    List.of(
                            new Route(3, 2, 3, 2),
                            new Route(4, 2, 1, 2)
                    )
            );
            case 4 -> Response.of(
                    List.of(
                            new Route(5, 3, 1, 3)
                    )
            );
            default -> Response.of(List.of());
        };
    }
}
