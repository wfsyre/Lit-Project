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
        StoryNode gildedStorms = new StoryNode("Gilded Storms", goldenTornadoes, "faculty", true, true);
        StoryNode dist = new StoryNode("Distinguished Faculty", start, "Howey", true, true);
        StoryNode pGE = new StoryNode("Project Gold Experience", dist, "Edwards", true, true);
        StoryNode fOB = new StoryNode("Fall of Babel", pGE, "Glory Hound", true, true);
        StoryNode lll = new StoryNode("Location Location Location", fOB, "", false, true);
        StoryNode end = new StoryNode("1996", start, "", false, true);
        StoryNode secret = new StoryNode("Clever Girl", start, "", false, true);
        
        
        //Tribute Images
        DocNode t1 = new DocNode("Football Season", "1928 Fball Season.jpg", "image", false);
        DocNode t2 = new DocNode("Fan mail", "Bae Watch.jpg", "image", false);
        DocNode t3 = new DocNode("Burton and Bary","Burton and Bary.jpg","image", false);
        DocNode t4 = new DocNode("Coach Alexander","Coach Alexander.jpg","image", false);
        DocNode t5 = new DocNode("Grant Field","Grant Field.jpg","image", false);
        DocNode t6 = new DocNode("Faces 1","Important Faces 1.jpg","image", false);
        DocNode t7 = new DocNode("Faces 2","Important Faces 2.jpg","image", false);
        DocNode t8 = new DocNode("Faces 3","Important Faces 3.jpg","image", false);
        DocNode t9 = new DocNode("Overhead","Overhead.jpg","image", false);
        DocNode t10 = new DocNode("Praise","Praise.jpg","image", false);
        DocNode t11 = new DocNode("Credits","Printings By.jpg","image", false);
        DocNode t12 = new DocNode("Rose Bowl","Rose Bowl.jpg","image", false);
        DocNode t13 = new DocNode("Scores","Scores.jpg","image", false);
        DocNode t14 = new DocNode("Tackle","Tackle 1.jpg","image", false);
        DocNode t15 = new DocNode("Team Info","Team Info.jpg","image", false);
        DocNode t16 = new DocNode("Team Rough","Team Rough.jpg","image", false);
        DocNode t17 = new DocNode("Touchdown","Touchdown.jpg","image", false);
        DocNode t18 = new DocNode("Warner and Stumpy","Warner and Stumpy.jpg","image", false);
        DocNode coverImage = new DocNode("Tribute Book Cover", "Cover.jpg",
                "image", false);
        DocNode forewardImage = new DocNode("Tribute Book Foreword",
                "Foreword.jpg", "image", false);
        DocNode teamImage = new DocNode("Tribute Book Team", "Team Info.jpg",
                "image", false);
        //Documents
        DocNode secretDoc = new DocNode("Secret", "secret.txt", "text", false);
        DocNode inf = new DocNode("Inflitrator Incident", "infiltrator incident.txt", "text", true, "Einstein");
        DocNode sec1 = new DocNode("Bill and Bill", "Bill and Bill.jpg", "image", false);
        DocNode sec2 = new DocNode("Bob Lang", "Bob Lang 1.jpg", "image", false);
        DocNode sec3 = new DocNode("Bob Lang 2", "Bob Lang 2.jpg", "image", false);
        DocNode sec4 = new DocNode("Bob Lang 2.5", "Bob Lang2.5.jpg", "image", false);
        DocNode investigate = new DocNode("The End", "Text/Final Reveal.txt", "text", false);
        DocNode finalHints = new DocNode("The End?", "Text/last puzzle hints.txt", "text", false);
        DocNode rsKing = new DocNode("King's Reflection", "Text/R. S. King Journal 2.txt", "text", false);
        DocNode centroids = new DocNode("Centroid Notes", "centroid notes.txt", "text", true, "Swarm");
        DocNode eAll3 = new DocNode("Edward to All 3", "Request WEC 2.jpg", "image", false);
        DocNode eAll2 = new DocNode("Edward to All 2", "Request WEC 1.jpg", "image", false);
        DocNode eAll1 = new DocNode("Edward to All 1", "Request SLC.jpg", "image", false);
        DocNode ek1 = new DocNode("Edward-King 1", "Request King 1.jpg", "image", false);
        DocNode ek2 = new DocNode("Edward-King 2", "Request King 2.jpg", "image", false);
        DocNode ke1 = new DocNode("King-Edward", "King Fights Back.jpg", "image", false);
        DocNode excel = new DocNode("Current Research", "ResearchPaperArchive.xlsx", "excel", false);
        DocNode uL1 = new DocNode("Underling's log #1", "Text/Assistant Log 1.txt", "text", false);
        DocNode uL2 = new DocNode("Underling's log #2", "Text/Assistant Log 2.txt", "text", true, "King");
        DocNode readMe = new DocNode("README", "Text/README.txt", "text", false);
        DocNode gfAudio = new DocNode("Interview", "test.mp3", "audio", false);
        DocNode technique = new DocNode("technique", "Text/technique.txt", "text", true, "John Doe");
        DocNode startDoc4 = new DocNode("unencrypt.exe", "Text/puzz4start.txt", "text", false);
        DocNode campusMapHint = new DocNode("Location", "Text/location.txt", "text", false);
        DocNode goldQuestion = new DocNode("Golden Question",
                        "Text/Golden Question.txt", "text", false);
        
        start.addDoc(0, gfAudio);
        start.addDoc(1, technique);
        start.addFolder(2, goldenTornadoes);
        start.addFolder(3, dist);
        start.addFolder(4, end);
        goldenTornadoes.addFolder(0, tribBook);
        goldenTornadoes.addDoc(1, goldQuestion);
        goldenTornadoes.addFolder(2, gildedStorms);
        gildedStorms.addDoc(0, campusMapHint);
        tribBook.addDoc(0, coverImage);
        tribBook.addDoc(1, forewardImage);
        tribBook.addDoc(2, teamImage);
        tribBook.addDoc(3, t1);
        tribBook.addDoc(4, t2);
        tribBook.addDoc(5, t3);
        tribBook.addDoc(6, t4);
        tribBook.addDoc(7, t5);
        tribBook.addDoc(8, t6);
        tribBook.addDoc(9, t7);
        tribBook.addDoc(10, t8);
        tribBook.addDoc(11, t9);
        tribBook.addDoc(12, t10);
        tribBook.addDoc(13, t11);
        tribBook.addDoc(14, t12);
        tribBook.addDoc(15, t13);
        tribBook.addDoc(16, t14);
        tribBook.addDoc(17, t15);
        tribBook.addDoc(18, t16);
        tribBook.addDoc(19, t17);
        tribBook.addDoc(20, t18);
        puzz4.addDoc(0, startDoc4);
        puzz4.addDoc(1, campusMapHint);
        dist.addDoc(0, excel);
        dist.addFolder(1, pGE);
        pGE.addDoc(0, eAll1);
        pGE.addDoc(1, eAll2);
        pGE.addDoc(2, eAll3);
        pGE.addDoc(3, uL1);
        pGE.addDoc(4, ek1);
        pGE.addDoc(5, ek2);
        pGE.addDoc(6, ke1);
        pGE.addDoc(7, uL2);
        pGE.addFolder(8, fOB);
        fOB.addDoc(0, rsKing);
        fOB.addDoc(1, centroids);
        end.addDoc(0, sec1);
        end.addDoc(1, sec2);
        end.addDoc(2, sec3);
        end.addDoc(3, sec4);
        end.addDoc(4, inf);
        end.addDoc(5,  investigate);
        lll.addDoc(0, finalHints);
        secret.addDoc(0, secretDoc);
        
        
        
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
                			story.setIsLocked(false);
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
            	if (current == secret) {
            		current = start;
            		path = start.getName();
            	} else if (current.hasPrevious()) {
                    path = path.substring(0, path.length()
                                    - current.getName().length() - 2) + ">";
                    current = current.getPrevious();
                } else {
                    System.out.println("Cannot go to previous directory");
                }
            } else if (answer.equals("illuminati")) {
            	System.out.println("Secret file system activated");
            	path = "Clever Girl>";
            	current = secret;
            	
            }
            System.out.println("\n");
        }
        System.exit(0);
    }
}
