package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class GameWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        row = Main.getApp().getRow();
        column = Main.getApp().getColumn();
        grid = new GridPane();
        pane.getChildren().add(grid);
        grid.setMinWidth(Region.USE_PREF_SIZE);
        grid.setMinHeight(Region.USE_PREF_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);
        createCells();
    }

    private int row, column;
    @FXML
    private Pane pane;

    private GridPane grid;

    Label labels[][];
    private void createCells() {
        labels  = new Label[row][column];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                Label label = new Label();
                labels[j][i] = label;
                label.setMinWidth(Region.USE_PREF_SIZE);
                label.setMinHeight(Region.USE_PREF_SIZE);
                label.setMaxHeight(Region.USE_PREF_SIZE);
                label.setMaxWidth(Region.USE_PREF_SIZE);
                label.setPrefHeight(30);
                label.setPrefWidth(30);
                GridPane.setConstraints(label, i, j);
                grid.getChildren().add(label);
                int finalJ = j;
                int finalI = i;
                if (Main.getApp().getCellsSituation()[finalJ][finalI] == 0) {
                    label.setStyle("-fx-background-color: white");
                    label.setStyle("-fx-border-color: #454545");
                } else if (Main.getApp().getCellsSituation()[finalJ][finalI] == 1) {
                    label.setStyle("-fx-background-color: black");
//                    label.setStyle("-fx-border-color: #454545");
                }

//                label.setStyle("-fx-border-color: #454545");

            }
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        nextGeneration();
                    }
                },
                1000
        );
    }




    public void nextGeneration() {
        int row = Main.getApp().getRow();
        int column = Main.getApp().getColumn();
        int[][] cellsSituation = Main.getApp().getCellsSituation();
        int[][] future = new int[row][column];

        // Loop through every cell
        for (int l = 1; l < row - 1; l++) {
            for (int m = 1; m < column - 1; m++) {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveNeighbours += cellsSituation[l + i][m + j];

                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= cellsSituation[l][m];

                // Implementing the Rules of Life

                // Cell is lonely and dies
                if ((cellsSituation[l][m] == 1) && (aliveNeighbours < 2))
                    future[l][m] = 0;

                    // Cell dies due to over population
                else if ((cellsSituation[l][m] == 1) && (aliveNeighbours > 3))
                    future[l][m] = 0;

                    // A new cell is born
                else if ((cellsSituation[l][m] == 0) && (aliveNeighbours == 3))
                    future[l][m] = 1;

                    // Remains the same
                else
                    future[l][m] = cellsSituation[l][m];
            }
        }

        Main.getApp().setCellsSituation(future);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        updateCells();
                    }
                },
                1000
        );

    }

    public void updateCells(){
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                Label label = labels[j][i];
                int finalJ = j;
                int finalI = i;
                if (Main.getApp().getCellsSituation()[finalJ][finalI] == 0) {
                    label.setStyle("-fx-background-color: white");
                    label.setStyle("-fx-border-color: #454545");
                } else if (Main.getApp().getCellsSituation()[finalJ][finalI] == 1) {
                    label.setStyle("-fx-background-color: black");
//                    label.setStyle("-fx-border-color: #454545");
                }

//                label.setStyle("-fx-border-color: #454545");

            }
        }
        nextGeneration();
    }
}
