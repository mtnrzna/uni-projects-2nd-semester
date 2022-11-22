package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class App implements Initializable {
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //primary stage is the stage that show and asks how many cells user wants
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("initialWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }




    private int cellsSituation[][];
    public void initializeCellsSituation(){
        cellsSituation = new int[row][column];
        for(int j = 0; j<row;j++){
            for(int i = 0; i<column;i++){
                cellsSituation[j][i] = 0;
            }
        }
    }

    public  void updateCellsSituation(int value, int row, int column){
        cellsSituation[row][column] = value;
    }
    public int[][] getCellsSituation() {
        return cellsSituation;
    }
    public void setCellsSituation(int[][] cellsSituation) {
        this.cellsSituation = cellsSituation;
    }

    private int row, column;
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }


    public void ShowSelectCellsWindow() throws IOException {
        Stage selectStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("selectAliveCells.fxml"));
        selectStage.setTitle("Select alive cells:");
        selectStage.setScene(new Scene(root));
        selectStage.show();

    }

    public void showTheGameWindow() throws IOException {
        Stage gameStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gameWindow.fxml"));
        gameStage.setTitle("Game Of Life");
        gameStage.setScene(new Scene(root));
        gameStage.show();

    }




    public boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }



}
