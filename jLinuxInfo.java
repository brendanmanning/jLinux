
/**
 * A class mostly just so i remember to update the version number. 
 * The main method in 'main' class should call version() to output the correct version number.
 * 
 * @author (Brendan Manning) 
 * @version (2/12/2016)
 */
import java.io.*;
public class jLinuxInfo
{
   public static String version() {
       return "jLinux 2.5.1";
    }
   public static String developer() {
       return "Brendan Manning (c) 2016";
    }
   public static String downloadLocation() {
        return "http://www.jlinux.net/download/latest/latest.jar";
   }
   public static String appsLocation() {
       return System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "applications" + File.separator;
    }
   public static String hddLocation() {
       return System.getProperty("user.dir") + File.separator + "hdd" + File.separator;
   }
   public static String configLocation() {
       return System.getProperty("user.dir") + File.separator + "hdd" + File.separator + ".config" + File.separator;
   }
   public static String overrideUsernameLocation() {
       return System.getProperty("user.dir") + File.separator + "hdd" + File.separator + ".config" + File.separator + "overrideSystemUsername.jLinuxBoolean";    }
}
