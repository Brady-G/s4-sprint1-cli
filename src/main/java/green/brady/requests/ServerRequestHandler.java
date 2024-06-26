package green.brady.requests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import green.brady.model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerRequestHandler implements RequestHandler {

    private static final String URL = System.getProperty("API_URL", "http://localhost:8080/");
    private static final Gson GSON = new Gson();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static <T> Response<T> get(String path, TypeToken<T> token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + path))
                .header("User-Agent", "Brady Green S4 CLI (1.0.0)")
                .GET()
                .build();

        try {
            var res = CLIENT.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return Response.of(GSON.fromJson(res.body(), token));
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @Override
    public Response<List<Airport>> getAirports() {
        return get("airports", new TypeToken<>() {});
    }

    @Override
    public Response<List<City>> getCities() {
        return get("cities", new TypeToken<>() {});
    }

    @Override
    public Response<List<Route>> getRoutes() {
        return get("routes", new TypeToken<>() {});
    }

    @Override
    public Response<List<AirCraft>> getAircrafts() {
        return get("aircrafts", new TypeToken<>() {});
    }

    @Override
    public Response<List<Passenger>> getPassengers() {
        return get("passengers", new TypeToken<>() {});
    }

    @Override
    public Response<List<Route>> getPassengerRoutes(int passenger) {
        return get("passengers/%d/routes".formatted(passenger), new TypeToken<>() {});
    }
}
