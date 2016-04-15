import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
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
            HBox tools = new HBox();
            URL resource = getClass().getResource(a.getName());
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            stage.setTitle(a.getName());

            // play button
            Button play = new Button("||");
            play.setOnAction(event -> {
                Status status = mediaPlayer.getStatus();
                if (status == Status.UNKNOWN || status == Status.HALTED) {
                    // don't do anything in these states
                    return;
                }
                if (status == Status.PAUSED || status == Status.READY
                                || status == Status.STOPPED) {
                    mediaPlayer.play();
                    play.setText("||");
                } else {
                    mediaPlayer.pause();
                    play.setText(">");
                }
            });
            tools.getChildren().addAll(play);
            stage.setOnCloseRequest(event -> {
                mediaPlayer.stop();
            });
            stage.setScene(new Scene(tools));
        } else if (type.equals("picture")) {

        } else if (type.equals("text")) {
            String fileText = "";
            try {
                Scanner scan = new Scanner(a);
                while (scan.hasNext()) {
                    fileText = fileText + "\n" + scan.nextLine();
                }
                if (!isEnterable) {
                    fileText = Encrypt.litcryption(fileText, "buzz");
                    Scanner input = new Scanner(System.in);
                    System.out.println(
                                    "The file you are attempting to open is encrypted, please enter the password");
                    String pass = input.nextLine();
                    fileText = Encrypt.litunencryption(fileText, pass);
                }
                TextArea textDisplay = new TextArea(fileText);
                stage.setScene(new Scene(textDisplay));
                stage.setTitle(a.getName());
            } catch (FileNotFoundException e) {
            }
        }
    }
}
