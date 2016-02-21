
/**
 * Adds to the daemon list
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.5)
 */
import java.util.Scanner;
import java.io.*;
public class liveupdate
{
    public static boolean daemonAdd() throws FileNotFoundException {
        FileWriter out = null;
        //System.out.println(jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean");
        try {
            String daemonOut = jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean"; 
            System.out.println(daemonOut);
            out = new FileWriter(daemonOut,true); //the true will append the new data
        } catch (IOException ioe) {
            System.out.println("Error! Daemon not added!");
            return false;
        }
        //System.out.println(""); //print new line
        System.out.print("Daemon to add >> ");
        Scanner i = new Scanner(System.in);
        String daemon = i.nextLine();
        File app = new File(jLinuxInfo.appsLocation() + daemon + ".jar");
        if(!app.exists()) { //app doesn't exist
            System.out.println("Sorry, that app (" + daemon + ")is not installed!");
        } else {
            try {
                out.write(daemon + "\n");//appends the string to the file
                out.close();
                System.out.println("Daemon '" + daemon + "' added!");
            } catch (IOException ioe) {
                System.out.println("[ error ] Internal Java error! Daemon not added!");
            }
        }
        //daemonThread.main(null);
        
        return true;
        
    }
    public static boolean addDaemonByName(String name) throws FileNotFoundException {
        if(name.equals(null)) {
            System.out.println("[ error ] No daemon name specified!");
            return false;
        }
        FileWriter out = null;
        //System.out.println(jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean");
        try {
            String daemonOut = jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean"; 
            System.out.println(daemonOut);
            out = new FileWriter(daemonOut,true); //the true will append the new data
        } catch (IOException ioe) {
            System.out.println("Error! Daemon not added!");
            return false;
        }
        String daemon = name;
        File app = new File(jLinuxInfo.appsLocation() + daemon + ".jar");
        if(!app.exists()) { //app doesn't exist
            System.out.println("Sorry, that app (" + daemon + ")is not installed!");
        } else {
            try {
                out.write(daemon + "\n");//appends the string to the file
                out.close();
                System.out.println("Daemon '" + daemon + "' added!");
            } catch (IOException ioe) {
                System.out.println("[ error ] Internal Java error! Daemon not added!");
            }
        }
        //daemonThread.main(null);
        
        return true;
    }
}
