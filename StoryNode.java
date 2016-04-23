import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.stage.Stage;

public class StoryNode extends Node {
    private HashMap<Integer, Node> items;
    private StoryNode previous;
    private boolean isCascadeLock;
    String text;

    public StoryNode(String name) {
        this(name, null, "", false, false);
    }

    public StoryNode(String name, StoryNode previous, String pass,
                    boolean isLocked, boolean isCascadeLock) {
        this.name = name;
        this.previous = previous;
        this.pass = pass;
        this.isLocked = isLocked;
        this.isCascadeLock = true;
        items = new HashMap<Integer, Node>();
    }

    public StoryNode(String name, StoryNode previous) {
        this(name, previous, "", false, false);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addDoc(int a, DocNode b) {
        items.put(a, b);
    }

    public void displayDocs(String path) {
        updateDependencies();
        if (previous != null) {
            System.out.println("<-- back");
        }
        for (int i = 0; i <= items.size(); i++) {
        	if (items.containsKey(i)) {
        	    if (items.get(i) instanceof StoryNode) {
        	    	StoryNode story = (StoryNode) items.get(i);
                    if (story.isDependent()) {
                        System.out.println(
                                        Encrypt.litcryption(story.getName(), "aaaaaa"));
                    } else if (story.isLocked()){
                    	System.out.println("Folder: $$$" + story.getName() + "#^#&");
                    } else {
                        System.out.println("Folder: " + story.getName());
                    }
                } else {
                    DocNode doc = (DocNode) items.get(i);
                    if (doc.isLocked() && !doc.getIsDependent()) {
                        System.out.println("$$$" + doc.getName() + "#^#&");
                    } else  if (doc.getIsDependent()) {
                    	System.out.println(Encrypt.litcryption(doc.getName(), "aaaaaa"));
                    } else {
                        System.out.println(doc.getName());
                    }
                }
            }
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
            for (int i = 0; i < items.size(); i++) {
            	if (!hereOn && items.get(i).isLocked()) {
            		hereOn = true;
            		items.get(i).setIsDependent(false);
            	} else if (hereOn && items.get(i).isLocked()){
            		items.get(i).setIsDependent(true);
            	} else {
            		if (hereOn) {
            			items.get(i).setIsDependent(true);
            		} else {
            			items.get(i).setIsDependent(false);
            		}
            	}
            }
        }
    }

    public void addFolder(int a, StoryNode b) {
        items.put(a, b);
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
    
    public boolean contains(String name) {
    	for (int a: items.keySet()) {
    		if (items.get(a).getName().equals(name)) {
        		return true;
        	}
    	}
    	return false;
    }
    
    public Node get(String name) {
    	for (int a: items.keySet()) {
    		if (items.get(a).getName().equals(name)) {
    			return items.get(a);
    		}
    	}
    	return null;
    }
}
