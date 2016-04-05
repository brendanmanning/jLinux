
/**
 * Write a description of class encrypt here.
 * 
 * @author (Brendan Manning) 
 * @version (a version number or a date)
 */
import java.util.*;
public class encrypt
{
    private static String original;
    private static String key;
    private static String encrypted = "";
    private static int which = 0;
    public encrypt()
    {
       
    }
    public static void setKey(String k)
    {
       key = k;
    }
    public static String encryptString(String b) {
        original = b;
        return doEncrypt();
    }
    private static String doEncrypt() {
        System.out.println("Started");
        for(int x = 0; x < original.length(); x++) {
            int intval = Integer.parseInt(Character.toString(key.charAt(which)));
            int t = intval + (int) (original.charAt(x));
            if(t > 177) {
                //leave it be
                encrypted += ":::::";
            } else {
                encrypted += "" + (char) (intval + (original.charAt(x)));
            }
            if(which == (key.length() - 1)) {
                which = 0;
            } else {
                which++;
            }
        }
        return encrypted;
    }
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        System.out.println("Key: ");
        setKey(i.nextLine());
        System.out.println("String: ");
        encryptString(i.nextLine());
    }
}
