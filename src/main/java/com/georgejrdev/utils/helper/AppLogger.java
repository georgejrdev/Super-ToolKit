package com.georgejrdev.utils.helper;

import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    
    private static Logger logger;
    
    public static Logger getLogger() {
        if (logger == null) {
            initializeLogger();
        }
        return logger;
    }

    private static void initializeLogger() {
        logger = Logger.getLogger("MyAppLogger");
        logger.setUseParentHandlers(false);

        try {;
            FileHandler fileHandler = new FileHandler(PROGRAM_PATH+"\\app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            removeConsoleHandler();

            logger.setLevel(java.util.logging.Level.ALL);
            logger.info("Logger setup completed.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error setting up logger: " + e.getMessage());
        }
    }

    private static void removeConsoleHandler() {
        for (var handler : logger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                logger.removeHandler(handler);
            }
        }
    }
}