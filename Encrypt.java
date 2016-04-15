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
        String uncode = encoded;
        int lenu = uncode.length();
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
        for (int k = 0; k < newPass.length(); k++) {
            char x = uncode.charAt(k);
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
        String ans = "";
        int lent = text.length();
        int lene = encode.length();
        int diff = lent - lene;
        String newPass = encode;
        int count = 0;
        if (diff > 0) {
            for (int i = lene; i < lent; i++) {
                newPass = newPass + encode.charAt(count);
                count++;
                if (count == lene) {
                    count = 0;
                }
            }
        }
        for (int k = 0; k < newPass.length(); k++) {
            count = 0;
            char x = text.charAt(k);
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

    public static String litunencryption(String text, String decode) {
        String ans = "";
        int lent = text.length();
        int lenp = decode.length();
        int diff = lent - lenp;
        String newPass = decode;
        int count = 0;
        if (diff > 0) {
            for (int i = lenp; i < lent; i++) {
                newPass = newPass + decode.charAt(count);
                count++;
                if (count == lenp) {
                    count = 0;
                }
            }
        }
        // after the String are of equal length
        for (int k = 0; k < newPass.length(); k++) {
            char x = text.charAt(k);
            /*
            if (x == '$') {
                x = 's';
            } else if (x == '#') {
                x = 'n';
            } else if (x == '@') {
                x = 'a';
            } else if (x == '3') {
                x = 'e';
            } else if (x == '^') {
                x = 'v';
            } else if (x == '0') {
               x = 'o';
            }
            */
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
}
