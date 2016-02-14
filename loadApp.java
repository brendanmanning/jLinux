
/**
 * Write a description of class loadApp here.
 * 
 * @author (Brendan Manning) 
 * @version (Revision 1)
 */
import java.io.*;
public class loadApp
{
    public static void main(String[] arg) {
        //this should never be run. 
        //in case it is though, show a copyright notice
        System.out.println("jLinux - (c) 2016 Brendan Manning");
    }
    public static boolean run(String prog, String baseDir) {
        //first disable the default shell prompt
        //main._enableShell(false);
        //create a File object to find the program
        String pStr = baseDir + "applications" + File.separator + prog.toLowerCase() + ".jar";
        //System.out.println(pStr);
        File f = new File(pStr);
        if (f.exists()) {
         //the program is there, run it
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", pStr);
                    pb.directory(new File(System.getProperty("java.home")));
                    Process p = pb.start();
                    LogStreamReader lsr = new LogStreamReader(p.getInputStream());
                    Thread thread = new Thread(lsr, "LogStreamReader");
                    thread.start();
                    try {
                    p.waitFor();
                } catch (java.lang.InterruptedException ie) {
                    //wait
                    System.out.print("[ fatal error ] Program '" + prog.toLowerCase() + "' created an error that jLinux can't process. Quitting...");
                    System.exit(1);
                }
                //code found @ http://stackoverflow.com/questions/1320476/execute-another-jar-in-a-java-program
            } catch (java.io.IOException ioex) {
                //alert the user of an error
                System.out.println("Program '" + prog.toLowerCase() + "' exists but an internal error occured");
                System.out.print("::Error Code - ");
                ioex.printStackTrace();
                System.out.print(" ::");
                return false;
            }
            
        } else {
            //alert user
            System.out.println("Program '" + prog.toLowerCase() + "' does not exist");
            //actually nevermine to the above, because the main class should do that itself
            return false;
        }
        return true;
    }
}
//LogStreamreader class from user Mohammad Adil on stack Overflow
//post can be found here http://stackoverflow.com/questions/15700879/how-to-run-a-java-executable-jar-in-another-java-program
class LogStreamReader implements Runnable {

    private BufferedReader reader;

    public LogStreamReader(InputStream is) {
        this.reader = new BufferedReader(new InputStreamReader(is));
    }

    public void run() {
        try {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
