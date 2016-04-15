import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<DocNode> docs;
    private StoryNode next;
    private String pass;
    private String name;
    private boolean isLocked;
    String text;

    public StoryNode(String name, ArrayList<DocNode> docs) {
        this.name = name;
        this.docs = docs;
        isLocked = false;
        String pass = "";
        next = null;
    }

    public StoryNode(String name) {
        this.name = name;
        docs = new ArrayList<DocNode>();
        isLocked = false;
        String pass = "";
        next = null;
    }

    public StoryNode(String name, StoryNode next, String pass,
                    boolean isLocked) {
        this.name = name;
        docs = new ArrayList<DocNode>();
        this.next = next;
        this.pass = pass;
        this.isLocked = isLocked;
    }

    public StoryNode(String name, StoryNode next) {
        docs = new ArrayList<DocNode>();
        this.name = name;
        this.next = next;
        isLocked = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addDoc(DocNode a) {
        docs.add(a);
    }

    public void displayDocs() {
        for (DocNode doc : docs) {
            System.out.println(doc.isEnterable() ? doc.getName()
                            : "$$$" + doc.getName() + "$^#*@");
        }
        if (next != null) {
            System.out.println("Folder: " + next.getName());
        }
    }

    public boolean showDoc(String name, Stage stage) {
        boolean found = false;
        for (DocNode doc : docs) {
            if (doc.getName().equals(name)) {
                doc.makeStage(stage);
                found = true;
            }
        }
        if (!found) {
            System.out.println("could not find document specified");
            return false;
        } else {
            System.out.println("loading document...");
            return true;
        }
    }

    public boolean accessNext() {
        if (next.isLocked()) {
            Scanner scan = new Scanner(System.in);
            System.out.println(
                            "This directory is locked, please enter the passcode to access it");
            String maybe = scan.nextLine();
            if (maybe.compareToIgnoreCase(next.getPass()) == 0) {
                System.out.println("Success!");
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        } else {
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getPass() {
        return pass;
    }

    public void setNext(StoryNode next) {
        this.next = next;
    }
}
