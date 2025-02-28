package com.example;

import java.io.IOException;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            // Configure file handler
            FileHandler fileHandler = new FileHandler("src\\main\\resources\\Log.txt", true);
            fileHandler.setFormatter(new SingleLineFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Disable console logging
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static void logError(Exception e, String customMessage) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement firstElement = stackTrace[0]; // Get the first stack trace element
            String logMessage = String.format(
                    "%s in %s:%d - %s",
                    e.getClass().getSimpleName(),
                    firstElement.getFileName(),
                    firstElement.getLineNumber(),
                    customMessage);

            logger.severe(logMessage);
        }
    }
}

// Custom formatter to ensure single-line log format
class SingleLineFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getMessage() + System.lineSeparator();
    }
}
