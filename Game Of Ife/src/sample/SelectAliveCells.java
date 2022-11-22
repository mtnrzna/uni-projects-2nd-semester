package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectAliveCells implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        row = Main.getApp().getRow();
        column = Main.getApp().getColumn();
        createScene();
    }

    private int row, column;
    @FXML private Pane pane;

    private GridPane grid;

    private void createScene() {
        Label label = new Label("Select ALive Cells: ");
        Button playButton = new Button("Play");
        playButton.setOnAction(actionEvent -> {
            Stage thisStage = ((Stage) ((playButton).getScene().getWindow()));
            thisStage.close();
            try {
                playButtonPressed();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        grid = new GridPane();
        grid.setMinWidth(Region.USE_PREF_SIZE);
        grid.setMinHeight(Region.USE_PREF_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);

        for(int j = 0; j<row;j++){
            for(int i = 0; i<column;i++){
                Button button  = new Button();
                button.setMinWidth(Region.USE_PREF_SIZE);
                button.setMinHeight(Region.USE_PREF_SIZE);
                button.setMaxHeight(Region.USE_PREF_SIZE);
                button.setMaxWidth(Region.USE_PREF_SIZE);
                button.setPrefHeight(30);
                button.setPrefWidth(30);
                button.setStyle("-fx-background-color: white");
                button.setStyle("-fx-border-color: #454545");
                GridPane.setConstraints(button, i,j);
                grid.getChildren().add(button);

                int finalJ = j;
                int finalI = i;
                button.setOnAction(actionEvent -> {
                    if(Main.getApp().getCellsSituation()[finalJ][finalI] == 0){
                        Main.getApp().updateCellsSituation(1, finalJ, finalI);
                        button.setStyle("-fx-background-color: black");
                    }
                    else if(Main.getApp().getCellsSituation()[finalJ][finalI] == 1){
                        Main.getApp().updateCellsSituation(0, finalJ, finalI);
                        button.setStyle("-fx-background-color: white");
                        button.setStyle("-fx-border-color: #454545");
                    }
                });
            }
        }
        pane.getChildren().addAll(label,grid, playButton);
    }

    public void playButtonPressed() throws IOException {
        Main.getApp().showTheGameWindow();
    }
}
