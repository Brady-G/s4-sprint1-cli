package green.brady.requests;

import green.brady.model.*;

import java.util.List;

public interface RequestHandler {

    Response<List<Airport>> getAirports();

    Response<List<City>> getCities();

    Response<List<Route>> getRoutes();

    Response<List<AirCraft>> getAircrafts();

    Response<List<Passenger>> getPassengers();

    Response<List<Route>> getPassengerRoutes(int passenger);
}
