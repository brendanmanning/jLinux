
/**
 * Write a description of class explore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
public class Explorer extends JFrame
{
    // instance variables - replace the example below with your own
    private String wd;
    private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;
    /**
     * Constructor for objects of class explore
     */
    public Explorer()
    {
        // initialise instance variables
        wd = main.getwd();
    }
    private String getType(String f) {
        if(f.endsWith(".txt") || f.endsWith(".docx") || f.endsWith(".rtf") || f.endsWith(".md")) {
            return "Text File";
        } else if(f.endsWith(".mp4") || f.endsWith(".avi") || f.endsWith(".flv") || f.endsWith(".mkv") || f.endsWith(".3gp") || f.endsWith(".mov")) {
            return "Video File";
        } else if(f.endsWith(".mp3") || f.endsWith(".ogg") || f.endsWith(".flac") || f.endsWith(".m4a")) {
            return "Audio File";
        } else if(f.endsWith(".exe") || f.endsWith(".mui")) {
            return "Windows Executable";
        } else if(f.endsWith(".php") || f.endsWith(".html") || f.endsWith(".css") || f.endsWith(".js") || f.endsWith(".htaccess")  || f.endsWith(".php5")) {
            return "Web Server File";
        } else if(f.endsWith(".java") || f.endsWith(".class")) {
            return "Java Source/Class File";
        } else if(f.endsWith(".jLinuxBoolean")) {
            return "jLinux Configuration File";
        } else if(f.endsWith(".jar")) {
            return "Java Executable";
        } else {
            return "???";
        }
    }
    public boolean setDir(String d)
    {
        // put your code here
        File dir = new File(d);
        if(dir.exists()) {
            if(dir.isFile()) {
                //if dir is a file
                return false;
            } else {
                //otherwise return true
                return true;
            }
        } else {
            //folder doesn't exist
            return false;
        }
    }
    private String[][] getContents() {
       File directory = new File(wd);
       // get all the files from a directory
        File[] fList = directory.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file) {
                return !file.isHidden();
            }
        });
        String fileName;
        String[] res = new String[fList.length];
        
               String[][] t = new String[fList.length][3];
       for (int index = 0; index < fList.length; index++) {
             fileName = fList[index].getName();
             if (fList[index].isFile()) {
                 if(!fileName.startsWith(".")) { //if not a dot file
                     //res[index] = fileName + "&&&" + "null" +"&&&" + "n/a";
                     t[index][0] = fileName;
                     t[index][1] = getType(fileName);
                     if(wd.startsWith(jLinuxInfo.appsLocation())) {
                         if(fileName.endsWith(".jar")) {
                             t[index][2] = "Yes";
                         } else {
                             t[index][2] = "No";
                            }
                     } else {
                         t[index][2] = "No";
                        }
                 }
                } else if (fList[index].isDirectory()) {
                    if(!fileName.startsWith(".")) { //ignore hidden folders
                        t[index][0] = fileName;
                        t[index][1] = "Folder";
                        t[index][2] = "n/a";
                        
                    }
                } 
       }
       
       
       /* String[] arr;
        //ArrayList<ArrayList<String>> info= new ArrayList<ArrayList<String>>();
        for(int u = 0; u < res.length; u++) {
            //t = Arrays.asList(r[u].split("&&&"));
            arr = res[u].split("&&&");
            t[u][0] = arr[0];
            t[u][1] = arr[1];
            t[u][2] = arr[2];
        }
        */
       return t;
    }
    public void showExplorer() {
        //set frame aspects (properties)
        setTitle("Explorer: " + wd);
        setSize(475, 350);
        setBackground(Color.gray);
        //Create a panel to hold everything
        topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add(topPanel);
        //intenerate over the rows returned from getContents
       /* String[] r = getContents();
        String[][] t =null;
        String[] arr;
        //ArrayList<ArrayList<String>> info= new ArrayList<ArrayList<String>>();
        for(int u = 0; u < r.length; u++) {
            //t = Arrays.asList(r[u].split("&&&"));
            arr = r[u].split("&&&");
            t[u][0] = arr[0];
            t[u][1] = arr[1];
            t[u][2] = arr[2];
        }
        */
        //create the colums
        String[] cols = {"Name", "Type", "Application"};
        // add the table
        table = new JTable( getContents(), cols );
        // Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
    }
}
