
/**
 * Update class for the update command
 * can't actually do anything yet except for printing upgrade instaructions.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.5)
 */
import java.io.*;
public class update
{
   public static void doUpdate() {
       if(System.getProperty("os.name").toLowerCase().contains("windows")) {
           System.out.println("To update jLinux, run jLinuxUpdater.exe in you jLinux install folder");
           System.out.println("*jLinux install folder is C:\\Program Files\\jLinux");
           try {
               if(new File("C:\\Program Files\\jLinux\\jLinuxUpdater.exe").exists()) {
                   Open o = new Open();
                   o.setType(1); //file
                   o.doOpen("C:\\Program Files\\jLinux\\jLinuxUpdater.exe");
                }
            } catch (Exception e) {
                log.log("Couldn't open updater for Windows");
                //fail silently
            }
       } else {
           System.out.println("To update jLinux, download the latest JAR file from jLinux.net and replace your existing .jar with it");
       }
       
       
   }
   public static boolean checkForUpdate() {
       //dl.get("http://www.jlinux.net/download/version/number.txt");\
       return false;
    }
}
