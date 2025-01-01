package com.georgejrdev.utils.helper;

import java.util.ArrayList;
import java.util.List;

public class ChangeLog {

    static private List<String> CHANGE_LOG = new ArrayList<String>(){{
        // Structure: add("Version: 0.0.0 - dd/mm/AAAA \n\n Features: \n -<feature here>. \n\n Bug Fixes: \n -No bugs fixed.");
        add("Version: 4.2.0 - 01/01/2025 \n\n Features: \n -Add command 'qrcode' to generate a QRCode. \n\n Bug Fixes: \n -No bugs fixed.");
        add("Version: 4.3.0 - 02/01/2025 \n\n Features: \n -Add command ramviewer. \n -Add command changelog. \n\n Bug Fixes: \n -Bug that incorrectly saved information about the arguments passed to the program in the log file at the time of the error. \n -Spelling error in the description of the 'qrcode' command in the README.");
    }};

    public static void showChangeLog(boolean all){
        if (all) {
            System.out.println("\n-----------------------------------------------------\n");
            for (String change : CHANGE_LOG) {
                System.out.println(change);
                System.out.println("\n-----------------------------------------------------\n");
            }
        }
        else {
            System.out.println("\n-----------------------------------------------------\n");
            System.out.println(CHANGE_LOG.get(CHANGE_LOG.size() - 1));
            System.out.println("\n-----------------------------------------------------\n");
        }
    }
}