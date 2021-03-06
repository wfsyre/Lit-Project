import java.util.Scanner;

public class Encrypt {
    public static void do1(String str) {
        // Scanner scan=new Scanner(System.in);
        String ans = "";
        // String first=scan.next();
        String first = str;
        for (int i = 0; i <= first.length() - 1; i++) {
            char a = first.charAt(i);
            a++;
            if (a > 126) {
                a = 33;
            }
            ans = ans + a;
        }
        System.out.println(ans);
    }

    public static void undo1() {
        Scanner scan = new Scanner(System.in);
        String ans = "";
        String first = scan.next();
        for (int i = 0; i <= first.length() - 1; i++) {
            char a = first.charAt(i);
            a--;
            if (a < 33) {
                a = 127;
            }
            ans = ans + a;
        }
        System.out.println(ans);
        scan.close();
    }

    public static String doPass(String encode, String pass) {
        String ans = "";
        int lene = encode.length();
        int lenp = pass.length();
        int diff = lene - lenp;
        String newPass = pass;
        int count = 0;
        if (diff > 0) {
            for (int i = lenp; i < lene; i++) {
                newPass = newPass + pass.charAt(count);
                count++;
                if (count == lenp) {
                    count = 0;
                }
            }
        }
        for (int k = 0; k < newPass.length(); k++) {
            count = 0;
            char x = encode.charAt(k);
            char y = newPass.charAt(k);
            int temp = x + y;
            char newChar = (char) temp;
            while (newChar > 126) {
                newChar = (char) ((newChar - 126) + 32);
                count++;
            }
            ans = ans + newChar;
        }
        return ans;
    }

    public static String undoPass(String encoded, String pass) {
        String ans = "";
        String text = encoded;
        int lenu = text.length();
        int lenp = pass.length();
        int diff = lenu - lenp;
        String newPass = pass;
        int count = 0;
        if (diff > 0) {
            for (int i = lenp; i < lenu; i++) {
                newPass = newPass + pass.charAt(count);
                count++;
                if (count == lenp) {
                    count = 0;
                }
            }
        }
        // after the String are of equal length
        for (int k = 0; k < newPass.length() && k < text.length(); k++) {
            char x = text.charAt(k);
            char y = newPass.charAt(k);
            int temp = x - y;
            while (temp < 32) {
                temp = (temp + 94);
            }
            char newChar = (char) temp;
            ans = ans + newChar;
        }
        return ans;
    }

    public static String litcryption(String text, String encode) {
        if (text.isEmpty()) {
            return "";
        }
        String ans = "";
        for (int k = 0; k < text.length(); k++) {
            char x = text.charAt(k);
            char y = encode.charAt(k % encode.length());
            int temp = x + y;
            char newChar = (char) temp;
            while (newChar > 126) {
                newChar = (char) ((newChar - 126) + 32);
            }
            ans = ans + newChar;
        }
        return ans;
    }

    public static String litunencryption(String text, String decode) {
        if (text.isEmpty()) {
            return "";
        }
        String ans = "";
        for (int k = 0; k < text.length(); k++) {
            char x = text.charAt(k);
            char y = decode.charAt(k % decode.length());
            int temp = x - y;
            while (temp < 32) {
                temp = (temp + 94);
            }
            char newChar = (char) temp;
            ans = ans + newChar;
        }
        return ans;
    }
}
