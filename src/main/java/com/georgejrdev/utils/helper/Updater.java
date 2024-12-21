package com.georgejrdev.utils.helper;

import java.util.regex.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.*;

import static com.georgejrdev.SuperToolKit.VERSION;

public class Updater {

    public static void update() {
        String latestVersion = getLatestVersion();
        
        if (!isVersionNewer(VERSION, latestVersion)){
            System.out.println("No updates available");
            return;
        }

        final String OS = System.getProperty("os.name");

        System.out.println("New version available: " + latestVersion);
        System.out.println("Installing...");

        if (OS != null && OS.toLowerCase().contains("linux")) {
            linuxUpdate(latestVersion);
        } else {
            windowsUpdate(latestVersion);
        }
    }

    public static void updateIsAvailable() {
        String latestVersion = getLatestVersion();
        
        if (isVersionNewer(VERSION, latestVersion)) {
            System.out.println("New version available: " + latestVersion);
            System.out.println("Use 'stk update' to update");
        }
    }

    private static String getLatestVersion() {
        try {
            URL url = new URL("https://github.com/georgejrdev/Super-ToolKit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String htmlContent = response.toString();
                String regex = "https://img\\.shields\\.io/badge/(version-\\S+)";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(htmlContent);

                String version = "N/A";

                while (matcher.find()) {
                    String versionInfo = matcher.group(1);
                    version = versionInfo.substring(8, 13);
                    break;
                }

                return version;
            }

            return "N/A";
        } catch (Exception e) {
            System.out.println("Error fetching latest version: " + e.getMessage());
            return "N/A";
        }
    }

    private static boolean isVersionNewer(String localVersion, String latestVersion) {
        String[] parts1 = localVersion.split("\\.");
        String[] parts2 = latestVersion.split("\\.");

        for (int i = 0; i < Math.max(parts1.length, parts2.length); i++) {
            int part1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
            int part2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;

            if (part2 > part1) {
                return true;
            } else if (part1 > part2) {
                return false;
            }
        }
        return false;
    }

    private static void linuxUpdate(String latestVersion) {
        final String URL = "https://github.com/georgejrdev/Super-ToolKit/raw/main/build/linux" + latestVersion + ".zip";
        String path = getSuperToolKitPath();
        String saveDir = path + File.separator + "linux" + latestVersion + ".zip";

        try {
            URL url = new URL(URL);
            try (InputStream in = url.openStream(); FileOutputStream out = new FileOutputStream(saveDir)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            System.out.println("Error downloading update: " + e.getMessage());
            return;
        }

        try {
            Path zipPath = Paths.get(saveDir);
            Path destDir = zipPath.getParent();

            try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(saveDir))) {
                ZipEntry entry;
                while ((entry = zipIn.getNextEntry()) != null) {
                    Path outputPath = destDir.resolve(entry.getName());
                    if (entry.isDirectory()) {
                        Files.createDirectories(outputPath);
                    } else {
                        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath.toFile()))) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = zipIn.read(buffer)) != -1) {
                                bos.write(buffer, 0, length);
                            }
                        }
                    }
                    zipIn.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println("Error extracting update: " + e.getMessage());
            return;
        }

        String sourceDirPath = path + "/linux" + latestVersion;

        Path stkFilePath = Paths.get(sourceDirPath, "stk.sh");
        Path jarFilePath = Paths.get(sourceDirPath, "SuperToolKit.jar");

        Path stkDestPath = Paths.get(path, "stk.sh");
        Path jarDestPath = Paths.get(path, "SuperToolKit.jar");

        try {
            Files.move(stkFilePath, stkDestPath, StandardCopyOption.REPLACE_EXISTING);
            Files.move(jarFilePath, jarDestPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error replacing files: " + e.getMessage());
            return;
        }

        try {
            Files.deleteIfExists(Paths.get(saveDir));
            Files.deleteIfExists(Paths.get(sourceDirPath, "install.sh"));
            Files.delete(Paths.get(sourceDirPath));
        } catch (IOException e) {
            System.out.println("Error cleaning up: " + e.getMessage());
        }
    }

    private static void windowsUpdate(String latestVersion) {
        final String URL = "https://github.com/georgejrdev/Super-ToolKit/raw/main/build/windows" + latestVersion + ".zip";
        String path = getSuperToolKitPath();
        String saveDir = path + File.separator + "windows" + latestVersion + ".zip";

        String batScript = path + File.separator + "update_script.bat";

        try {
            Files.deleteIfExists(Paths.get(batScript));
        } catch (IOException e) {
            System.out.println("Error deleting existing script: " + e.getMessage());
            return;
        }

        try {
            URL url = new URL(URL);
            try (InputStream in = url.openStream(); FileOutputStream out = new FileOutputStream(saveDir)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            System.out.println("Error downloading update: " + e.getMessage());
            return;
        }

        try {
            Path zipPath = Paths.get(saveDir);
            Path destDir = zipPath.getParent();

            try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(saveDir))) {
                ZipEntry entry;
                while ((entry = zipIn.getNextEntry()) != null) {
                    Path outputPath = destDir.resolve(entry.getName());
                    if (entry.isDirectory()) {
                        Files.createDirectories(outputPath);
                    } else {
                        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath.toFile()))) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = zipIn.read(buffer)) != -1) {
                                bos.write(buffer, 0, length);
                            }
                        }
                    }
                    zipIn.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println("Error extracting update: " + e.getMessage());
            return;
        }

        String sourceDirPath = path + "/windows" + latestVersion;

        Path stkFilePath = Paths.get(sourceDirPath, "stk.bat");
        Path jarFilePath = Paths.get(sourceDirPath, "SuperToolKit.jar");

        Path stkDestPath = Paths.get(path, "stk.bat");
        Path jarDestPath = Paths.get(path, "SuperToolKit.jar");

        String batContent = "@echo off\n" +
                "timeout /t 2 >nul\n" +
                "del \"" + jarDestPath.toString() + "\"\n" +
                "del \"" + stkDestPath.toString() + "\"\n" +
                "move \"" + jarFilePath.toString() + "\" \"" + jarDestPath.toString() + "\"\n" +
                "move \"" + stkFilePath.toString() + "\" \"" + stkDestPath.toString() + "\"\n" +
                "rd /S /Q \"" + sourceDirPath + "\"\n" +
                "start \"\" \"" + jarDestPath.toString() + "\"\n" +
                "exit";

        try {
            Files.deleteIfExists(Paths.get(saveDir));
        } catch (IOException e) {
            System.out.println("Error cleaning up: " + e.getMessage());
            return;
        }

        try {
            Files.write(Paths.get(batScript), batContent.getBytes());
            Runtime.getRuntime().exec("cmd /c start " + batScript);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error executing update script: " + e.getMessage());
        }
    }

    private static String getSuperToolKitPath() {
        try {
            String jarPath = Updater.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File jarFile = new File(jarPath);
            return jarFile.getParent();
        } catch (Exception e) {
            System.out.println("Error determining toolkit path: " + e.getMessage());
            return "";
        }
    }
}