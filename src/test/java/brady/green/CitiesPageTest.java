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

public class CitiesPageTest extends BasePageTest {

    @BeforeEach
    public void setup() {
        super.setup();
        CliApp.start(
                new TestPrompt(Map.of("Enter an option: ", new ArrayList<>(List.of("1", "5")))),
                new TestRequestHandler()
        );
    }

    @Test
    public void testPageOpens() {
        Assertions.assertTrue(out.toString().contains("Getting cities..."));
    }

    @Test
    public void testCitiesVisible() {
        List<String> outputs = TestUtils.getOutputsBetween(out, "Getting cities...", "Options:");

        Assertions.assertEquals(outputs.size(), 9);
        Assertions.assertEquals(outputs.get(3), "<1> New York [NY] (10000000)");
        Assertions.assertEquals(outputs.get(4), "<2> Los Angeles [CA] (4000000)");
        Assertions.assertEquals(outputs.get(5), "<3> Chicago [IL] (3000000)");
        Assertions.assertEquals(outputs.get(6), "<4> Houston [TX] (2000000)");
        Assertions.assertEquals(outputs.get(7), "<5> Phoenix [AZ] (1500000)");
    }
}
