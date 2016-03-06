
/**
 * Out is the jLinux output class.
 * It accepts two arguments, GUI (true or false) and MESSAGE (a string)
 * run it by doing o.echo(true, "Message Goes here!");, etc.
 * 
 * @author (Brendan Manning) 
 * @version (v1)
 */
import javax.swing.*;
public class o
{
    public static void termOut(String msg) {
        System.out.println(msg);
    }
    public static void guiOut(String msg) {
        if(info.isGUI()) {
            JOptionPane.showMessageDialog(null, msg);
        }
    }
    public static void echo(boolean gui, String msg) {
        /* if the program is outputting to the GUI */
        if(gui) {
            guiOut(msg);
        } else { /* if outputting to the terminal */
            termOut(msg);
        }
    }
}
