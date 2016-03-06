/**
 * Returns basic information about how jLinux is setup^1
 * 
 * @author (Brendan Manning) 
 * @version (v1)
 */
import java.io.*;
import java.awt.*;
public class info
{
    /* Folder where jLinux stores all the user's files */
    private static String dataFolder = System.getProperty("user.home") + "hdd";
    /* folder where downloaded apps go */
    private static String appFolder = System.getProperty("user.home") + "applications";
    /* returns the host os */
    private static String os = System.getProperty("os.name");
    /* return the file separator ('/' or '\') */
    private static String slash = File.separator;
    /* Gets if the system is headless*/
    private static boolean headless = GraphicsEnvironment.isHeadless();
    /* Method to return data info below : */
    
    /* returns the hdd folder location */
    public static String dataFolder() {
        return dataFolder;
    }
    /* returns the application folder */
    public static String appFolder() {
        return appFolder;
    }
    /* returns the host os */
    public static String hostOS() {
        return os;
    }
    /* returns the file separator */
    public static String getSlash() {
        return slash;
    }
    /* returns if the system supports a graphical user interface based on whether it's headless or not */
    public static boolean isGUI() {
        if(headless == true)
        {
            return false;
        }
        if(headless == false) {
            return true;
        }
        /* this point should never be reached.
         * However Java requires it's presence for compilation
         */
        return false;
    }
}
/* ^1 Jalopy cannot determine with completley certainty that things are setup the way it expects 
 * Manual modification may change various file locations, etc.
 * However for 99.9% of setup's these values will work.
 */ 
