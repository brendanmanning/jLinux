
/**
 * Write a description of class decrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class decrypt
{
    private static String encrypted;
    private static String key;
    private static String decrypted = "";
    private static int which = 0;
    /**
     * Constructor for objects of class decrypt
     */
    public decrypt()
    {
        
    }
    private static String doDecrypt() {
        for(int x = 0; x < encrypted.length(); x++) {
            int ival = Integer.parseInt(Character.toString(key.charAt(which)));
            decrypted += "" + (char) ((encrypted.charAt(x)) - ival);
            if(which == key.length() - 1) {
                which = 0;
            } else {
                which++;
            }
        }
        decrypted = decrypted.replace("[*LINE*]", "\n");
        System.out.println(decrypted);
        return decrypted;
    }
    public static void setKey(String y)
    {
        key = y;
    }
    public static void decrypt() {
        doDecrypt();
    }
    public static void setString(String i) {
        encrypted = i;
    }
    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        System.out.println("Key: ");
        setKey(n.nextLine());
        System.out.println("Encrypted String: ");
        setString(n.nextLine());
        doDecrypt();
    }
}
