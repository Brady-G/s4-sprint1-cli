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

public class AirPortsPageTest extends BasePageTest {

    @BeforeEach
    public void setup() {
        super.setup();
        CliApp.start(
                new TestPrompt(Map.of("Enter an option: ", new ArrayList<>(List.of("2", "5")))),
                new TestRequestHandler()
        );
    }

    @Test
    public void testPageOpens() {
        Assertions.assertTrue(out.toString().contains("Getting airports..."));
    }

    @Test
    public void testAirportsVisible() {
        List<String> outputs = TestUtils.getOutputsBetween(out, "Getting airports...", "Options:");

        Assertions.assertEquals(outputs.size(), 9);
        Assertions.assertEquals(outputs.get(3), "Los Angeles");
        Assertions.assertEquals(outputs.get(4), "  <2> Los Angeles International Airport (LAX)");
        Assertions.assertEquals(outputs.get(5), "  <3> O'Hare International Airport (ORD)");
        Assertions.assertEquals(outputs.get(6), "New York");
        Assertions.assertEquals(outputs.get(7), "  <1> John F. Kennedy International Airport (JFK)");
    }
}
