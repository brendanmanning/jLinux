
/**
 * GUI class is the entry point for graphical user-interface shell prompts
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.7)
 */
import javax.swing.*;
public class gui
{
   public static boolean enterGUIMode() {
       if(!info.isGUI()) {
           System.out.println("[ error ] This OS does not support a graphical user interface!\nGUI mode will not work on this machine!");
           return false;
       } else {
           System.out.println("[ ok ] OS supports graphical user interface. Launching GUI Mode!");
           /* Make sure jLinux is aware that it's in GUI mode */
           jLinuxInfo.guiOn();
           return true;
       }
    }
   public static String showGUITerminal(String cwd) {
       return JOptionPane.showInputDialog(null, "(Current Directory: " + cwd + ") Type Command Below:");
   }
}
