
/**
 * Turns angry errors on or off
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.6)
 */
import java.io.*;
public class angry
{
    public static void main(boolean on) {
        File f = new File(jLinuxInfo.configLocation() + "angry.jLinuxBoolean");
        if(on == true) {
            if(!f.exists()) {
                try {
                    f.createNewFile();
                    System.out.println("Angry errors enabled!");
                } catch (IOException e) {
                    errors.other("Angry errors couldn't be enabled!" , "angry");
                }
            }
            if(f.exists()) {
               errors.other("Angry errors already on!", "Default Shell Service");
            }
        }
        if(on == false) {
            if(f.exists()) {
                if(!f.isDirectory()) {
                        f.delete();
                } else {
                     System.out.println("[ error ] Angry errors mode couldn't be disabled!");
                }
            }
            if(!f.exists()) {
                System.out.println("Already disabled!");
            }
        }
    }
}
