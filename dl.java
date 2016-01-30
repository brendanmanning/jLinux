
/**
 * Write a description of class dl here.
 * 
 * @author (Brendan Manning) 
 * @version (Version 0.0.1 - As of 1/28/16)
 * This code has been adapted from code by Alvin Alexander
 * See this website for his source code:
 * http://alvinalexander.com/blog/post/java/jget-something-like-wget
 * or go to the README
 */
import java.io.*;
import java.nio.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.nio.channels.ReadableByteChannel;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
public class dl
{
   public static void main(String[] args) {
       //in case the dl class gets treated as the main class for some strange reason
       //....if so, show a copyright notice just cuz
       System.out.println("jLinux - (c) 2016 Brendan Manning");
    }
    //otherwise actually run the program
    public static boolean get(String url, String cwd) {
        //pass url parameter to method from main class... dl.get("http://www.url.url/file.extension")
        if(url == "") { //check if the URL got left empty some how
            System.out.println("Missing Argument. dl syntax is...");
            System.out.println("dl <direct file url>");
            return false;
        }
        if(url == null) { //do the same here, but for null values
            return false;
        }
        if(url.contains("?")) {
            System.out.println("[ error ] File could not be downloaded. ERROR -> File name contains a '?' character");
            return false; 
        }
        URL u;
        try {
          u = new URL(url);
        } catch (java.net.MalformedURLException mfue) {
            return false;
        }
        InputStream i = null;
        DataInputStream ds;
        String str;
        String newFileName = cwd + url.substring(url.lastIndexOf('/') + 1);
        FileWriter f;
        ReadableByteChannel rbc;
        try {
            rbc = Channels.newChannel(u.openStream());
        } catch (java.io.IOException exception) {
            return false;
        }
        FileOutputStream fos;
        try {
        fos = new FileOutputStream(newFileName);
    } catch (java.io.FileNotFoundException fnfe){
        return false;
    }
        try {   
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (java.io.IOException ioioioeeee) {
            return false;
        }
        
        
        
        /*try {
           f = new FileWriter(newFileName);
        } catch (java.io.IOException ioex) {
            System.out.println("[ error ] File not downloaded");
            System.out.println("[ error ] Internal Java Error -> " + ioex);
            return false;
        }
        
        try {
            u = new URL(url);
            i = u.openStream();
            ds = new DataInputStream(new BufferedInputStream(i));
            while ((str = ds.readLine()) != null) {
                f.write(str);
            }
            try {
                f.close();
            } catch(IOException cioe) {
                //do nothing
            }
        } catch (MalformedURLException mue) {
            System.err.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(2);
        }
        catch (IOException ioe)
        {
            System.err.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(3);
            }
            return true;
    }*/
    return true;
}
}
