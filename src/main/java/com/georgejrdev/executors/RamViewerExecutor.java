package com.georgejrdev.executors;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class RamViewerExecutor {

    private static boolean running = true;
    private static long biggerValue = 0;
    private static final Logger logger = AppLogger.getLogger();

    public static void view() {

        logger.info("Executing command ram-viewer");

        System.out.println("\nPress 'Ctrl+C' to stop\n");

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            running = false;
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Maximum used memory: "+ ANSI_RED + biggerValue + " MB" + ANSI_RESET);
            System.out.println("-----------------------------------------------------------");
        }));

        while (running) {
            print();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void print() {
        OperatingSystemMXBean osBean = 
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        long totalMemorySize = bytesToMBRounded(osBean.getTotalMemorySize());
        long freeMemorySize = bytesToMBRounded(osBean.getFreeMemorySize());
        long usedMemorySize = totalMemorySize - freeMemorySize;

        if (usedMemorySize > biggerValue) {
            biggerValue = usedMemorySize;
        }

        System.out.println(
        " | Total memory: " + ANSI_CYAN + totalMemorySize + " MB" + ANSI_RESET +
        " | Free memory: " + ANSI_GREEN + freeMemorySize + " MB" + ANSI_RESET +
        " | Used memory: " + ANSI_RED + usedMemorySize + " MB" + ANSI_RESET
        );
    }

    private static long bytesToMBRounded(long bytes) {
        return bytes / (1024 * 1024); 
    }
}