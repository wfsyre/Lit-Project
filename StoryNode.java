import java.util.ArrayList;

import javafx.stage.Stage;

public class StoryNode {
    private ArrayList<DocNode> docs;
    String text;

    public StoryNode(ArrayList<DocNode> docs) {
        this.docs = docs;
    }

    public StoryNode() {
        docs = new ArrayList<DocNode>();
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
}
