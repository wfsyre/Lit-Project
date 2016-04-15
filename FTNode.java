import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FTNode {
    private String name;
    private String type;
    private File a;
    private boolean isEnterable;
    private ArrayList<FTNode> children;

    public FTNode(String name) {
        this.name = name;
        this.type = null;
        a = null;
        isEnterable = true;
    }

    public FTNode(String name, String fileName, String type) {
        this.name = name;
        isEnterable = true;
        a = new File(fileName);
    }

    public FTNode(String name, String fileName,
                    String type, boolean isEnterable) {
        this.name = name;
        this.type = type;
        this.isEnterable = isEnterable;
        a = new File(fileName);
    }

    public String getName() {
        return name;
    }

    public boolean isEnterable() {
        return isEnterable;
    }

    public void addChildren(FTNode... a) {
        for (FTNode node : a) {
            children.add(node);
        }
    }

    public void displayFile() {
        if (type == null) {
            System.out.println("Nothing to display");
        } else if (type.equals("video")) {

        } else if (type.equals("picture")) {

        } else if (type.equals("text")) {
            Stage stage = new Stage();
            String fileText = "";
            try {
                Scanner scan = new Scanner(a);
                while (scan.hasNext()) {
                    fileText = fileText + "\n" + scan.nextLine();
                }
                TextField textDisplay = new TextField(fileText);
                stage.setScene(new Scene(textDisplay));
            } catch (FileNotFoundException e) {
            }
            stage.show();
        } else if (type.equals("audio")) {

        }
    }

    public void addChildren(FTNode child) {
        children.add(child);
    }

    public ArrayList<FTNode> getChildren() {
        return children;
    }
}
