
/**
 * Update class for the update command
 * can't actually do anything yet except for printing upgrade instaructions.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.5)
 */
public class update
{
   public static void doUpdate() {
       if(System.getProperty("os.name").toLowerCase().contains("windows")) {
           System.out.println("To update jLinux, run jLinuxUpdater.exe in you jLinux install folder");
           System.out.println("*jLinux install folder is C:\\Program Files\\jLinux");
       } else {
           System.out.println("To update jLinux, download the latest JAR file from jLinux.net and replace your existing .jar with it");
       }
       
       
   }
}
