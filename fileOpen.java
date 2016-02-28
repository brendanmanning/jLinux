 
/**
 * Write a description of class opencmd here.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.6)
 */
public class fileOpen
{
    public static void doOpen(String arg, String wd) {
        Open o = new Open();
        o.setType(1); /* 1 is for file */
        
        /* Before we go and open the file, we should make sure that the path will work */
        if(arg.startsWith("/") || arg.startsWith("C:")) {
            /* if this is true, it's an absolute path */
            /* throw an error because we should only open relative paths */
            errors.other("Must be an relative path!", "file");
        } else {
            /* we don't want the user to pass in absolute paths
             * however in order to open files in their jLinux folder
             * we must convert the absolute to relative paths.
             * Simple String manipulation can accomplish this
             */
            arg = jLinuxInfo.hddLocation() + arg;
            /* All that was done there was put the HDD folder location
             * in front of the file argument
             */
        }
        o.doOpen(arg);
    }
}
