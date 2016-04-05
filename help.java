
/**
 * Write a description of class help here.
 * 
 * @author (Brendan Manning) 
 * @version (0.0.1)
 */
/*   Programs to include help for
 * cd +
 * mkdir + 
 * ls + 
 * vinf + 
 * dl +
 * echo
 * help
 * quit
 */
public class help
{
    public static void main(String[] args) {
        //this method should never execute, but in case it does, show a copyright notice
        System.out.println("jLinux - (c) 2016 Brendan Manning");
    }
    public static void cdHelp() {
            System.out.println("cd - Syntax: cd <relative folder path here>");
            System.out.println("###########################################");
            System.out.println("cd - Tips: cd .. will always bring you to the root of your installation (Your Windows/Mac/etc home folder + hdd)");
            System.out.println("###########################################");
            System.out.println("cd - Troubleshooting: Remember, paths must be relative. They cannot start with a slash and must refer to a directory inside your current one. To go to a directory above you, cd to the root of USBLinux (cd ..) and continue from there.");
            System.out.println("#############END cd Help Page###############");
    }
    public static void mkdirHelp() {
        System.out.println("mkdir - Syntax: mkdir <folder name relative to current folder>");
        System.out.println("###########################################");
        System.out.println("mkdir - Tips: when using mkdir, the new folder name cannot start with a '/'");
         System.out.println("#############END mkdir Help Page###############");
    }
    public static void lsHelp() {
        System.out.println("ls - Syntax: ls");
        System.out.println("###########################################");
        System.out.println("-- ls lists the contents of your current working directory --");
        System.out.println("ls - Tips: when using ls, you only need to type 'ls', no arguments are needed");
        System.out.println("#############END ls Help Page###############");
    }
    public static void vinfHelp() {
        System.out.println("vinf (Vi New File) - Syntax: vinf <new file name here>");
        System.out.println("###########################################");
        System.out.println("-- vinf creates a new file of your name in your current working directory and lets you add text --");
        System.out.println("#############END vinf Help Page###############");
    }
    public static void dlHelp() {
        System.out.println("dl (Download File Utility) - Syntax: dl <HTTP(s) or FTP File URL>");
        System.out.println("###########################################");
        System.out.println("-- dl downloads a file from the internet over http/https/ftp --");
        System.out.println("dl - Troubleshooting: URLs cannot contain any parameters '?' symbols. Remove them if possible.");
        System.out.println("#############END dl Help Page###############");
    }
    public static void addappHelp() {
        System.out.println("addapp (Package Manager Utility) - Syntax: addapp <app name>");
        System.out.println("addapp - Troubleshooting: If an app fails to download, make sure you have internet connection, and that the app exists");
        System.out.println("addapp - Troubleshooting: To remove an app manually, delete it from the applications folder in your jLinux directory");
    }
    public static void cpHelp() {
        System.out.println("cp (copy command) - Syntax: cp <original file location> <duplicate file location>");
        System.out.println("cp - Troubleshooting: If a file fails to copy, ensure that you are reffering to it with a relative path, not an absolute path");
    }
    public static void echoHelp() {
        System.out.println("echo (repeats text or return system property) - Syntax: echo <text to repeat> OR echo <$OS/$VERSION> to return system properties");
        System.out.println("echo - Troubleshooting: If you are unable to echo a system property, ensure that you input a '$' (dollar) sign before the property (either OS or VERSION)");
    }
    public static void updateHelp() {
        System.out.println("update (gives instructions on how to update jLinux) - Syntax: update");
    }
    public static void listappsHelp() {
        System.out.println("listapps (Lists all appstore installed JARs) - Syntax: listapps");
        System.out.println("listapps: Troubleshooting - Ensure that your applications folder exists and any apps you want are inside and have not been modified");
    }
    public static void daemonHelp() {
       System.out.println("daemon (Live Updates) - Syntax daemon");
       System.out.println("*daemons are appstore commands that are configured to run ever 2 minutes. They accept no input and are designed to keep you updated on the things/information you care about!");
       System.out.println("**If daemons do not run, make sure they are enabled by re-running setup and choosing 'y' at the live updates prompt");
       System.out.println("***Also, ensure that apps are added in the daemon-m.jLinuxBoolean file located at: " + jLinuxInfo.configLocation() + "dameon-m.jLinuxBoolean");
       System.out.println("****To easily add a daemon, run the 'daemon' command and type the name of the app store command you want to add! Unlimited numbers of daemons can be configured!");
       System.out.println("*****Dameons are new to jLinux, so they may be buggy for a little while until everything is sorted out! However, live updates has already been heavily tested, and has performed great!");
    }
    public static void setupHelp() {
        System.out.println("setup (Deletes and reconfigures jLinux preferences) - Syntax: setup");
        System.out.println("Instructions: follow the prompts to reconfigure jLinux!");
        System.out.println("* The setup command removes files and preferences from you .config folder. All jLinux preferences will be removed!");
    }
    public static void nukeHelp() {
        System.out.println("nuke (deletes all jLinux files and preferences) - Syntax: nuke");
        System.out.println("WARNING: nuke deletes ALL files and folders inside you jLinux 'hdd' folder! jLinux and it's developers are not reponsible for any damages that may be caused!");
        System.out.println("* nuke does not uninstall jLinux. To do so, remove the jLinux application directory/jar file from you computer!");
    }
    public static void textHelp() {
        System.out.println("TeXT creates and reads encrypted text files");
        System.out.println("Syntax:\ntext [-v:,-d:]<file location>");
        System.out.println("-v: Lets users view a normal (enencrypted text file)");
        System.out.println("-d: Lets users decrypt and view a file encrypted with the TeXT");
    }
}
