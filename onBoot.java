
/**
 * onBoot runs boot scripts from .config/onBoot.jlf
 * Scripts are run after the jLinux core is loaded and before the first shell prompt is shown
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.9)
 */
import java.io.*;
import java.util.*;
public class onBoot
{
    public static boolean bootApps() {
        /* Ensure everything is all right for boot apps */
        if((new File(jLinuxInfo.bootScriptsLocation())).exists())
        {
            return runApps(); //All good! Run runApps() and return it's value to the method calling this one
        } else {
            log.log("[ debug ] Not running boot scripts because they aren't enabled\nCreate file " + jLinuxInfo.bootScriptsLocation() + " to enable them");
            /* Fail silently because the user doesn't need to be bothered by this */
            return false;
        }
    }
    private static boolean runApps() {
       /* Checks if there are any scripts to run */
       String f = jLinuxInfo.bootScriptsLocation();
       /* Use the geFileText method in utils */
       String file = utils.getFileText(f);
       /* Ok, now split the file on new line */
       String commands[] = file.split("\n");
       /* Now iterate over the array and run the commands */
       
       /* Boolean for telling if any commands worked */
       boolean ok = false;
       for(int i = 0; i < commands.length; i++) {
          if(commands[i].toLowerCase().startsWith("cd")) {
              /* Add this code later */
          }
          if(loadApp.run(commands[i], jLinuxInfo.hddLocation(), main.getwd()) == true) {
              ok = true;
          }
          log.log("Trying to run app " + commands[i]);
        }
        /* If any commands worked successfully, return true*/
       if(ok == true) {
            return true;
       } else { /* Otherwise, return false */
            return false;
       }
    }
    public static boolean setupFunc() {
        Scanner n = new Scanner(System.in);
        System.out.println("Turn on Bootscripts?\nBootscripts are apps you can choose to run when starting jLinux\nAlways like to know the time, add 'time' as a bootscript");
        System.out.println("Enable bootscripts? (y/n)");
        String a = n.nextLine();
        if(a.equals("y")) {
           /* Create the bootscript file */
           File f = new File(jLinuxInfo.bootScriptsLocation());
           if(f.exists()) {
               System.out.println("File already exists!");
           } else {
               try {
                   f.createNewFile();
                   System.out.println("Ok!\nWould you like to add 'time' as a bootscript? (y/n)");
                   a = n.nextLine();
                   if(a.equals("y")) {
                       try {
                          utils.writeToFile(jLinuxInfo.bootScriptsLocation(), "time");
                       } catch (Exception eee) {
                           System.out.println("Couldn't add bootscript 'time'");
                       }
                   } else {
                       System.out.println("No bootscripts? That's all right! You can enabled them at anytime by re-running setup");
                    }
               } catch (Exception e) {
                   System.out.println("Sorry, an error occured!");
                   try {
                       log.log("Couldn't create bootscript file");
                       return false;
                    } catch (Exception ee) {
                        System.out.println("[ error ] Couldn't write to error log");
                        return false;
                    }
                }
            }
        } else {
            try {
                log.log("User declined bootscripts");
                System.out.println("Not enabling bootscripts");
            } catch (Exception ex) {
                System.out.println("Error! Bootscrips not enabled");
            }
            return false;
        }
        return true;
    }
}
