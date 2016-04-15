import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DocNode {
    private String name;
    private String type;
    private File a;
    private boolean isEnterable;

    public DocNode(String name) {
        this.name = name;
        this.type = null;
        a = null;
        isEnterable = true;
    }

    public DocNode(String name, String fileName, String type) {
        this.name = name;
        isEnterable = true;
        a = new File(fileName);
    }

    public DocNode(String name, String fileName,
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

    public void makeStage(Stage stage) {
        if (type == null) {
            System.out.println("Nothing to display");

        } else if (type.equals("audio")) {
            Media media = new Media(a.getPath());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            stage.setTitle(a.getName());
        } else if (type.equals("video")) {

        } else if (type.equals("picture")) {

        } else if (type.equals("text")) {
            String fileText = "";
            try {
                Scanner scan = new Scanner(a);
                while (scan.hasNext()) {
                    fileText = fileText + "\n" + scan.nextLine();
                }
                Text text = new Text(fileText);
                text.setFont(new Font(10));
                TextArea textDisplay = new TextArea(fileText);
                stage.setScene(new Scene(textDisplay));
                stage.setTitle(a.getName());
            } catch (FileNotFoundException e) {
            }
        } else if (type.equals("audio"))

        {

        }

    }
}
