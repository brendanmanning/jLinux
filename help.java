
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
}
