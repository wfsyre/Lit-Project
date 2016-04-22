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
        //Folders
        StoryNode start = new StoryNode("Beginnings", null, "", false, true);
        StoryNode goldenTornadoes = new StoryNode("Golden Tornadoes", start, "Golden Tornadoes", true, true);
        StoryNode tribBook = new StoryNode("Tribute Book", goldenTornadoes, "gold", true, true);
        StoryNode puzz4 = new StoryNode("Physics Building", null, "", false, true);
        StoryNode olympics = new StoryNode("Building Site", puzz4, "Howey", true, true);
        //Documents
        DocNode readMe = new DocNode("README", "README.txt", "text", false);
        DocNode gfAudio = new DocNode("Interview", "test.mp3", "audio", false);
        DocNode technique = new DocNode("technique", "Text/technique.txt", "text", true, "John Doe");
        DocNode startDoc4 = new DocNode("unencrypt.exe", "Text/puzz4start.txt", "text", false);
        DocNode campusMapHint = new DocNode("Location", "Text/location.txt", "text", false, "pass");
        DocNode goldQuestion = new DocNode("Golden Question",
                        "Golden Question.txt", "text", true, "pass");
        DocNode coverImage = new DocNode("Tribute Book Cover", "Cover.jpg",
                        "image", false);
        DocNode forewardImage = new DocNode("Tribute Book Foreword",
                        "Foreword.jpg", "image", false);
        DocNode teamImage = new DocNode("Tribute Book Team", "Team.jpg",
                        "image", false);
        
        start.addDoc(gfAudio);
        start.addDoc(technique);
        start.addFolder(goldenTornadoes, 3);
        goldenTornadoes.addFolder(tribBook, 0);
        goldenTornadoes.addDoc(goldQuestion);
        tribBook.addDoc(coverImage);
        tribBook.addDoc(forewardImage);
        tribBook.addDoc(teamImage);
        puzz4.addDoc(startDoc4);
        puzz4.addDoc(campusMapHint);
        String answer = "";
        Scanner scan = new Scanner(System.in);
        current = start;
        path = current.getName() + ">";
        while (!answer.equals("quit")) {
            System.out.println("C:\\" + path);
            System.out.println(
                            "please enter a document name, folder name, \"back\" or \"help\"");
            current.displayDocs(path);
            answer = scan.nextLine();
            if (current.hasFolders()) {
            	int num = current.folderHasName(answer);
                if (num != -1) {
                	StoryNode temp = current.getFolder(num);
                	if (temp.isLocked()) {
                		System.out.println("The folder you are attempting to acces is locked. PLease provide the passcode");
                		String tempAns = scan.nextLine();
                		if (tempAns.equals(temp.getPass())) {
                			System.out.println("success");
                			current = temp;
                			current.setIsLocked(false);
                		}
                	} else {
                        current = current.getFolder(num);
                	}
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
            } else if (answer.equals("triangle")) {
            	System.out.println("Secret file system activated");
            	path = "C:\\Secret>";
            } else {
                if (current.showDoc(answer, firstStage)) {
                    firstStage.showAndWait();
                }
            }
        }
        System.exit(0);
    }
}
