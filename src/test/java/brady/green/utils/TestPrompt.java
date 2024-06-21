package brady.green.utils;

import green.brady.cli.Prompt;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPrompt extends Prompt {

    private final Map<String, List<String>> inputs = new HashMap<>();

    public TestPrompt(Map<String, List<String>> inputs) {
        super(InputStream.nullInputStream());
        inputs.forEach((prompt, responses) ->
            this.inputs.put(prompt, new ArrayList<>(responses))
        );
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
