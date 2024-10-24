package com.georgejrdev.executors;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ConvertExecutor {

    public void convertImage(String pathFile, String newFormat) {
        try {
            File inputFile = new File(pathFile);
            BufferedImage image = ImageIO.read(inputFile);
            File outputFile = new File(inputFile.getParent(), getFileNameWithoutExtension(inputFile) + "." + newFormat);
            ImageIO.write(image, newFormat, outputFile);

            System.out.println("Success image convert - Local: " + outputFile.getParent());
        } 
        
        catch (IOException e) {
            System.out.println("Error while trying to convert");
        }
    }

    public void convertVideo(String pathFile, String newFormat) {
        try {
            File file = new File(pathFile);
            String folderPath = file.getParent();
            
            String command = "ffmpeg -i " + pathFile + " " + folderPath + "/" + getFileNameWithoutExtension(file) + "." + newFormat;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("Success video convert - Local: " + folderPath);
        }
        
        catch (IOException | InterruptedException e) {
            System.out.println("Error while trying to convert. Check if you have ffmpeg installed on your system");
        }
    }

    public void convertAudio(String pathFile, String newFormat) {
        try {
            File file = new File(pathFile);
            String folderPath = file.getParent();

            String command = "ffmpeg -i " + pathFile + " " + folderPath + "/" + getFileNameWithoutExtension(file) + "." + newFormat;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("Success audio convert - Local: " + folderPath);
        } 
        
        catch (IOException | InterruptedException e) {
            System.out.println("Error while trying to convert. Check if you have ffmpeg installed on your system");
        }
    }

    private String getFileNameWithoutExtension(File file) {
        String name = file.getName();
        return name.substring(0, name.lastIndexOf('.'));
    }
}