import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;

public class ProjectRunner extends Application {
    StoryNode current;
    String path;

    public void start(Stage stage) {
        Stage firstStage = new Stage();
        StoryNode coaches = new StoryNode("Coaches");
        StoryNode tribBook = new StoryNode("$#$%Tribute Book$#%$", coaches,
                        "gold", true, true);
        StoryNode start = new StoryNode("Beginnings", tribBook, "", false,
                        true);
        DocNode readMe = new DocNode("README", "README.txt", "text", false);
        DocNode goldQuestion = new DocNode("Golden Question",
                        "Golden Question.txt", "text", true, "pass");
        DocNode goldBio = new DocNode("Golden Tornadoes Team",
                        "Golden Tornadoes Team.txt", "text", false);
        DocNode coverImage = new DocNode("Tribute Book Cover", "Cover.jpg",
                        "image", false);
        DocNode forewardImage = new DocNode("Tribute Book Foreword",
                        "Foreword.jpg", "image", false);
        DocNode teamImage = new DocNode("Tribute Book Team", "Team.jpg",
                        "image", false);

        start.addDoc(goldQuestion);
        start.addDoc(goldBio);
        tribBook.addDoc(coverImage);
        tribBook.addDoc(forewardImage);
        tribBook.addDoc(teamImage);
        String answer = new String();
        Scanner scan = new Scanner(System.in);
        current = start;
        path = current.getName() + ">";
        while (!answer.equals("quit")) {
            System.out.println("C:\\" + path);
            System.out.println(
                            "please enter a document name, or the password for the next document, or type \"help\"");
            current.displayDocs(path);
            answer = scan.nextLine();
            if (answer.equals(current.getNext().getName())) {
                if (current.accessNext()) {
                    current = current.getNext();
                    path = path.substring(0, path.length() - 1) + "\\"
                                    + current.getName() + ">";
                }
            } else if (answer.equals("help")) {
                readMe.makeStage(firstStage);
                firstStage.showAndWait();
            } else if (answer.equals("back")) {
                if (current.hasPrevious()) {
                    path = path.substring(0, path.length()
                                    - current.getName().length() - 2);
                    current = current.getPrevious();
                } else {
                    System.out.println("Cannot go to previos directory");
                }
            } else {
                if (current.showDoc(answer, firstStage)) {
                    firstStage.showAndWait();
                }
            }
        }
    }
}
