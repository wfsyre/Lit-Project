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
        StoryNode next = new StoryNode("answers", null, "buzz", true);
        StoryNode start = new StoryNode("Beginnings", next);
        DocNode text = new DocNode("test", "test.txt", "text", false);
        DocNode audio = new DocNode("rick", "Rick.mp3", "audio", true);
        DocNode image = new DocNode("image", "IMG_3178.jpg", "image", false);
        start.addDoc(text);
        start.addDoc(audio);
        start.addDoc(image);
        String answer = "";
        Scanner scan = new Scanner(System.in);
        current = start;
        while (!answer.equals("quit")) {
            System.out.println(
                            "please enter a document name, or the password for the next document");
            current.displayDocs();
            answer = scan.nextLine();
            if (answer.equals(next.getName())) {
                if (start.accessNext()) {
                    current = next;
                }
            } else {
                if (start.showDoc(answer, firstStage)) {
                    firstStage.showAndWait();
                }
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
