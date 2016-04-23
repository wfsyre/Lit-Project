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
        StoryNode tribBook = new StoryNode("Tribute Book", goldenTornadoes, "", false, false);
        StoryNode puzz4 = new StoryNode("Physics Building", null, "", false, true);
        StoryNode olympics = new StoryNode("Building Site", puzz4, "Howey", true, true);
        StoryNode gildedStorms = new StoryNode("Gilded Storms", goldenTornadoes, "faculty", true, true);
        StoryNode dist = new StoryNode("Distinguished Faculty", start, "Howey", true, true);
        StoryNode pGE = new StoryNode("Project Gold Experience", start, "Edwards", true, true);
        //Documents
        DocNode excel = new DocNode("Current Research", "ResearchPaperArchive.xlsx", "excel", false);
        
        DocNode readMe = new DocNode("README", "Text/README.txt", "text", false);
        DocNode gfAudio = new DocNode("Interview", "test.mp3", "audio", false);
        DocNode technique = new DocNode("technique", "Text/technique.txt", "text", true, "John Doe");
        DocNode startDoc4 = new DocNode("unencrypt.exe", "Text/puzz4start.txt", "text", false);
        DocNode campusMapHint = new DocNode("Location", "Text/location.txt", "text", true, "faculty");
        DocNode goldQuestion = new DocNode("Golden Question",
                        "Golden Question.txt", "text", false);
        DocNode coverImage = new DocNode("Tribute Book Cover", "Cover.jpg",
                        "image", false);
        DocNode forewardImage = new DocNode("Tribute Book Foreword",
                        "Foreword.jpg", "image", false);
        DocNode teamImage = new DocNode("Tribute Book Team", "Team.jpg",
                        "image", false);
        
        start.addDoc(0, gfAudio);
        start.addDoc(1, technique);
        start.addFolder(2, goldenTornadoes);
        start.addFolder(3, dist);
        goldenTornadoes.addFolder(0, tribBook);
        goldenTornadoes.addDoc(1, goldQuestion);
        goldenTornadoes.addFolder(2, gildedStorms);
        gildedStorms.addDoc(0, campusMapHint);
        tribBook.addDoc(0, coverImage);
        tribBook.addDoc(1, forewardImage);
        tribBook.addDoc(2, teamImage);
        puzz4.addDoc(0, startDoc4);
        puzz4.addDoc(1, campusMapHint);
        dist.addDoc(0, excel);
        dist.addFolder(1, pGE);
        String answer = "";
        Scanner scan = new Scanner(System.in);
        current = start;
        path = current.getName() + ">";
        while (!answer.equals("quit")) {
            System.out.println("C:\\" + path);
            current.displayDocs(path);
            System.out.println(
                    "please enter a document name, folder name, \"back\" or \"help\"");
            answer = scan.nextLine();
            if (current.contains(answer)) {
            	Node temp = current.get(answer);
                if (temp instanceof StoryNode) {
                	StoryNode story = (StoryNode) temp;
                	if (story.isDependent()) {
                		System.out.println("This folder is unable to be accessed at this time");
                	} else if (story.isLocked()) {
                		System.out.println("This folder is locked, please enter the key");
                		String userPass = scan.nextLine();
                		if (userPass.equals(story.getPass())) {
                			System.out.println("Success");
                			current = story;
                			path = path.substring(0, path.length() - 1) + "\\" + current.getName() + ">";
                		} else {
                			System.out.println("Incorrect pass");
                		}
                	} else {
                		current = story;
                		path = path.substring(0, path.length() - 1) + "\\" + current.getName() + ">";
                	}
                } else {
                	DocNode doc = (DocNode) temp;
                	if (doc.isDependent()) {
                		System.out.println("This document cannot be accessed at this time");
                	} else {
                	    doc.makeStage(firstStage);
                	    firstStage.showAndWait();
                	}
                }
            } if (answer.equals("help")) {
                readMe.makeStage(firstStage);
                firstStage.showAndWait();
            } else if (answer.equals("back")) {
                if (current.hasPrevious()) {
                    path = path.substring(0, path.length()
                                    - current.getName().length() - 2) + ">";
                    current = current.getPrevious();
                } else {
                    System.out.println("Cannot go to previous directory");
                }
            } else if (answer.equals("triangle")) {
            	System.out.println("Secret file system activated");
            	path = "C:\\Secret>";
            }
            System.out.println("\n");
        }
        System.exit(0);
    }
}
