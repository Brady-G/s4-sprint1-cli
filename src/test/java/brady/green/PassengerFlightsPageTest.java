package brady.green;

import brady.green.utils.TestPrompt;
import brady.green.utils.TestRequestHandler;
import brady.green.utils.TestUtils;
import green.brady.cli.CliApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PassengerFlightsPageTest extends BasePageTest {

    @Test
    public void testPageOpens() {
        CliApp.start(
                new TestPrompt(Map.of(
                        "Enter an option: ", List.of("4", "5"),
                        "Enter the id of the passenger you would like to view, or 0 to cancel:", new ArrayList<>(List.of("1", "0"))
                )),
                new TestRequestHandler()
        );
        Assertions.assertTrue(out.toString().contains("Flights for John Doe ((123) 456-7890)"));
    }

    @Test
    public void testPassengersFlightsVisible() {
        CliApp.start(
                new TestPrompt(Map.of(
                        "Enter an option: ", List.of("4", "5"),
                        "Enter the id of the passenger you would like to view, or 0 to cancel:", new ArrayList<>(List.of("1", "0"))
                )),
                new TestRequestHandler()
        );

        List<String> outputs = TestUtils.getOutputsBetween(
                out,
                "Flights for John Doe ((123) 456-7890)",
                "Options:"
        );

        Assertions.assertTrue(outputs.size() >= 4);
        Assertions.assertEquals(outputs.get(1), "Los Angeles International Airport -> John F. Kennedy International Airport");
        Assertions.assertEquals(outputs.get(2), "Los Angeles International Airport -> John F. Kennedy International Airport");
    }

    @Test
    public void testPassengersFlightsAfterVisible() {
        CliApp.start(
                new TestPrompt(Map.of(
                        "Enter an option: ", List.of("4", "5"),
                        "Enter the id of the passenger you would like to view, or 0 to cancel:", new ArrayList<>(List.of("1", "3", "4", "0"))
                )),
                new TestRequestHandler()
        );

        List<String> outputs = TestUtils.getOutputsBetween(
                out,
                "Flights for John Doe ((123) 456-7890)",
                "Options:"
        );

        Assertions.assertEquals(outputs.get(1), "Los Angeles International Airport -> John F. Kennedy International Airport");
        Assertions.assertEquals(outputs.get(2), "Los Angeles International Airport -> John F. Kennedy International Airport");

        outputs = TestUtils.getOutputsBetween(
                out,
                "Flights for Alice Smith ((123) 456-7890)",
                "Options:"
        );

        Assertions.assertEquals(outputs.get(1), "O'Hare International Airport -> Los Angeles International Airport");
        Assertions.assertEquals(outputs.get(2), "John F. Kennedy International Airport -> Los Angeles International Airport");

        outputs = TestUtils.getOutputsBetween(
                out,
                "Flights for Bob Smith ((123) 456-7890)",
                "Options:"
        );

        Assertions.assertEquals(outputs.get(1), "John F. Kennedy International Airport -> O'Hare International Airport");
    }

}
