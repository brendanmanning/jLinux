
/**
 * Simplified Error Reporting
 * @author (Brendan Manning) 
 * @version (0.0.1)
 */
import java.io.*;
public class errors
{
    public static boolean angry() {
        File a = new File(jLinuxInfo.configLocation() + "angry.jLinuxBoolean");
        if(a.exists()) {
            return true;
        } else {
            return false;
        }
    }
    public static void fileNotFound(String fname) {
        if(angry() == false) {
            System.out.println("[ error ] The file " + fname + " could not be found or does not exist!");
            log.log("[ error ] The file " + fname + " could not be found or does not exist!");
        }
        if(angry()) {
            System.out.println("srsly mon! What ARE YOU DOING! \nHow come u go loozin dis fiel : " + fname);
        }
    }
    public static void internal(String prog) {
        if(angry() == false) {
            System.out.println("[ error ] An internal java error caused the program " + prog + " to fail");
            log.log("[ error ] An internal java error caused the program " + prog + " to fail");
        }
        if(angry()) {
            System.out.println("Well, you really SCREWED UP THIS TIME! No, I'm not making a scene! \nYOU'RE THE ONE MAKING A SCENE! It's not MY fault there was an \nINTERNAL JAVA ERROR *Nicholas Cage freak-out voice*");
        }
    }
    public static void other(String msg, String prog) {
        if(angry() == false) {
            System.out.println("[ error ] " + msg + "(Program: " + prog + ")");
        }
        if(angry()) {
            System.out.println("WOW! Just WOW! Look what you've done! Go to your room NOW! \nHow DARE YOU cause this error message: " + msg + " I am VERY DISSAPPOINTED in you!");
        }
    }
    public static void path(String p) {
        if(angry() == false) {
            System.out.println("There was an error in your path: " + p + ". \nEnsure that it is relative to your current directory and does not start with a slash");
        }
        if(angry()) {
            System.out.println("You think ur so cool?!?! Being all l33t hax0r with ur screwed up path?!?! \nHow is a folder like this " + p + " okay to you??!!?!");
        }
    }
    public static void cmd(String msg) {
        if(angry() == false) {
            o.echo(jLinuxInfo.guiEnabled(), msg);
        }
        if(angry()) {
            o.echo(jLinuxInfo.guiEnabled(), "How could you!! After all i've done for you?!?! " + msg);
        }
    }
}
