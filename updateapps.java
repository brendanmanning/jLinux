
/**
 * Updates every 3rd party app from the appstore by getting the newest version
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.5)
 */

import java.util.*;
import java.io.*;
public class updateapps
{
   public static boolean doUpdate() {
       System.out.println("The following apps will be updated:");
       String appName;
       List<String> allInstalled = listapps.list();
       File f;
       String[] apps = new String[allInstalled.size()];
       /* convert the list to an array */
       for (int b = 0; b<allInstalled.size(); b++) {
           apps[b] = allInstalled.get(b);
        }
       for (int i = 0; i < apps.length; i++) {
           System.out.println("Deleting file: " + apps[i]);
           f = new File(apps[i]);
           main.deleteContents(f);
           System.out.println("Downloading latest version to: " + apps[i]);
           appName = f.getName();
           appName = appName.replace(".jar", ""); //remove .jar from end of string
           System.out.println("Installing " + appName);
           addapp.install(appName);
        }
        System.out.println("App updates finished!");
       return true;
   }
}
