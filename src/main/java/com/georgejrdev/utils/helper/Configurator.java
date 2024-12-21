package com.georgejrdev.utils.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Configurator {

    private static final Logger logger = AppLogger.getLogger();

    public static void configureGeminiApiKey() {
        System.out.println("Enter your Gemini API key:");
        Scanner scanner = new Scanner(System.in);
        String apiKey = scanner.nextLine();

        final String OS = System.getProperty("os.name");

        try {
            if (OS != null && OS.toLowerCase().contains("linux")) {
                configureForLinux(apiKey);
            } else if (OS != null && OS.toLowerCase().contains("win")) {
                configureForWindows(apiKey);
            } else {
                System.out.println("Unsupported operating system.");
                logger.severe("Unsupported operating system.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error configuring API key: " + e.getMessage());
            logger.severe("Error configuring API key: " + e.getMessage());
        }

        System.out.println("Gemini API key configured.");
        System.out.println("Restart the terminal for the changes to take effect.");
        logger.info("Gemini API key configured.");
        scanner.close();
    }

    private static void configureForLinux(String apiKey) throws IOException {
        String shellConfig = System.getenv("SHELL");
        String configFile;

        if (shellConfig != null && shellConfig.contains("zsh")) {
            configFile = System.getProperty("user.home") + "/.zshrc";
        } else {
            configFile = System.getProperty("user.home") + "/.bashrc";
        }

        String exportCommand = "export STK_GEMINI_API_KEY='" + apiKey+"'";
        Files.write(Paths.get(configFile), ("\n" + exportCommand).getBytes(), java.nio.file.StandardOpenOption.APPEND);
        System.out.println("API key added to " + configFile);
    }

    private static void configureForWindows(String apiKey) throws IOException, InterruptedException {
        String command = "cmd.exe /c setx STK_GEMINI_API_KEY \"" + apiKey + "\"";
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("API key successfully added as a system variable.");
        } else {
            System.err.println("Failed to configure API key on Windows.");
        }
    }
}