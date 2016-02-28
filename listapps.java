
/**
 * ListApps - lists all 3rd party installed apps
 * 
 * @author (Brendan Manning) 
 * @version (0.0.1)
 */
import java.io.*;
import java.lang.*;
import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class listapps
{
    public static void main(String[] args) {
        //this method should never get called
        //but if it does, show a copyright notice
        System.out.println("ListApps - part of jLinux (c) 2016 Brendan Manning");
    }
    public static List list() {
        List apps = new ArrayList();
        
        String fName;
        File appsFolder = new File(jLinuxInfo.appsLocation());
        File[] appsList = appsFolder.listFiles();
        //System.out.println("The following apps are currently installed:");
        for (int i = 0; i < appsList.length; i++) {
            if(appsList[i].isFile()) {
                fName = appsList[i].getName();
                if(fName.startsWith(".")) {
                    //ignore dot files
                } else {
                    System.out.println("[" + fName.replace(".jar", "") + "]");
                    apps.add(appsList[i].getAbsolutePath());
                    
                }
            }
        }
        return apps;
    }
}
