import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<DocNode> docs;
    private StoryNode next;
    private String pass;
    private String name;
    private boolean isLocked;
    private boolean isCascadeLock;
    private int cas = -1;
    String text;

    public StoryNode(String name) {
        this(name, null, "", false, false);
    }

    public StoryNode(String name, StoryNode next, String pass,
                    boolean isLocked, boolean isCascadeLock) {
        this.name = name;
        docs = new ArrayList<DocNode>();
        this.next = next;
        this.pass = pass;
        this.isLocked = isLocked;
        this.isCascadeLock = isCascadeLock;
    }

    public StoryNode(String name, StoryNode next) {
        this(name, next, "", false, false);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addDoc(DocNode a) {
        docs.add(a);
    }

    public void displayDocs() {
        boolean cascade = false;
        for (int i = 0; i < docs.size(); i++) {
            if (isCascadeLock) {
                if (cas != -1 && !docs.get(i).isEnterable()) {
                    cascade = true;
                    cas = i;
                }
                System.out.println(cascade ? docs.get(i).getName()
                                : "$$$" + docs.get(i).getName() + "$^#*@");
            } else {
                System.out.println(docs.get(i).isEnterable()
                                ? docs.get(i).getName()
                                : "$$$" + docs.get(i).getName() + "$^#*@");
            }
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
