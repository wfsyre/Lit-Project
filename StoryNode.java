import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<DocNode> docs;
    private HashMap<Integer, StoryNode> folders;
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

    public StoryNode(String name, StoryNode previous, String pass,
                    boolean isLocked, boolean isCascadeLock) {
        this.name = name;
        docs = new ArrayList<DocNode>();
        this.previous = previous;
        this.pass = pass;
        this.isLocked = isLocked;
        this.isCascadeLock = isCascadeLock;
        folders = new HashMap<Integer, StoryNode>();
    }

    public StoryNode(String name, StoryNode previous) {
        this(name, previous, "", false, false);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addDoc(DocNode a) {
        docs.add(a);
    }

    public void displayDocs(String path) {
        updateDependencies();
        int num = folders.size();
        if (previous != null) {
            System.out.println("<-- back");
        }
        for (int i = 0; i <= docs.size() + folders.size(); i++) {
            if (folders.containsKey(i)) {
                if (folders.get(i).isDependent()) {
                    System.out.println(
                                    Encrypt.litcryption(folders.get(i).getName(), "aaaaaa"));
                } else {
                    System.out.println("Folder: " + folders.get(i).getName());
                }
            }
            if (i < docs.size()) {
                DocNode doc = docs.get(i);
                if (doc.isLocked() && ! doc.getIsDependent()) {
                    System.out.println("$$$" + doc.getName() + "#^#&");
                } else if (doc.getIsDependent()) {
                	System.out.println(Encrypt.litcryption(doc.getName(), "aaaaaa"));
                } else {
                    System.out.println(doc.getName());
                }
            }
        }
    }

    public boolean showDoc(String name, Stage stage) {
        boolean found = false;
        for (int i = 0; i < docs.size(); i++) {
            DocNode doc = docs.get(i);
            if (doc.getName().equals(name)) {
                found = true;
                if (doc.getIsDependent()) {
                    System.out.println("could not find document specified");
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

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getPass() {
        return pass;
    }

    public StoryNode getPrevious() {
        return previous;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
    
    public void setPrevious(StoryNode a) {
    	previous = a;
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
                } else if (hereOn) {
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
    
    public void setIsLocked(boolean value) {
    	isLocked = value;
    }
    
    public boolean hasFolders() {
    	return folders != null && !folders.isEmpty();
    }
    
    public int folderHasName(String name) {
    	for (int i: folders.keySet()) {
    		if (folders.get(i).getName().equals(name)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public StoryNode getFolder(int i) {
    	return folders.get(i);
    }
}
