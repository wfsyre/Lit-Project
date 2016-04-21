import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<DocNode> docs;
    private HashMap<Integer, StoryNode> folders;
    private StoryNode next;
    private String pass;
    private String name;
    private StoryNode previous;
    private boolean isLocked;
    private boolean isCascadeLock;
    private boolean isDependent;
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
        folders = new HashMap<Integer, StoryNode>();
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

    public void displayDocs(String path) {
        updateDependencies();
        if (previous != null) {
            System.out.println("<-- Back");
        }
        for (int i = 0; i < docs.size() + folders.size(); i++) {
            if (folders.containsKey(i)) {
                if (folders.get(i).isDependent()) {
                    System.out.println(
                                    "$$$" + folders.get(i).getName() + "#^#&");
                } else {
                    System.out.println(folders.get(i).getName());
                }
            }
            if (i < docs.size()) {
                DocNode doc = docs.get(i);
                if (doc.isLocked() || doc.getIsDependent()) {
                    System.out.println("$$$" + doc.getName() + "#^#&");
                } else {
                    System.out.println(doc.getName());
                }
            }
        }
        if (next != null) {
            System.out.println("--> " + next.getName());
        }
    }

    public boolean showDoc(String name, Stage stage) {
        boolean found = false;
        for (int i = 0; i < docs.size(); i++) {
            DocNode doc = docs.get(i);
            if (doc.getName().equals(name)) {
                found = true;
                if (doc.getIsDependent()) {
                    System.out.println(
                                    "You must unlock a previous document before this one is available");
                    return false;
                } else {
                    doc.makeStage(stage);
                }
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

    public StoryNode getNext() {
        return next;
    }

    public StoryNode getPrevious() {
        return previous;
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    public void updateDependencies() {
        if (isCascadeLock) {
            boolean hereOn = false;
            for (int i = 0; i < docs.size(); i++) {
                if (folders.containsKey(i)) {
                    if (folders.get(i).isLocked()) {
                        hereOn = true;
                    }
                }
                if (!hereOn && docs.get(i).isLocked()) {
                    hereOn = true;
                }
                if (hereOn) {
                    if (folders.containsKey(i)) {
                        folders.get(i).setDependent(true);
                    }
                    docs.get(i).setIsDependent(true);
                } else {
                    docs.get(i).setIsDependent(false);
                    if (folders.containsKey(i)) {
                        folders.get(i).setDependent(false);
                    }
                }
            }
        }
    }

    public void addFolder(StoryNode a, int b) {
        folders.put(b, a);
    }

    public void setDependent(boolean value) {
        isDependent = true;
    }

    public boolean isDependent() {
        return isDependent;
    }
}
