import java.util.ArrayList;

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
}
