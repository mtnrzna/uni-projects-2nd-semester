package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static App app;
    public static App getApp() {
        return app;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        app = new App();
        app.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("initialWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
