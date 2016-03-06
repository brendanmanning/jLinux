
/**
 * Write a description of class extras here.
 * 
 * @author (Brendan Manning) 
 * @version (0.0.1)
 */
public class extras
{
    public static void echoOS() {
        o.echo(jLinuxInfo.guiEnabled(),"{{" + System.getProperty("os.name") + "}}");
    }
    public static void echoVersion() {
        o.echo(jLinuxInfo.guiEnabled(), "{{" + System.getProperty("os.name") + " " + System.getProperty("os.version") + "}}");
    }
    public static void echo(String arg) {
        //code here
        //System.out.println("The echo class worked!");
        if(arg.toLowerCase().equals("$os")) {
            //if we're looking for the OS property
            echoOS();
        } else if (arg.toLowerCase().equals("$version")) {
            //if we're looking for the host OS version
            echoVersion();
        } else {
            //otherwise just return the text
            System.out.println(arg);
        }
    }
    public static void main(String[] args) {
        System.out.println("jLinux - Copyright 2016, Brendan Manning");
    }
}
