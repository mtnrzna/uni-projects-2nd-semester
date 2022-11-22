package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialWindow {
    private @FXML
    TextField rowTxtFld, columnTxtFld;

    public void playButtonClicked(javafx.event.ActionEvent e) throws IOException {
        if(!rowTxtFld.getText().trim().equals("") && !columnTxtFld.getText().trim().equals("")&&
                Main.getApp().isNumeric(rowTxtFld.getText())&& Main.getApp().isNumeric(columnTxtFld.getText())){
            if(Integer.parseInt(rowTxtFld.getText().trim())>=16 &&Integer.parseInt(rowTxtFld.getText().trim())<51 &&
                    Integer.parseInt(columnTxtFld.getText().trim())>=16 &&Integer.parseInt(columnTxtFld.getText().trim())<51){
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                Main.getApp().setRow(Integer.parseInt(rowTxtFld.getText().trim()));
                Main.getApp().setColumn(Integer.parseInt(columnTxtFld.getText().trim()));
                Main.getApp().initializeCellsSituation();
                Main.getApp().ShowSelectCellsWindow();
            }
        }

    }
}
