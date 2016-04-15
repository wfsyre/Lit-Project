import java.io.File;
import java.io.FileNotFoundException;
//port java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class LetterChanger {
    public static void main(String[] args) {
        String fileName = "test.txt";
        String pass = "pass";
        String fileText = "";
        File a = new File(fileName);
        try {
            Scanner scan = new Scanner(a);
            while (scan.hasNext()) {
                fileText = fileText + scan.nextLine();
            }
            PrintStream p = new PrintStream(a);
            fileText = Encrypt.litunencryption(fileText, pass);
            p.println(fileText);
            scan.close();
            p.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}