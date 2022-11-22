package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupWithYesNo implements Initializable {
    @FXML Button yesButton, noButton;
    @FXML Label label;
    static String announcement;

    static ActionEvent e;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText(announcement);
    }


    public void clickedYes(ActionEvent e) throws IOException {
        Main.getApp().getStage(e).close();
        Main.getApp().getStage(PopupWithYesNo.e).close();
        Main.getApp().showLoginWindow();
    }
    public void clickedNo(ActionEvent e) throws  IOException{
        Main.getApp().getStage(e).close();
    }
}
