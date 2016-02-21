
/**
 * Write a description of class addapp here.
 * 
 * @author (Brendan Manning) 
 * @version (1.0.0)
 */
import java.net.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
public class addapp
{
    public static void main(String[] args) {
      //this shouldn't ever be run
      //but in case it is, show a copyright notice
      System.out.println("jLinux 2 - (c) 2016 Brendan Manning");
    }
    public static boolean install(String app) {
        String urlString;
        urlString = "https://www.jlinux.net/appstore/content/" + app + "/";
        String fileString;
        fileString = urlString + app + ".jar";
        String appDir;
        appDir = System.getProperty("user.dir") + File.separator + "hdd" + File.separator + "applications" + File.separator;
        URL u;
        try {
           u = new URL(urlString); 
        } catch (java.net.MalformedURLException ume) {
           System.out.println("Program '" + app.toLowerCase() + "' does not exist");
            return false;
        }
        HttpsURLConnection huc;
        try {
            huc =  (HttpsURLConnection)  u.openConnection(); 
         } catch (java.io.IOException ioe) {
             System.out.println("App download failed!");
             return false;
         }
        try {
         huc.setRequestMethod("HEAD"); 
        } catch (java.net.ProtocolException pe) {
            System.out.println("App failed to download!");
            return false;
        }
        try {
           huc.connect(); 
        } catch (java.io.IOException ioe2) {
            System.out.println("An Error Occured. Download stopped!");
            return false;
        }
        
        //try {
             //if (huc.getResponseCode() == HttpsURLConnection.HTTPS_OK) {
                  //URL does exist
                  System.out.println("Downloading Application '" + app + "'....");
                  //now actually download the file
                  dl.get(fileString, appDir);
                  return true;
             //} else {
                 //System.out.println("Sorry, that app does not exist");
                 //return false;
               // }
        //} //catch (java.io.IOException ioe3) {
         //   System.out.println("An error caused the download to fail!");
          //  return false;
        //}
    }
}
