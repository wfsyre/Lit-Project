import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;

public class DocNode extends Node {
    private String type;
    private File a;

    public DocNode(String name) {
        this(name, "", "", false, "");
    }

    public DocNode(String name, String fileName, String type) {
        this(name, fileName, type, false, "");
    }

    public DocNode(String name, String fileName, String type,
                    boolean isLocked) {
        this(name, fileName, type, isLocked, "");
    }

    public DocNode(String name, String fileName,
                    String type, boolean isLocked, String pass) {
        this.name = name;
        a = new File(fileName);
        this.type = type;
        this.isLocked = isLocked;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public boolean isEnterable() {
        return isLocked;
    }

    public String getPass() {
        return pass;
    }

    public void makeStage(Stage stage) {
        if (type == null) {
            System.out.println("Nothing to display");

        } else if (isDependent) {
            return;
        } else if (type.equals("audio")) {
            if (isLocked) {
                Scanner input = new Scanner(System.in);
                System.out.println(
                                "Please enter the password to view this document");
                String string = input.nextLine();
                if (string.equals(pass)) {
                    System.out.println("Entry granted");
                    isLocked = false;
                }
            }
            if (!isLocked) {
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
            } else {
                System.out.println("Entry denied");
            }
        } else if (type.equals("image")) {
            if (isLocked) {
                Scanner input = new Scanner(System.in);
                System.out.println(
                                "Please enter the password to view this document");
                String string = input.nextLine();
                if (string.equals(pass)) {
                    System.out.println("Entry granted");
                    isLocked = false;
                }
            }
            if (!isLocked) {
                ImageView viewer = new ImageView();
                Image image = new Image(a.getName());
                viewer.setImage(image);
                viewer.setRotate(90);
                viewer.setFitWidth(700);
                viewer.setFitHeight(700);
                viewer.setPreserveRatio(false);
                viewer.setCache(true);
                Group root = new Group();
                root.getChildren().addAll(viewer);
                stage.setScene(new Scene(root));
                stage.setTitle(a.getName());
                stage.sizeToScene();
            } else {
                System.out.println("Entry denied");
            }
        } else if (type.equals("text")) {
            String fileText = "";
            try {
                Scanner scan = new Scanner(a);
                while (scan.hasNext()) {
                    fileText = fileText + "\n" + scan.nextLine();
                }
                if (isLocked) {
                    String[] strings = fileText.split("\n");
                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = Encrypt.litcryption(strings[i], pass);
                    }
                    Scanner input = new Scanner(System.in);
                    System.out.println(
                                    "The file you are attempting to open is encrypted, please enter the password");
                    String userPass = input.nextLine();
                    fileText = "";
                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = Encrypt.litunencryption(strings[i],
                                        userPass);
                        if (i == 0 || strings[i].equals("")) {
                            fileText = strings[i];
                        } else {
                            fileText = fileText + "\n" + strings[i];
                        }
                    }
                    if (userPass.equals(pass)) {
                        isLocked = false;
                    }
                }
                TextArea textDisplay = new TextArea(fileText);
                stage.setScene(new Scene(textDisplay));
                stage.setTitle(a.getName());
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } else if (type.equals("excel")) {
        	Desktop dt = Desktop.getDesktop();
        	try {
        	    dt.open(a);
        	} catch (Exception e) {
        		System.out.println(e);
        	}
        	TextArea text = new TextArea("Useful Excel Commands\nctrl-f");
        	stage.setScene(new Scene(text));
        	stage.setAlwaysOnTop(true);
        } else {
        	System.out.println("File type not supported");
        }
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setIsDependent(boolean value) {
        isDependent = value;
    }

    public boolean getIsDependent() {
        return isDependent;
    }
}
