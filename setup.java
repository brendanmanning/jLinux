
/**
 * Write a description of class setup here.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.4)
 */
import java.io.*;
import java.util.Scanner;
public class setup
{
    public static void doSetup() {
        Scanner i = new Scanner(System.in);
        System.out.println("Are you sure you would like to setup jLinux again?");
        System.out.println("Doing so will reset all previous preferences");
        System.out.print("Would you like to proceed? (yes or no): ");
        String answer = i.nextLine();
        if(answer.equals("yes")) {
            File config = new File(jLinuxInfo.configLocation());
            main.deleteFolder(config); //delete old config folder
            main.setupJLinux(); //setup jLinux again
        } else {
            System.out.println("Aborting...");
        }
    }
}
