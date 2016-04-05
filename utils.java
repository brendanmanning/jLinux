
/**
 * Re-useable code for across jLinux
 * 
 * @author (Brendan Manning) 
 * @version (1.0)
 */
import java.io.*;
public class utils
{
    public static boolean fileOk(String p) {
        /* Checks whether the file is ok to use with jLinux */
        /* Checks to ensure that the file exists and is not a directory */
        
        /* Check if it exists */
        File f = new File(p); //using file class
        if(!f.exists()) {
            /* If file does not exist */
            return false;
        }
        if(f.isDirectory()) {
            /* If file is a directory */
            return false;
        }
        /* If both checks pass, return true */
        return true;
    }
    public static boolean writeToFile(String p, String text) {
        try {
            FileWriter fw = new FileWriter(p,true); //the true will append the new data
            fw.write(text);//appends the string to the file
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isStringNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }
    public static String getFileText(String f) {
        BufferedReader b = null;
       String line = null;
        try {
            b = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException fnfe) {
            return "";
        }
        String tr = ""; //(String TO RETURN)
        try {
            try {
                line = b.readLine();
            } catch (IOException e) {
                
            }
            while(line != null) {
                tr += line;
                try {
                    line = b.readLine();
                } catch (IOException i) {
                    
                }
            }
        } finally {
            try {
                b.close();
            } catch (IOException e) {
                
            }
        }
        return tr;
    }
}
