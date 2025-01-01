package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.georgejrdev.executors.QRCodeGeneratorExecutor;

public class TestQRCodeGenerator {


    @Test
    public void testQRCodeGenerator(){
        final String URL = "https://github.com/georgejrdev";
        final String PATH = "./src/test/resources";

        boolean result = QRCodeGeneratorExecutor.generateQRCode(URL, PATH);
        assertEquals(true, result);
    }
}
