package brady.green.utils;

import green.brady.cli.Prompt;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TestPrompt extends Prompt {

    private final Map<String, List<String>> inputs;

    public TestPrompt(Map<String, List<String>> inputs) {
        super(InputStream.nullInputStream());
        this.inputs = inputs;
    }

    @Override
    public void waitForEnter() {

    }

    @Override
    public String prompt(String message) {
        List<String> responses = inputs.get(message);
        if (responses == null || responses.isEmpty()) {
            throw new IllegalStateException("No responses for: " + message);
        }
        return responses.remove(0);
    }
}
