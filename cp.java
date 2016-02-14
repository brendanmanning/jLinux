
/**
 * Write a description of class cp here.
 * 
 * @author (Brendan Manning) 
 * @version (cp v2 - jLinux v2.2.0)
 */
import java.util.Scanner;
import java.nio.file.StandardCopyOption.*;
import java.nio.*;
import java.nio.file.Files;
import java.io.*;
import java.nio.file.Path;
public class cp
{
    public static void doCopy(File o, File d) {
        //try {
          Path original = o.toPath(); //convert from File to Path
          Path dest = d.toPath(); //convert from String to Path
          try {
              Files.copy(original, dest);
              System.out.println("Copied!");
           } catch(IOException ioe3) {
               System.out.println("[ error ] File copy failed!");
               System.out.println(ioe3.getMessage());
            }
          //if file already exists, replace it
          //otherwise it will just do a normal java nio copy
        //}
        
    }
    public static void main(String[] args) {
        //this method should never run.
        //if it does though, show a copyright notice
        System.out.println("jLinux (c) 2016 - Brendan Manning");
    }
    public static void copy(String origin, String dest, String cwd) {
        System.out.println("Copy " + origin + " to " + dest);    
        //if file name doesn't contain slashes, we need to add the current path to the front
        if(!origin.contains(File.separator)) {
            //origin = "";
            origin = cwd + origin;
        }
        if(!dest.contains(File.separator)) {
            //dest = "";
            dest = cwd + dest;
        }
        File o = new File(origin);
        File d = new File(dest);
        
            doCopy(o,d);
            
    }
}
