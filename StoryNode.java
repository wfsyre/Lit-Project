import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<Doc> docs;
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
        docs = new ArrayList<Doc>();
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

    public void displayDocs(String path) {
        boolean cascade = false;
        for (int i = 0; i < docs.size(); i++) {
            if (isCascadeLock) {
                if (docs.get(i).isLocked()) {
                    cascade = true;
                }
                if (cascade) {
                    docs.get(i).setIsDependent(true);
                }
            }
            System.out.println(!docs.get(i).isLocked()
                            || !docs.get(i).getIsDependent()
                                            ? docs.get(i).getName()
                                            : "$$$" + docs.get(i).getName()
                                                            + "$^#*@");
        }
        if (next != null) {
            System.out.println("Folder: " + next.getName());
        }
    }

    public boolean showDoc(String name, Stage stage) {
        boolean found = false;
        for (int i = 0; i < docs.size(); i++) {
            Doc doc = docs.get(i);
            if (cas > i && isCascadeLock) {
                doc.setIsLocked(false);
            }
            if (doc.getName().equals(name)) {
                found = true;
                if (doc.getIsDependent()) {
                    System.out.println(
                                    "You must unlock a previous document before this one is available");
                    return false;
                } else if (doc instanceof DocNode) {
                    DocNode newDoc = (DocNode) doc;
                    if (newDoc.makeStage(stage)) {
                        int cas = i;
                    }
                } else {
                    DocFolder folder = (DocFolder) doc;
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
}
