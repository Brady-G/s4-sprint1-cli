package brady.green;

import brady.green.utils.TestPrompt;
import brady.green.utils.TestRequestHandler;
import brady.green.utils.TestUtils;
import green.brady.cli.CliApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirCraftsPageTest extends BasePageTest {

    @BeforeEach
    public void setup() {
        super.setup();
        CliApp.start(
                new TestPrompt(Map.of("Enter an option: ", new ArrayList<>(List.of("3", "5")))),
                new TestRequestHandler()
        );
    }

    @Test
    public void testPageOpens() {
        Assertions.assertTrue(out.toString().contains("Getting aircrafts..."));
    }

    @Test
    public void testAirportsVisible() {
        List<String> outputs = TestUtils.getOutputsBetween(out, "Getting aircrafts...", "Options:");

        Assertions.assertEquals(outputs.size(), 25);
        Assertions.assertEquals(outputs.get(2), "American Airlines (1):");
        Assertions.assertEquals(outputs.get(3), "  Type: Boeing 737");
        Assertions.assertEquals(outputs.get(4), "  Capacity: 150");
        Assertions.assertEquals(outputs.get(5), "  Take off/landings:");
        Assertions.assertEquals(outputs.get(6), "    Los Angeles International Airport: 2/0");
        Assertions.assertEquals(outputs.get(7), "    John F. Kennedy International Airport: 0/2");

        Assertions.assertEquals(outputs.get(9), "Delta Airlines (2):");
        Assertions.assertEquals(outputs.get(10), "  Type: Boeing 747");
        Assertions.assertEquals(outputs.get(11), "  Capacity: 300");
        Assertions.assertEquals(outputs.get(12), "  Take off/landings:");
        Assertions.assertEquals(outputs.get(13), "    O'Hare International Airport: 2/0");
        Assertions.assertEquals(outputs.get(14), "    Los Angeles International Airport: 0/2");

        Assertions.assertEquals(outputs.get(16), "United Airlines (3):");
        Assertions.assertEquals(outputs.get(17), "  Type: Airbus A320");
        Assertions.assertEquals(outputs.get(18), "  Capacity: 200");
        Assertions.assertEquals(outputs.get(19), "  Take off/landings:");
        Assertions.assertEquals(outputs.get(20), "    John F. Kennedy International Airport: 1/0");
        Assertions.assertEquals(outputs.get(21), "    O'Hare International Airport: 0/2");
        Assertions.assertEquals(outputs.get(22), "    Los Angeles International Airport: 1/0");
    }
}
