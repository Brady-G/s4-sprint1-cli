package brady.green.utils;

import java.io.OutputStream;
import java.util.List;

public class TestUtils {

    public static List<String> getOutputsBetween(OutputStream stream, String first, String second) {
        String output = stream.toString();
        var lines = output.lines().toList();
        int start = -1;
        int end = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equalsIgnoreCase(first) && start == -1) {
                start = i;
            } else if (lines.get(i).equalsIgnoreCase(second) && start != -1) {
                end = i;
                break;
            }
        }
        if (start == -1 || end == -1) {
            throw new IllegalStateException("Could not find start or end");
        }
        return lines.subList(start + 1, end);
    }
}
