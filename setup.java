
/**
 * Write a description of class setup here.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.4)
 */
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
public class setup
{
    public static void doSetup() {
        Scanner i = new Scanner(System.in);
        if(jLinuxInfo.guiEnabled() == false) {
            System.out.println("Are you sure you would like to setup jLinux again?");
            System.out.println("Doing so will reset all previous preferences");
        }
        if(jLinuxInfo.guiEnabled()) {
            o.echo(true, "Are you sure you would like to setup jLinux again?\nDoing so will reset all previous preferences\n(You will be prompted on the next screen)");
        }
        String answer = "";
        if(jLinuxInfo.guiEnabled() == true) {
            answer = JOptionPane.showInputDialog("Would you like to proceed? (yes or no): ");
        } else {
            System.out.println("Would you like to proceed? (yes or no): ");
            answer = i.nextLine();
        }
        if(answer.equals("yes")) {
            File config = new File(jLinuxInfo.configLocation());
            main.deleteFolder(config); //delete old config folder
            main.setupJLinux(); //setup jLinux again
        } else {
            o.echo(jLinuxInfo.guiEnabled(), "Aborting...");
        }
    }
}
