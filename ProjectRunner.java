import java.util.HashMap;
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
        //puzzle 4 story nodes
        StoryNode olympics = new StoryNode("Building Site", null, "Howey", true, true);
        StoryNode puzz4 = new StoryNode("Physics Building", olympics, "", false, true);
        olympics.setPrevious(puzz4);
        //puzzle 4 story nodes
        HashMap<Integer, StoryNode> puzzle1Folder = new HashMap<Integer, StoryNode>();
        StoryNode tribBook = new StoryNode("Tribute Book", coaches,
                        "gold", true, true);
        StoryNode start = new StoryNode("Beginnings", tribBook, "", false,
                        true);
        DocNode readMe = new DocNode("README", "README.txt", "text", false);
        DocNode excelTest = new DocNode("Excel", "book1.xlsx", "excel", false);
        //puzzle 4 documents
        DocNode startDoc4 = new DocNode("unencrypt.exe", "Text/puzz4start.txt", "text", false);
        DocNode campusMap = new DocNode("Campus Map", "campusmap.jpg", "image", false);
        DocNode campusMapHint = new DocNode("Location", "Text/location.txt", "text", false, "pass");
        DocNode directory = new DocNode("Directory", "/Images/Map Resoure/campusdirectory.jpg", "image", false);
        // end puzzle 4 documents
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
        puzz4.addDoc(excelTest);
        puzz4.addDoc(startDoc4);
        puzz4.addDoc(campusMapHint);
        puzz4.addDoc(campusMap);
        puzz4.addDoc(directory);
        String answer = "";
        Scanner scan = new Scanner(System.in);
        current = puzz4;
        path = current.getName() + ">";
        while (!answer.equals("quit")) {
            System.out.println("C:\\" + path);
            System.out.println(
                            "please enter a document name, folder name, \"back\" or \"help\"");
            current.displayDocs(path);
            answer = scan.nextLine();
            if (current.hasNext() && answer.equals(current.getNext().getName())) {
                if (current.accessNext()) {
                    current = current.getNext();
                    current.setIsLocked(false);
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
                    System.out.println("Cannot go to previous directory");
                }
            } else {
                if (current.showDoc(answer, firstStage)) {
                    firstStage.showAndWait();
                }
            }
        }
    }
}
