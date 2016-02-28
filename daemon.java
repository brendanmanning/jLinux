
/**
 * Checks for and runs daemons
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.5)
 */
import java.io.*;
import java.util.Calendar;
public class daemon
{
    public static boolean doDaemon() {
        File enabledFile = new File(jLinuxInfo.configLocation() + "daemons.jLinuxBoolean");
        File minuteDaemon = new File(jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean");
        int actuallyRan = 0;
        if(enabledFile.exists()) {
            //daemons are enabled
            //System.out.println(""); //print a new line
            //System.out.println("1");
            if(minuteDaemon.exists()) {
                FileReader filereader;
                //System.out.println("2");
                try {
                    filereader = new FileReader(jLinuxInfo.configLocation() + "daemon-m.jLinuxBoolean");
                    BufferedReader br = new BufferedReader(filereader);
                    String line = null;
                    try {
                        
                        //System.out.println("*** Daemon Output ***");
                        while((line = br.readLine()) != null) {
                            if(line.equals("")) {
                                //check if line is empty
                                //if that's the case, don't do anything
                            } else {
                                System.out.println("---------------------");
                                loadApp.run(line, jLinuxInfo.hddLocation(), "");
                                /* Added a blank wd to Loadapp reference. It won't need that info, but it still must be passed in */
                                actuallyRan++;
                            }
                        }
                        //System.out.println("*** End Daemon Output");
                        //now re-show the shell prompt to not confuse the user
                        if(actuallyRan > 0) {
                            String cwd = main.getwd();
                            System.out.print(main.getUsername() + "@jLinux: " + main.getwd() + "$ ");
                        }
                    } catch (IOException ioe) {
                        System.out.println("IOE ERROR");
                        return false;
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println("fnfe error");
                    return false;
                }
            }
            return true;
        } else {
            //daemons are disabled
            return true; //still return true though, because if we don't messages that the daemon didn't run will annoy the user
        }
    }
}
