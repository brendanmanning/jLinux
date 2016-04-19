import java.util.*;
import java.io.*;
public class text {
    public static void main(String a) {
        boolean read_file = false;
        if(a.toLowerCase().startsWith("-d:")) {
            a = a.replace("-d:", "");
            a = main.getwd() + a;
            log.log(a);
            decrypt d = new decrypt();
            d.setString(utils.getFileText(a));
            Scanner i = new Scanner(System.in);
            System.out.println("What is the password?");
            d.setKey(i.nextLine());
            d.decrypt();
        } else if (a.toLowerCase().startsWith("-v:")) {
            /* Remove the argument from the argument
             * we no longer need it so we can open the file 
             */
            a = a.replace("-v:", "");
            /* Since we're opening a file we need to put the working directory in front of the argument */
            a = main.getwd() + a;
            /* Ensure that the file is ok */
            if(utils.fileOk(a) == false) {
                System.out.println("File does not exist!");
                log.log("TeXT couldn't open " + a + " because the file does not exist");
            } else { //ok, proceed
                /* Use getFileText from the utils class */
                System.out.println(utils.getFileText(a).replace("[*LINE*]", "\n"));
            }
        } else {
            if(a.replace(" ", "").equals("")) {
                log.log("Not enough args for TeXT");
                o.echo(jLinuxInfo.guiEnabled(), "Error! Not enough args");
            } else {
                if(a.startsWith(File.separator)) {
                    o.echo(jLinuxInfo.guiEnabled(),"Error! Path must be relative!");
                } else {
                    a = main.getwd() + a;
                }
                newFile(a);
            }
        }
    }
    public static void newFile(String f) {
        String t = "";
        String temp = "";
        String pass = "";
        boolean encrypt = false;
        int passwordCount = 0;
        Scanner i = new Scanner(System.in);
        System.out.println("Would you like to encrypt this file? (yes/no)");
        String res = i.nextLine();
        if(res.toLowerCase().equals("yes")) {
            encrypt = true;
            System.out.println("What numeric password will you use?");
            boolean ok = false;
            do {
                if(passwordCount > 0) {
                    System.out.println("Try again! Only numbers are allowed!");
                }
                pass = i.nextLine();
                if(utils.isStringNumeric(pass)) {
                    ok = true;
                } else {
                    ok = false;
                    passwordCount++;
                }
            } while (ok == false);
        }
        System.out.println("*** Text Editor ***");
        System.out.println("To save and close, type 'quit'\non a blank line and press enter");
        while(true) { //loop until user is ready to quit
            temp = i.nextLine();
            if(temp.equals("quit") || temp.equals("'quit'")) {
                break;
            } else {
                t += "[*LINE*]";
                //replace dashes because they seem to mess with the encryption
                t = t.replace("-", "*");
                t += temp;
            }
            //reset temp string
            temp = "";
        }
            //check if we need to encrypt
            if(encrypt) {
                t = t.toUpperCase();
                encrypt e = new encrypt();
                e.setKey(pass);
                t = e.encryptString(t);
            }
            //when we're done getting input, output the file
            if(utils.writeToFile(f, t)) {
                System.out.println("Done!");
            } else {
                System.out.println("Sorry! An error occured!\nYour file was not saved");
            }
        }
    }