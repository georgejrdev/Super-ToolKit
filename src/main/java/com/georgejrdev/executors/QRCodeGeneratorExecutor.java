package com.georgejrdev.executors;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Logger;

import com.georgejrdev.utils.helper.AppLogger;
import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

public class QRCodeGeneratorExecutor {

    private static final Logger logger = AppLogger.getLogger();
    
    public static boolean generateQRCode(String url, String savePath){

        if (!(new File(savePath).exists())) {
            System.out.println("QRCode generator error: Path does not exist. Please create the folder and try again.");
            logger.warning("QRCode generator error: Path does not exist. Please create the folder and try again.");
            return false;
        }
        
        final String PATH = savePath + File.separator + "QRCode.png";

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);

            Path path = FileSystems.getDefault().getPath(PATH);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            
            System.out.println("QRCode saved at: " + PATH);
            return true;
        }

        catch (WriterException | IOException e) {
            System.out.println("Error generating QR code. You can see more details in the log file located at" + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Error generating QR code");
            logger.fine("Stack trace for the error:\n" + sw.toString());

            return false;
        }
    }
}