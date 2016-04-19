/**
 * Main class - Entry point for jLinux.
 *            - when jLinux boots, main() is loaded
 * 
 * @author (Brendan Manning) 
 * @version (12/7/2015)
 * //
 * Program PIDs (deprecated)
 * cd - 1
 * mkdir - 2
 * vinf - 3
 * quit - 88
 */
import java.util.*;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.net.*;
public class main
{
    //declare working directory string first
    public static String wd = null;
    /* next create a method to return the wd variable to any class that wants it */
    public static String getwd() {
        return wd;
    }
    /* code for delete functions taken from: http://stackoverflow.com/questions/7768071/how-to-delete-directory-content-in-java */
    public static void deleteContents(File path) {
        File[] files = path.listFiles();
        if(files != null) {
            for (File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }
    public static void deleteFolder(File path) {
        File[] files = path.listFiles();
        if(files != null) {
            for (File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        //finally, delete the folder itself
        path.delete();
    }
    public static String _cleanArg(String arg) {
                if(!arg.endsWith(File.separator)) {
                    arg = arg + File.separator;
                }
                return arg;
    }
    public static String _cleanWd(String wd) {
        if (!wd.endsWith(File.separator)) {
                   wd = wd + File.separator; 
        }
        return wd;
    }
    public static void _command_help(String arg) {
        if(arg.equals("*")) {
            System.out.println("#########Commands with help pages#########");
            System.out.println("----> cd");
            System.out.println("----> ls");
            System.out.println("----> mkdir");
            System.out.println("----> dl");
            System.out.println("----> vinf");
            System.out.println("----> addapp");
            System.out.println("----> dameon");
            System.out.println("----> setup");
            System.out.println("----> listapps");
            System.out.println("----> nuke");
            System.out.println("----> cp");
        } else if(arg.equals("cd")) {
            help.cdHelp();
        } else if (arg.equals("ls")) {
            help.lsHelp();
        } else if(arg.equals("mkdir")) {
            help.mkdirHelp();
        } else if(arg.equals("dl")) {
            help.dlHelp();
        } else if(arg.equals("vinf")) {
            help.vinfHelp();
        } else if(arg.equals("addapp")) {
            help.addappHelp();
        } else if(arg.equals("daemon")) {
            help.daemonHelp();
        } else if(arg.equals("setup")) {
            help.setupHelp();
        } else if(arg.equals("listapps")) {
            help.listappsHelp();
        } else if(arg.equals("nuke")) {
            help.nukeHelp();
        } else if(arg.equals("update")) {
            help.updateHelp();
        } else if(arg.equals("cp")) {
            help.cpHelp();
        } else {
            System.out.println("Command " + arg + " not found. Run help * to list all commands");
        }
    }
    public static void _command_ls(String wd, String arg) {
             String output = ""; //string to hold the output
                wd = _cleanWd(wd);
            
              arg = _cleanArg(arg);
             
              File directory = new File(wd + arg);
            // get all the files from a directory
            File[] fList = directory.listFiles();
        String fileName;
    for (int index = 0; index < fList.length; index++) {
                /* Style fixes */
        if(index != 0) {
            //if(index != fList.length - 1) {
              //  if(fList.length != 0) {
                    output += "\n";
                //}
            //}
        }
        fileName = fList[index].getName();
        if (fList[index].isFile()) {
            //if(!fileName.startsWith(".")) { //if not a dot file
                    output += "[file] " + fList[index];

            //}
        } else if (fList[index].isDirectory()) {
            //if(!fileName.startsWith(".")) { //ignore hidden folders
                output += "[directory] " + fList[index];
            //}
        } 
     }
     
     /* if nothing was output above */ 
     if(fList.length == 0) {
         /* Only show the message in GUI mode */
         if(jLinuxInfo.guiEnabled() == true) {
             output += "Nothing to show here :(";
         } else {
             /* set output to a message that jLinux will know to ignore */
             output = ";;;ignore;;;";
         }
     }
     /* Now output the list to the user */
     
     /* But only if there's something to show */
        if(!output.equals(";;;ignore;;;")) {
            o.echo(jLinuxInfo.guiEnabled(), output); /* using the gui extension if required */
        }
    }
    public static String _command_mkdir(String wd, String arg) {
        //clean up the command args
        if(!arg.endsWith(File.separator)) {
          arg = arg + File.separator;
        }
        //terminate if path is not relative
        if(arg.startsWith(File.separator)) {
            errors.path(arg);
            return "FAIL";
        }
        File newFolderLocation = new File(wd + arg);
        String newFolderString = wd + arg;
        if (newFolderLocation.exists()) {
           // folder already exists
           System.out.println("Folder already exists!");
           return newFolderString;
        } else {
           newFolderLocation.mkdir(); 
           System.out.println("Folder Created!");
           return newFolderString;
        }
    }
    public static String vinf_scan() {
         Scanner vinf_in = new Scanner(System.in);
          System.out.println("##################################");
          System.out.println("############Vi New File###########");
          String returned = vinf_in.nextLine();
           System.out.println("##################################");
            System.out.println("##################################");
           //System.out.println("TXT: " + returned);
           return returned;
    }
    public static int _command_vinf(String wd, String arg) throws java.io.FileNotFoundException {
        if (arg.startsWith(File.separator)) {
            errors.path(arg);
            return 0;
        }
        String vinfNewLocation = wd + arg;
        
        File vinfFile = new File(vinfNewLocation);
        if(vinfFile.exists() && !vinfFile.isDirectory()) { 
          // do something
          Scanner vinfScanner = new Scanner(System.in);
          System.out.println("File already exists. Would you like to append to it? (Y/n)");
          String vinfYn = vinfScanner.nextLine();
          if(vinfYn.equals("Y")) {
                  try
                        {
                            String vinfOut = vinfNewLocation;
                            FileWriter vinf_fw = new FileWriter(vinfOut,true); //the true will append the new data
                            vinf_fw.write(vinf_scan());//appends the string to the file
                            vinf_fw.close();
                        }
                            catch(IOException ioe)
                        {
                               errors.internal("vinf");
                               return 0;
                            }
              return 1;
            } else {
                return 0;
            }
         } else {
              try
                 {
                    String vinfOut = vinfNewLocation;
                    FileWriter vinf_fw = new FileWriter(vinfOut,true); //the true will append the new data
                    vinf_fw.write(vinf_scan());//appends the string to the file
                    vinf_fw.close();
                      }
                  catch(IOException ioe)
                    {
                    errors.internal("vinf");
                    return 0;
                  }
           return 1;
         }
    }
    public static String _command_cd(String baseDir, String wd, String cmd, String arg) throws java.lang.ArrayIndexOutOfBoundsException {
        //check for special conditions
        if (arg != "") {
            //all ok here
            //main logic for CD
                if(arg.equals("..")) {
                    wd = baseDir;
                    return wd;
                }
                if (!wd.endsWith(File.separator)) {
                   wd = wd + File.separator; 
                }
                if(!arg.endsWith(File.separator)) {
                    arg = arg + File.separator;
                }
                if(arg.startsWith(File.separator)) {
                    errors.path(arg);
                    return wd;
                }
                File cd2 = new File(wd + arg);
                if(!cd2.exists()) {
                    errors.other(wd + arg + " does not exist, or is a file.", "cd");
                    return wd;
                }
                wd = wd + arg;
                return wd;
            
        } else {
            wd = baseDir;
            return baseDir;
        }
        
        
    }
    public static void getNewJalopy(String downloadToPath) {
        /* Do nothing. This code is no longer nessecary, but the method will stay here for now just in case */
    }
    public static boolean doesJalopyExist() {
        return true;
    }
    public static void setupJLinux() {
        /* set the setup property */
        jLinuxInfo.doingSetupNow();
        for(int xy = 0; xy < 40; xy++) {
            System.out.println(""); //print new line
        }
        System.out.println("*** Setup jLinux ***");
        /* Download Jalopy API */
        System.out.println("Checking prerequisites...");
        if(doesJalopyExist() == false) {
            getNewJalopy(main.class.getProtectionDomain().getCodeSource().getLocation().getPath()); /* pass in path to the jLinux jar */
        } else {
            System.out.println("All good!");
        }
        
        System.out.println("Ready to setup!");
        boolean systemUsername;
        boolean defaultApps;
        String newUsername = "";
        File configFolder = new File(jLinuxInfo.configLocation());
        configFolder.mkdir();
        System.out.println(""); //print empty line
        Scanner in = new Scanner(System.in); //create a scanner object to use for the setup process
        System.out.print("Use your Windows/Mac/Linux username as your jLinux name [yes] (y/N): ");
        if(in.nextLine().equals("N")) {
            systemUsername = false;
            FileOutputStream fos;
            do {
                System.out.print("What username do you want then?: ");
                newUsername = in.nextLine();
            } while (newUsername.equals(""));
            try(  PrintWriter out = new PrintWriter(jLinuxInfo.configLocation() + "overrideSystemUsername.jLinuxBoolean")  ){
                out.print( newUsername );
            } catch (FileNotFoundException fnfe) {
                errors.fileNotFound("overrideSystemUsername.jLinuxBoolean");
                systemUsername = true;
            }
        } else {
            //use default system username
            System.out.println("Using system username...");
        }
        //ask user if they would like to install some basic apps from the store (that i still haven't made a front end for) (oops!) 
        System.out.print("Would you like to install some optional utility applications made by jLinux? [yes] (y/N): ");
        String choice = in.nextLine();
        if(choice.equals("y")) {
            //user wants to install
            System.out.println("Great! Applications: time, date, and properties will now download!");
            addapp.install("time");
            addapp.install("date");
            addapp.install("properties");
            System.out.println("Done installing apps....");
        } else if (choice.equals("N")) {
            System.out.println("Not installing optional apps");
            System.out.println("If you change your mind, you can install them by running: addapp <time/date/properties>");
        } else {
            System.out.println("You picked neither option. Assuming 'no'");
            System.out.println("If you change your mind, you can install them by running: addapp <time/date/properties>");
        }
        System.out.println("Would you like to enable live updates (daemons)?");
        System.out.println("Daemons allow for important updates of personal information by periodically running apps you choose");
        System.out.println("You can configure as many or as few daemons as you choose.");
        System.out.println("Would you like to enable live updates? [y] (y/N)");
        String liveUpdatesYN = in.nextLine();
        if(liveUpdatesYN.equals("N")) {
            System.out.println("Not enabling live updates!");
            System.out.println("If you change your mind, re-run setup to enable live updates!");
        } else {
            System.out.println("Enabling daemons (live updates)");
            File daemonsBool = new File(jLinuxInfo.configLocation() + "daemons.jLinuxBoolean");
            if(!daemonsBool.exists()) {
                try {
                    daemonsBool.createNewFile();
                } catch (IOException ioe) {
                    System.out.println("[ error ] Settings file couldn't be created!");
                }
            } else {
                System.out.println("[ info ] Live updates already enabled!");
            }
            System.out.println("Try out live updates?");
            System.out.println("Turn on time notifications via 'time' app? [y] (y/N)");
            String tryLive = in.nextLine();
            if(tryLive.equals("N")) {
                System.out.println("That's alright! You can always add a daemon with the 'liveupdate' app");
            } else {
                System.out.println("Adding 'time' daemon");
                File timeLocation = new File(jLinuxInfo.appsLocation() + "time.jar");
                if(timeLocation.exists()) {
                    System.out.println("App 'time' already installed");
                } else {
                    System.out.println("App 'time' not installed!");
                    addapp.install("time");
                }
                try {
                    liveupdate.addDaemonByName("time");
                } catch (FileNotFoundException fnfe) {
                    errors.other("[ error ] Could not add daemon!", "setup");
                }
            }
        }
        /* Ask the user if he/she wants bootscripts */
        /* Use onBoot.java's built in method to save space here */
        onBoot.setupFunc();
        /* All finished! */
        System.out.println("jLinux is now setup!");
        System.out.println("If you would like to change any of the options you just chose just run: setup");
        System.out.println("");
        /* If GUI was originally on, turn it off now because we have just re-setup jLinux */
        jLinuxInfo.guiOff();
        /* Now set the doing setup property to false */
        jLinuxInfo.notDoingSetup();
    }
    public static boolean isSystemSetup() {
       File configFolderLocation = new File(jLinuxInfo.configLocation());
       if(configFolderLocation.exists()) {
           return true;
       } else {
         return false;  
       }
    }
    public static boolean _doSetup(String baseDir) {
        File hddLocation = new File(System.getProperty("user.dir") + File.separator + "hdd");
        File downloadsFolder = new File(System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "downloads" + File.separator); 
        File appsFolder = new File(System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "applications" + File.separator);
        
        //File overrideUserName = new File(jLinuxInfo.configLocation() + "overrideSystemUsername.jLinuxBoolean");
        //moved this code to isSystemSetup method
        if (hddLocation.exists()) {
           //hdd folder exists
           if(jLinuxInfo.guiEnabled() == false) {
               System.out.println("[ ok ] HDD data folder exists!");
            }
        } else {
           hddLocation.mkdir(); 
           if(jLinuxInfo.guiEnabled() == false) {
               System.out.println("[ debug ] HDD folder was missing, so it has been created in your home folder");
               log.log("[ debug ] HDD folder was missing, so it has been created in your home folder");
            }
        }
        if(downloadsFolder.exists()) {
            //downloads folder exists
            if(jLinuxInfo.guiEnabled() == false) {
                System.out.println("[ ok ] User downloads folder exists");
            }
        } else {
            downloadsFolder.mkdir();
            //downloads folder did not exist so it was created
            if(jLinuxInfo.guiEnabled() == false) {
                System.out.println("[ debug ] User downloads folder was missing, so it was created!");
                log.log("[ debug ] User downloads folder was missing, so it was created!");
            }
        }
        if(appsFolder.exists()) {
            if(jLinuxInfo.guiEnabled() == false) {
                System.out.println("[ ok ] Applications folder exists");
            }
        } else {
            appsFolder.mkdir();
            if(jLinuxInfo.guiEnabled() == false) {
                System.out.println("[ debug ] Applications folder was missing, so it has been created!");
                log.log("[ debug ] Applications folder was missing, so it has been created!");
            }
        }
        daemonThread.main(null); //start daemon thread
        //silently check to see if jLinux config exists
         if(isSystemSetup() == false) {
            setupJLinux();
        }
        /*if(!jalopyJar.exists()) {
             Pass the current working directory of the running jLinux Jar 
            getNewJalopy(main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        } else {
            if(jLinuxInfo.guiEnabled() == false) {
                System.out.println("[ debug ] API Jar found!");
            }
        }*/
        return true; 
    }
    public static String getUsername() {
        String username = System.getProperty("user.name");
         //check if the username was overridden 
         File overrideFile = new File (jLinuxInfo.overrideUsernameLocation());
         if(overrideFile.exists()) {
             FileReader fr;
             try {
                 fr = new FileReader(jLinuxInfo.overrideUsernameLocation());
                 BufferedReader br = new BufferedReader(fr);
                 String line = null;
                 try {
                       while((line = br.readLine()) != null) {
                            username = line;
                       }
                 } catch (IOException ioe) {
                     errors.internal("Default Shell Service");
                     username = System.getProperty("user.name");
                 }
             } catch (FileNotFoundException fnfe) {
                errors.other("[ error ] Check for username override file failed!", "Default Shell Service");
                username = System.getProperty("user.name"); //default back to normal username
          }
        }
        /* once we're done finding the username, return it */
        return username;
    }
    public static boolean isGuiForced() {
        File guiBool = new File(jLinuxInfo.configLocation() + "forcegui.jLinuxBoolean");
        if(guiBool.exists()) {
            return true;
        }
        if(!guiBool.exists()) {
            return false;
        }
        /* this point will never be reached, but if it is, return false */
        return false;
    }
    public static String _shell(String wd) {
              /* Before scanning for terminal input, check to see if GUI input is enabled
               * if it is, use that instead
               */ 
              if(jLinuxInfo.guiEnabled()) {
                  return gui.showGUITerminal(wd);
              } else {
                  Scanner i = new Scanner(System.in);
                  System.out.print(getUsername() + "@jLinux: " + wd + "$ ");
                  String command = i.nextLine();
                  return command;
                }
    }
    public static void main(String[] args) throws java.lang.ArrayIndexOutOfBoundsException {
        /* create log folder if not exist */
        File logFolder = new File(jLinuxInfo.hddLocation() + "logs");
        if(!logFolder.exists()) {
            logFolder.mkdir();
        }
        /* create the lock log file just in case */
        File lckFile = new File(jLinuxInfo.logLocation());
        if(lckFile.exists()) {
            //do nothing
        } else {
            try {
                lckFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Couldn't enabled logger....");
                System.out.println("Disabling...");
                jLinuxInfo.noLog();
            }
        }
        /* Start the logging Thread */
        logThread.main(null);
        /* define basedir */
        String baseDir = System.getProperty("user.dir") + File.separator + "hdd" + File.separator;
        /* user is requesting root privlidges 
         * prompt for confirmation
         */
        if(args.length > 0) {
            if(args[0].equals("root")) {
                System.out.println("Logging in as root..");
                System.out.println("WARNING: Using root mode will disable jLinux features that limit jLinux to it's own directory\nThis will allow you to use jLinux utilities for any file on your system\nHowever this feature is untested.\nAre you sure you want to continue? (y/n) [y] : ");
                Scanner checker = new Scanner(System.in);
                String returned = checker.nextLine();
                if(returned.toLowerCase().equals("n")) {
                    System.out.println("Aborted! Logging in normally...");
                } else {
                    System.out.println("Logged in as root!");
                    baseDir = System.getProperty("user.dir");
                }
            }
        }
        //check if gui is being forced
        boolean guiForced = false;
        guiForced = isGuiForced();
        if(guiForced == true) {
            jLinuxInfo.guiOn();
        }
        //initilize core host system vars
        if(guiForced == false) {
            System.out.println("Booted Successfully!");
            log.log("System booted!");
            System.out.println("Checking if system set up");
        }
        String os = System.getProperty("os.name");
        boolean isSetup = _doSetup(baseDir);
        if(guiForced == false) {
            System.out.println("");
            System.out.println("----{" + jLinuxInfo.version() + "}----");
            System.out.println("");
            System.out.println("Use of jLinux is subject to the jLinux Liscense found at https://github.com/brendanmanning/jLinux/blob/master/LICENSE");
            System.out.println("");
            System.out.println("::::::::Need Help?::::::::");
            System.out.println("Run help * to list all commands, or help + command name to get command specific help");
        }
        if(guiForced == true) {
            o.echo(true, "Booted!\n--{jLinux " + jLinuxInfo.version() + "}--");
        }
        //System.out.println("");
        
        /* Before showing the first shell prompt, be sure to call the boot scripts */
        onBoot.bootApps();
        /*This calls the method which will search for and run any bootscripts
         * No filtering is needed on this side, onBoot.java will handle everything itself
         */
        
        /* After that's done, start showing the first shell prompt */
        //first shell prompt
         wd = baseDir;
        
        for (int zzz = 0; zzz < 1001; zzz++) {
            runCommand(null);
        }
        System.exit(0); 
        //when program execution finished, close safely
    }
    public static void runCommand(String command) {
        /* Moved code from main method to a new method
         * This way anyone can call this method to run a command
         */
        String c; //initilize command holding variable. 'c' is short for command
        if(command != null) {
            c = command;
        }
        String downloadDir = System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "downloads" + File.separator; 
            /* CHANGED: This should make commands work inside nested folders */
            /* TEST IT OUT */
            String baseDir = jLinuxInfo.hddLocation();
            String res = "";
            int ok = 1;
            String cmd;
            String arg;
            c = _shell(wd); //get the result of the shell prompt and set 'c' to it.
            int foundCommand = 0;
        if(c.toLowerCase().equals("quit")) {
            System.out.println("Quitting...");
            log.log("Safely closing jLinux");
            /* force writing to log of whatever's no already there */
            utils.writeToFile(jLinuxInfo.logLocation(), log.getLog());
            foundCommand = 1;
            /* break and exit */
            /* EDIT: changed breakt to System.exit because quit is no longer inside a loop */
            System.exit(0);
             
        }
        if(c.toLowerCase().equals("ls")) {
            cmd = "ls";
            arg = "";
            foundCommand = 1;
        } else if(c.toLowerCase().startsWith("cp")) {
            cmd = "cp";
            arg = "";
            String origin, dest;
            String[] a = new String[2];
            a = c.split(" "); //split on whitespace
            try {
                origin = a[1]; //second word (the first parameter bc the command is the first, would be [1] in the array
                dest = a[2]; //third index is the second arg
                cp.copy(origin, dest,wd);
                foundCommand = 1;
            } catch (ArrayIndexOutOfBoundsException arioobe) {
                foundCommand = 1;
                 o.echo(jLinuxInfo.guiEnabled(), "Invalid number of arguments");
            }
        } else if (c.toLowerCase().startsWith("listapp")) {
            cmd = "listapps";
            arg = "";
            foundCommand = 1;
            //show the title message
            //bc it was removed to facilitate updateapps command
            //however only do that if GUI is not enabled.
            //if gui is enabled, listapps will show it in the popup window
            if(jLinuxInfo.guiEnabled() == false) {
                o.echo(false, "The following apps are currently installed:");
            }
            listapps.list();
        } else if(c.toLowerCase().equals("setup")) {
            cmd = "setup";
            arg = "";
            foundCommand = 1;
            setup.doSetup();
        } else if(c.toLowerCase().equals("nuke")) {
            cmd = "nuke";
            arg = "";
            foundCommand = 1;
            nuke.doNuke();
        } else if(c.toLowerCase().startsWith("daemon")) {
            cmd = "daemon";
            arg = "";
            foundCommand = 1;
            try {
                liveupdate.daemonAdd();
            } catch (FileNotFoundException fnfe) {
                 o.echo(jLinuxInfo.guiEnabled(), "[ error ] Could not start program 'daemon'");
            }
        } else if(c.toLowerCase().equals("update")) {
            cmd = "update";
            arg = "";
            foundCommand = 1;
            update.doUpdate();
        } else if(c.toLowerCase().equals("updateapps")) {
            cmd = "updateapps";
            arg = "";
            foundCommand = 1;
            updateapps.doUpdate();
        } else if(c.toLowerCase().equals("version")) {
            cmd = "version";
            arg = "";
            foundCommand = 1;
            o.echo(jLinuxInfo.guiEnabled(), "Version: " + jLinuxInfo.version());
        } else if(c.toLowerCase().equals("explorer")) {
            cmd = "explorer";
            arg = "";
            foundCommand = 1;
            runExplorer.main();
        } else if(c.toLowerCase().equals("pwd")) {
            cmd = "pwd";
            arg = "";
            foundCommand = 1;
            o.echo(jLinuxInfo.guiEnabled(), wd);
        } else {
           String[] a = new String[2];
         a[1] = "";
         a = c.split(" ");
         cmd = a[0];
          try {
          arg = a[1];
         } catch (java.lang.IndexOutOfBoundsException eee) {
           arg = "";
           //System.out.println("[ info ] Found no command arguments, setting the argument value to empty");
         }
         
        
        }
        if(cmd.toLowerCase().equals("cd")) {
            wd = _command_cd(baseDir, wd, cmd, arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("vinf")) {
               try {
              ok = _command_vinf(wd, arg);
              if (ok == 1) {
                   o.echo(jLinuxInfo.guiEnabled(), "File created");
                } else {
                     o.echo(jLinuxInfo.guiEnabled(), "File creation failed!");
                }
            } catch (java.io.FileNotFoundException e) {
                 o.echo(jLinuxInfo.guiEnabled(), "Internal Java Error");
              }
             foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("mkdir")) {
            String newDir = _command_mkdir(wd, arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("ls")) {
            _command_ls(wd, arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("help")) {
            _command_help(arg);
            foundCommand = 1;
        }
        if (cmd.toLowerCase().equals("echo")) {
           cmd = "echo";
           arg = c.substring(c.indexOf(' ')+1); // 
           foundCommand = 1;
           extras.echo(arg);
        } 
        if(cmd.toLowerCase().equals("dl")) {
            cmd = "dl";
            //arg already set above
            foundCommand = 1;
            if(dl.get(arg, downloadDir)) {
                o.echo(jLinuxInfo.guiEnabled(), "Download Finished!");
            } else {
                 o.echo(jLinuxInfo.guiEnabled(), "Download Failed!");
            }
        }
        if(cmd.toLowerCase().equals("addapp")) {
            addapp.install(arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("open")) {
            fileOpen.doOpen(arg, wd);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("url")) {
            urlOpen.doOpen(arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("text")) {
            text.main(arg);
            foundCommand = 1;
        }
        if(cmd.toLowerCase().equals("do")) {
            foundCommand = 1;
            Script s = new Script();
            s.scrpt(arg);
            try {
                s.doRun();
            } catch (IOException e) {
                log.log("command 'do' ran into an IOExcption :(");
                System.out.println("Error!");
            }
        }
        if(cmd.toLowerCase().equals("gui")) {
            if(arg.toLowerCase().equals("on")) {
                jLinuxInfo.guiOn();
            } else if (arg.toLowerCase().equals("off")) {
                jLinuxInfo.guiOff();
            } else if(arg.toLowerCase().equals("force")) {
                File fgf = new File(jLinuxInfo.configLocation() + "forcegui.jLinuxBoolean");
                if(!fgf.exists()) {
                    try {
                        fgf.createNewFile();
                        System.out.println("Force GUI enabled! jLinux will boot into the GUI on next boot!\nTo remove this setting, erase this file:\n" + fgf.toPath());
                    } catch (IOException ioe) {
                        System.out.println("[ error ] Force GUI setting NOT enabled!");
                    }
                }
            } else {
                System.out.println("Command 'gui' accepts arguments on/off/force");
            }
            foundCommand = 1;
        }
        if(foundCommand == 0) { //if no command above matches
                if(loadApp.run(c, baseDir, wd) == true) {
                    //do nothing, the program will run by itself
                    //arg = c.substring(c.indexOf(' ')+1);
                } else {
                    /* Do nothing. Loadapp will output an error message */
                }
        }
    }
}
