
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
       /* First disable GUI.
        * just for now so that the diagnostic information still shows up
        */
       int gui;
       if(jLinuxInfo.guiEnabled()) {
           jLinuxInfo.guiOff();
           gui = 1;
        } else {
            gui = 0;
        }
       o.echo(false, "The following apps will be updated:");
       String appName;
       List<String> allInstalled = listapps.list();
       File f;
       /* boolean to see if the downloaded completed */
       boolean appInstalled;
       int okApps = 0; //apps installed successfully
       int totalApps = 0; //apps attempted to download
       
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
           appInstalled = addapp.install(appName);
           /* check if app installed */
           if(appInstalled == true) {
               okApps++;
               totalApps++;
            } else {
                totalApps++;
            }
        }
        if(gui == 1) {
           jLinuxInfo.guiOn();
            o.echo(jLinuxInfo.guiEnabled(), "App updates finished!\n" + okApps + " of " + totalApps + " installed successfully!");
        } else {
            o.echo(false, "App updates complete!\n" + okApps + " of " + totalApps + " installed successfully!");
        }
       return true;
   }
}
