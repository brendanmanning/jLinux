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
        }
        if(arg.equals("cd")) {
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
        
    for (int index = 0; index < fList.length; index++) {
        if (fList[index].isFile()) {
            System.out.println("[file] " + fList[index]);
        } else if (fList[index].isDirectory()) {
            System.out.println("[directory] " + fList[index]);
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
    public static boolean _doSetup(String baseDir) {
        File hddLocation = new File(System.getProperty("user.dir") + File.separator + "hdd");
        File downloadsFolder = new File(System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "downloads" + File.separator); 
        File appsFolder = new File(System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "applications" + File.separator); 
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
        return true; 
    }
    public static String _shell(String wd) {
            
              Scanner i = new Scanner(System.in);
              System.out.print(System.getProperty("user.name") + "@jLinux: " + wd + "$ ");
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
        System.out.println("All good!");
        System.out.println("----{" + jLinuxInfo.version() + "}----");
        System.out.println("");
        System.out.println("::::::::Need Help?::::::::");
        System.out.println("Run help * to list all commands, or help + command name to get command specific help");
        //System.out.println("");
        String c; //initilize command holding variable. 'c' is short for command
        //first shell prompt
        String wd = baseDir;
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
                if(loadApp.run(cmd.toLowerCase(), baseDir) == true) {
                    //do nothing, the program will run by itself
                } else {
                    System.out.println("The program '" + cmd + "' does not exist");
                }
        }
        }
        System.exit(0); 
        //when program execution finished, close safely
    }
}
