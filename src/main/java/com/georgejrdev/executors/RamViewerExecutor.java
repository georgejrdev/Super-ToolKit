package com.georgejrdev.executors;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import com.georgejrdev.utils.helper.AppLogger;

public class RamViewerExecutor {

    private static long biggerValue = 0;
    private static final Logger logger = AppLogger.getLogger();

    public static void view() {

        logger.info("Executing command ram-viewer");

        System.out.println("\nPress 'Ctrl+C' to stop\n");

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Maximum used memory: " + biggerValue + " MB");
            System.out.println("-----------------------------------------------------------");
        }));

        while (true) {
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

        System.out.println(" | Total memory: " + totalMemorySize + " MB | Free memory: " + freeMemorySize + " MB | Used memory: " + usedMemorySize + " MB");
    }

    private static long bytesToMBRounded(long bytes) {
        return bytes / (1024 * 1024); 
    }
}