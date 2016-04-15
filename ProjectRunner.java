import javafx.application.Application;
import javafx.stage.Stage;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;

public class ProjectRunner extends Application {
    FTNode current;

    public void start(Stage firstStage) {
        FTNode start = new FTNode("start", "test.txt", "text", true);
        start.makeStage(firstStage);
        firstStage.show();
    }

    public void stage1(String pass) {

    }

    public void stage2(String pass) {

    }

    public void stage3(String pass) {

    }

    public void stage4(String pass) {

    }
}
