package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static App app;
    public static App getApp() {
        return app;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        app = new App(primaryStage);
        app.showWarehouseManagerWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
