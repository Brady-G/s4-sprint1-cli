package green.brady.cli;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ErrorManager {

    private static final File ERROR_LOG = new File("error.log");

    private static OutputStream getStream() throws IOException {
        if (!ERROR_LOG.exists()) {
            try {
                ERROR_LOG.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create error log file");
            }
        }
        return Files.newOutputStream(ERROR_LOG.toPath(), StandardOpenOption.APPEND);
    }

    public static void logError(Throwable throwable) {
        try (PrintStream stream = new PrintStream(getStream())) {
            throwable.printStackTrace(stream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write to error log file: " + e.getMessage());
        }
    }

    public static void logError(String message) {
        try (PrintStream stream = new PrintStream(getStream())) {
            stream.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write to error log file: " + e.getMessage());
        }
    }
}
