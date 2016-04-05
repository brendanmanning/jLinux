
/**
 * Write a description of class open here.
 * 
 * @author (Brendan Manning) 
 * @version (jLinux 2.6)
 */
import java.lang.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Open
{
    private int type; //1 - file , 2 - url, 3 - download
    public Open() {
        //creates a new open instance
    }
    public int getType() {
        return type;
    }
    public void setType(int t) {
        type = t;
    }
    public void doOpen(String toOpen) {
        if(!GraphicsEnvironment.isHeadless()) {
            /* if desktop envirmonet is supported (ie Windows, Mac, Desktop Linux) */
            if(type == 1) {
                    /* create a new file object with the path specified */
                    File f = new File(toOpen);
                    /* open the file in the system default app */
                    try {
                        try {
                            Desktop.getDesktop().open(f);
                        } catch (IllegalArgumentException iae) {
                            System.out.println("[ error ] File could not be opened!");
                            log.log("Program: Open encountered an IllegalArgumentException");
                        }
                    } catch (IOException ioe) {
                        System.out.println("[ error ] File could not be opened!");
                        log.log("Program: Open encountered an IOException");
                        
                    }
            }
            if(type == 2) {
                try {
                    URI u = new URI(toOpen);
                    try {
                        Desktop.getDesktop().browse(u);
                    } catch (IOException i) {
                        System.out.println("[ error ] URL could not be opened: ioexception");
                        log.log("[ error ] URL could not be opened: ioexception");
                    }
                } catch (URISyntaxException use) {
                   System.out.println("[ error ] URL could not be opened!"); 
                   log.log("[ error ] URL could not be opened!");
                }
            }
        } else {
            /* If the desktop is not supported
             * i.e Command Line Only Linux
             */
            System.out.println("[ error ]: This computer does not support the open command!");
        }
    }
}