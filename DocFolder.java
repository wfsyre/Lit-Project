import java.util.ArrayList;

import javafx.stage.Stage;

public class DocFolder extends Doc {
    ArrayList<DocNode> docs;
    String name;

    public DocFolder(String name) {
        this(name, false, "");
    }

    public DocFolder(String name, boolean locked, String pass) {
        this.name = name;
        this.isLocked = locked;
        this.pass = pass;
        docs = new ArrayList<DocNode>();
    }

    public void displayDocs(String path, Stage stage) {
        System.out.println(path + "\\" + name + ">");

    }

    public String getName() {
        return name;
    }
}
