
/**
 * deletes everything in hdd folder and starts over
 * 
 * @author (Brendan Manning) 
 * @version (nuke - jLinux Version 2.4.0)
 */
import java.io.*;
import java.util.Scanner;
public class nuke
{
    public static void doNuke() {
        Scanner i = new Scanner(System.in);
        System.out.println("*** WARNING!! ***");
        System.out.println("NUKE PERMENANTLY DELETES EVERYTHING IN YOUR JLINUX FOLDER!");
        System.out.println("jLinux is not responsible for anything you lose by running this command!");
        System.out.print("ARE YOU SURE?? (YES/no): ");
        String answer = i.nextLine();
        if(answer.equals("YES")) {
            File hddFolder = new File(jLinuxInfo.hddLocation());
            main.deleteContents(hddFolder);
            System.out.println("Deleted HDD folder contents!");
            System.out.println("Relaunching jLinux!");
            main._doSetup(jLinuxInfo.hddLocation());
        } else {
            System.out.println("Aborting nuke command...");
            System.out.println("None of your files have been changed!");
        }
    }
}
