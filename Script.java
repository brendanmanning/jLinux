
/**
 * Enables a program to run multiple commands from a file
 * 
 * @author (Brendan Manning) 
 * @version (4/6/2016)
 */
/* This class throws errors but doesn't catch them */
import java.io.*;
public class Script 
{
    /* Create a string for holding the path to the file */
    private static String f;
    /* Create an array for storing all commands */
    private static String[] commands;
    /* Create an int to count how many commands were successfully */
    private static int ranOk = 0;
    /* String to hold entire file */
    private static String file = "";
    /* String to hold line as we run through file */
    private static String line = "";
    /**
     * Script contructor takes no parameters
     */
    public Script() 
    {
        //Do nothing yet
    }
    public static void scrpt(String s) {
        /* Set the path for the script to parameter of this function */
        if(s.startsWith(File.separator)) {
            System.out.println("Path must be relative!");
        } else {
            f = jLinuxInfo.hddLocation() + s;
            System.out.println(f);
        }
    }
    public static void doRun() throws FileNotFoundException, IOException {
       /* Use the geFileText method in utils */
       file = utils.getFileText(f);
       /* Ok, now split the file on new line */
       commands = file.split("\n");
       /* Now iterate over the array and run the commands */
       for(int i = 0; i < commands.length; i++) {
          loadApp.run(commands[i], jLinuxInfo.hddLocation(), main.getwd());
          log.log("Trying to run app " + commands[i]);
        }
        
        
    }
}
