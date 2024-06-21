package green.brady.requests;

import green.brady.model.*;

import java.util.List;

public class UnsupportedRequestHandler implements RequestHandler {

    @Override
    public Response<List<Airport>> getAirports() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<List<City>> getCities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<List<Route>> getRoutes() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<List<AirCraft>> getAircrafts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<List<Passenger>> getPassengers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<List<Route>> getPassengerRoutes(int passenger) {
        throw new UnsupportedOperationException();
    }
}
