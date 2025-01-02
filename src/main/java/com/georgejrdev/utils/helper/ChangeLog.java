package com.georgejrdev.utils.helper;

import java.util.ArrayList;
import java.util.List;

import static com.georgejrdev.DefaultValues.*;

public class ChangeLog {

    static private List<String> CHANGE_LOG = new ArrayList<String>() {{
        // Structure: 
        // add(ANSI_CYAN + "Version: 0.0.0 - dd/mm/AAAA" + ANSI_RESET + 
        // "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
        // "\n -<feature here>." + 
        // "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
        // "\n -No bugs fixed.");

        add(ANSI_CYAN + "Version: 4.2.0 - 01/01/2025" + ANSI_RESET +
            "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
            "\n -Add command 'qrcode' to generate a QRCode." + 
            "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
            "\n -No bugs fixed.");

        add(ANSI_CYAN + "Version: 4.3.0 - 01/01/2025" + ANSI_RESET +
            "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
            "\n -Add command 'ramviewer' to view RAM usage." + 
            "\n -Add command 'changelog' to view the change log." + 
            "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
            "\n -Bug that incorrectly saved information about the arguments passed to the program in the log file at the time of the error." + 
            "\n -Spelling error in the description of the 'qrcode' command in the README.");

        add(ANSI_CYAN + "Version: 4.3.1 - 01/01/2025" + ANSI_RESET +
            "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
            "\n -No new features (only bug fixes)." + 
            "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
            "\n -Duplicate dot when passing new file format (like .mov) in convert command." + 
            "\n -Incorrect date in the change log of version 4.3.0.");

        add(ANSI_CYAN + "Version: 4.3.2 - 02/01/2025" + ANSI_RESET +
            "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
            "\n -Add color on ram viewer." + 
            "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
            "\n -No bugs fixed.");

        add(ANSI_CYAN + "Version: 4.4.0 - 02/01/2025" + ANSI_RESET + 
            "\n\n" + ANSI_GREEN + "Features:" + ANSI_RESET + 
            "\n -Fully added colors to CLI." + 
            "\n -Add command 'key' to manipulate (add, remove, list) secret keys." + 
            "\n\n" + ANSI_RED + "Bug Fixes:" + ANSI_RESET + 
            "\n -No bugs fixed.");
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