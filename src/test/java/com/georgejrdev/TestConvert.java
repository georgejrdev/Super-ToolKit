package com.georgejrdev;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.executors.ConvertExecutor;

public class TestConvert {

    private ConvertExecutor convertExecutor;

    @Before
    public void setUp(){
        convertExecutor = new ConvertExecutor();
    }

    @Test
    public void testConvertImage(){
        String originalImagePath = "./src/test/resources/image.png";
        String convertImagePath = "./src/test/resources/image.gif";

        convertExecutor.convertImage(originalImagePath, "gif");

        Path resultPathFile = Paths.get(convertImagePath);
        boolean fileExist = Files.exists(resultPathFile);
        assertEquals(true,fileExist); 

        if (fileExist){
            try {
                Files.delete(resultPathFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testConvertVideo(){
        String originalVideoPath = "./src/test/resources/video.mp4";
        String convertVideoPath = "./src/test/resources/video.mov";

        convertExecutor.convertVideo(originalVideoPath, "mov");

        Path resultPathFile = Paths.get(convertVideoPath);
        boolean fileExist = Files.exists(resultPathFile);
        assertEquals(true,fileExist); 

        if (fileExist){
            try {
                Files.delete(resultPathFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testConvertAudio(){
        String originalAudioPath = "./src/test/resources/audio.mp3";
        String convertAudioPath = "./src/test/resources/audio.wav";

        convertExecutor.convertAudio(originalAudioPath, "wav");

        Path resultPathFile = Paths.get(convertAudioPath);
        boolean fileExist = Files.exists(resultPathFile);
        assertEquals(true,fileExist); 

        if (fileExist){
            try {
                Files.delete(resultPathFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
