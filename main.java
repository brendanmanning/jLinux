/**
 * Write a description of class main here.
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
              wd = _cleanWd(wd);
              arg = _cleanArg(arg);
              File directory = new File(wd + arg);
            // get all the files from a directory
            File[] fList = directory.listFiles();
        String fileName;
    for (int index = 0; index < fList.length; index++) {
        fileName = fList[index].getName();
        if (fList[index].isFile()) {
            if(!fileName.startsWith(".")) { //if not a dot file
                System.out.println("[file] " + fList[index]);
            }
        } else if (fList[index].isDirectory()) {
            if(!fileName.startsWith(".")) { //ignore hidden folders
                System.out.println("[directory] " + fList[index]);
            }
        } 
     }
    }
    public static String _command_mkdir(String wd, String arg) {
        //clean up the command args
        if(!arg.endsWith(File.separator)) {
          arg = arg + File.separator;
        }
        //terminate if path is not relative
        if(arg.startsWith(File.separator)) {
            System.out.println("Path not valid. Folder location must be a relative path");
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
            System.out.println("[ERROR]");
            System.out.println("New file path cannot start with a slash. It must be a relative path to the current working directory.");
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
                                System.err.println("Internal Java Error -- IOException: " + ioe.getMessage());
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
                     System.err.println("Internal Java Error -- IOException: " + ioe.getMessage());
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
                    System.out.println("Invalid path: Path must be relative to current working directory!");
                    return wd;
                }
                File cd2 = new File(wd + arg);
                if(!cd2.exists()) {
                    System.out.println(wd + arg + " does not exist, or is a file.");
                    return wd;
                }
                wd = wd + arg;
                return wd;
            
        } else {
            System.out.println("Not enough arguments were passed into program: cd");
            System.out.println("Current working directory changed to root of HDD folder");
            wd = baseDir;
            return baseDir;
        }
        
        
    }
    public static void setupJLinux() {
        boolean systemUsername;
        boolean defaultApps;
        String newUsername = "";
        File configFolder = new File(jLinuxInfo.configLocation());
        configFolder.mkdir();
        System.out.println(""); //print empty line
        System.out.println("*** Setup jLinux ***");
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
                System.out.println("[ error ] Could not change username. Please report the issue on GitHub at: www.github.com/brendanmanning/jLinux");
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
                    System.out.println("[ error ] Could not add daemon!");
                }
            }
        }
        System.out.println("jLinux is now setup!");
        System.out.println("If you would like to change any of the options you just chose just run: setup");
        System.out.println("");
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
           System.out.println("[ ok ] HDD data folder exists!");
        } else {
           hddLocation.mkdir(); 
           System.out.println("[ debug ] HDD folder was missing, so it has been created in your home folder");
        }
        if(downloadsFolder.exists()) {
            //downloads folder exists
            System.out.println("[ ok ] User downloads folder exists");
        } else {
            downloadsFolder.mkdir();
            //downloads folder did not exist so it was created
            System.out.println("[ debug ] User downloads folder was missing, so it was created!");
        }
        if(appsFolder.exists()) {
            System.out.println("[ ok ] Applications folder exists");
        } else {
            appsFolder.mkdir();
            System.out.println("[ debug ] Applications folder was missing, so it has been created!");
        }
        daemonThread.main(null); //start daemon thread
        //silently check to see if jLinux config exists
        if(isSystemSetup() == false) {
            setupJLinux();
        }
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
                     System.out.println("Internal Java Error!");
                     username = System.getProperty("user.name");
                 }
             } catch (FileNotFoundException fnfe) {
                System.out.println("[ error ] Check for username override file failed!");
                username = System.getProperty("user.name"); //default back to normal username
          }
        }
        /* once we're done finding the username, return it */
        return username;
    }
    public static String _shell(String wd) {
                  
              Scanner i = new Scanner(System.in);
              System.out.print(getUsername() + "@jLinux: " + wd + "$ ");
              String command = i.nextLine();
              return command;
             
    }
    //static int shellEnabled = 1;
    public static Boolean _enableShell(Boolean e) {
        if(e == true) {
            //shellEnabled = 1;
            //enable shell prompt
            return true;
        }
        if(e == false) {
            //shellEnabled = 0;
            return false;
        }
        return true; 
        //always return true, although this return statement should never be reached
    }
    public static void main(String[] args) throws java.lang.ArrayIndexOutOfBoundsException {
        //setShellVar();
        
        //initilize core host system vars
        System.out.println("Booted Successfully!");
        System.out.println("Checking if system set up");
        String baseDir = System.getProperty("user.dir") + File.separator + "hdd" + File.separator;
        String os = System.getProperty("os.name");
        boolean isSetup = _doSetup(baseDir);
        System.out.println("");
        System.out.println("----{" + jLinuxInfo.version() + "}----");
        System.out.println("");
        System.out.println("Use of jLinux is subject to the jLinux Liscense found at https://github.com/brendanmanning/jLinux/blob/master/LICENSE");
        System.out.println("");
        System.out.println("::::::::Need Help?::::::::");
        System.out.println("Run help * to list all commands, or help + command name to get command specific help");
        //System.out.println("");
        String c; //initilize command holding variable. 'c' is short for command
        //first shell prompt
         wd = baseDir;
        String downloadDir = System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "downloads" + File.separator; 
        for (int zzz = 0; zzz < 1001; zzz++) {
            
            
            String res = "";
            int ok = 1;
            String cmd;
            String arg;
            c = _shell(wd); //get the result of the shell prompt and set 'c' to it.
            int foundCommand = 0;
        if(c.toLowerCase().equals("quit")) {
            System.out.println("Quitting...");
            foundCommand = 1;
            break;
             
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
                System.out.println("Invalid number of arguments for 'cp'");
            }
        } else if (c.toLowerCase().startsWith("listapp")) {
            cmd = "listapps";
            arg = "";
            foundCommand = 1;
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
                System.out.println("[ error ] Could not start program 'daemon'");
            }
        } else if(c.toLowerCase().equals("update")) {
            cmd = "update";
            arg = "";
            foundCommand = 1;
            update.doUpdate();
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
                  System.out.println("File created");
                } else {
                    System.out.println("File creation failed!");
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Internal Java Error");
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
                System.out.println("Download Finished!");
            } else {
                System.out.println("Download Failed!");
            }
        }
        if(cmd.toLowerCase().equals("addapp")) {
            addapp.install(arg);
            foundCommand = 1;
        }
        if(foundCommand == 0) { //if no command above matches
                if(loadApp.run(c, baseDir) == true) {
                    //do nothing, the program will run by itself
                    //arg = c.substring(c.indexOf(' ')+1);
                } else {
                    System.out.println("The program '" + cmd + "' does not exist");
                }
        }
        }
        System.exit(0); 
        //when program execution finished, close safely
    }
}
