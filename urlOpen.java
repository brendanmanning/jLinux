
/**
 * URL Open opens a URL in the default GUI browser via Open class
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.6)
 */
public class urlOpen
{
    public static void doOpen(String arg) {
        Open o = new Open();
        o.setType(2); /* 1 is for file */
        /* Recognized URL types
         * http://
         * https://
         */
        if(!arg.startsWith("http://")) {
            if(!arg.startsWith("https://")) {
                arg = "http://" + arg;
            }
        }
        o.doOpen(arg);
    }
}
