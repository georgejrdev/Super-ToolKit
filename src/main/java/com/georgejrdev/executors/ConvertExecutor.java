package com.georgejrdev.executors;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.georgejrdev.utils.helper.AppLogger;

public class ConvertExecutor {

    private static final Logger logger = AppLogger.getLogger();

    public void convertImage(String pathFile, String newFormat) {
        logger.info("Converting image" + pathFile + " to " + newFormat);

        try {
            File inputFile = new File(pathFile);
            BufferedImage image = ImageIO.read(inputFile);
            File outputFile = new File(inputFile.getParent(), getFileNameWithoutExtension(inputFile) + "." + newFormat);
            ImageIO.write(image, newFormat, outputFile);

            System.out.println("Success image convert - Local: " + outputFile.getParent());
            logger.info("Success! Image"+pathFile+" converted to "+newFormat+"saved in "+outputFile.getParent());
        } 
        
        catch (IOException e) {
            System.out.println("Error while trying to convert");
            logger.severe("Error while trying to convert image: " + pathFile + " to " + newFormat + " - " + e.getMessage());
        }
    }

    public void convertVideo(String pathFile, String newFormat) {
        logger.info("Converting video" + pathFile + " to " + newFormat);

        try {
            File file = new File(pathFile);
            String folderPath = file.getParent();
            
            String command = "ffmpeg -i " + pathFile + " " + folderPath + "/" + getFileNameWithoutExtension(file) + "." + newFormat;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("Success video convert - Local: " + folderPath);
            logger.info("Success! Video"+pathFile+" converted to "+newFormat+"saved in "+folderPath);
        }
        
        catch (IOException | InterruptedException e) {
            System.out.println("Error while trying to convert. Check if you have ffmpeg installed on your system");
            logger.severe("Error while trying to convert video: " + pathFile + " to " + newFormat + " - " + e.getMessage());
        }
    }

    public void convertAudio(String pathFile, String newFormat) {
        logger.info("Converting audio" + pathFile + " to " + newFormat);

        try {
            File file = new File(pathFile);
            String folderPath = file.getParent();

            String command = "ffmpeg -i " + pathFile + " " + folderPath + "/" + getFileNameWithoutExtension(file) + "." + newFormat;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("Success audio convert - Local: " + folderPath);
            logger.info("Success! Audio"+pathFile+" converted to "+newFormat+"saved in "+folderPath);
        } 
        
        catch (IOException | InterruptedException e) {
            System.out.println("Error while trying to convert. Check if you have ffmpeg installed on your system");
            logger.severe("Error while trying to convert audio: " + pathFile + " to " + newFormat + " - " + e.getMessage());
        }
    }

    private String getFileNameWithoutExtension(File file) {
        String name = file.getName();
        return name.substring(0, name.lastIndexOf('.'));
    }
}