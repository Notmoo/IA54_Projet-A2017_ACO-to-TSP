import gui.graph.Graph;
import gui.main_frame.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new MainFrame().initAndShow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}