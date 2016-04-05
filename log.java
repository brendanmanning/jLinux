import java.text.SimpleDateFormat;
import java.util.Date;
public class log {
    private static String toLog = "";
    public static void log(String s) {  
        if(jLinuxInfo.loggingOn() == true) {
            String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            toLog += "\n" + time + "\n\t-> " + s;
        } else {
            //do nothing
        }
    }
    public static String getLog() {
        return toLog;
    }
    public static void clearLog() {
        toLog = "";
    }
}