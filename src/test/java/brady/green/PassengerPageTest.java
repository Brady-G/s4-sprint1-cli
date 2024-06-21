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

public class PassengerPageTest extends BasePageTest {

    @BeforeEach
    public void setup() {
        super.setup();
        CliApp.start(
                new TestPrompt(Map.of(
                        "Enter an option: ", new ArrayList<>(List.of("4", "5")),
                        "Enter the id of the passenger you would like to view, or 0 to cancel:", new ArrayList<>(List.of("0"))
                )),
                new TestRequestHandler()
        );
    }

    @Test
    public void testPageOpens() {
        Assertions.assertTrue(out.toString().contains("<id> First Last (Phone)"));
    }

    @Test
    public void testPassengersVisible() {
        List<String> outputs = TestUtils.getOutputsBetween(
                out,
                "<id> First Last (Phone)",
                "Options:"
        );

        Assertions.assertEquals(outputs.size(), 6);
        Assertions.assertEquals(outputs.get(1), "<1> John Doe ((123) 456-7890)");
        Assertions.assertEquals(outputs.get(2), "<2> Jane Doe ((123) 456-7890)");
        Assertions.assertEquals(outputs.get(3), "<3> Alice Smith ((123) 456-7890)");
        Assertions.assertEquals(outputs.get(4), "<4> Bob Smith ((123) 456-7890)");
    }
}
