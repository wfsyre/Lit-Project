import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;

public class ProjectRunner extends Application {
    StoryNode current;

    public void start(Stage stage) {
        Stage firstStage = new Stage();
        StoryNode start = new StoryNode();
        DocNode test = new DocNode("test", "test.txt", "text", false);
        DocNode rick = new DocNode("rick", "Rick.mp3", "audio", true);
        start.addDoc(test);
        start.addDoc(rick);
        String answer = "";
        Scanner scan = new Scanner(System.in);
        while (!answer.equals("buzz")) {
            System.out.println(
                            "please enter a document name, or the password for the next document");
            start.displayDocs();
            answer = scan.nextLine();
            if (start.showDoc(answer, firstStage)) {
                firstStage.showAndWait();
            }
        }
    }

    public void stage1(String pass) {

    }

    public void stage2(String pass) {

    }

    public void stage3(String pass) {

    }

    public void stage4(String pass) {

    }
}
