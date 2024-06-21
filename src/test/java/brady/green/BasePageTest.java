package brady.green;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class BasePageTest {

    private final PrintStream oldOut = System.out;
    protected ByteArrayOutputStream out;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void cleanup() {
        System.setOut(oldOut);
    }

}
