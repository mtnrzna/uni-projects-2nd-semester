package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupWithOk implements Initializable {

    @FXML Label label;
    static String  announcement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    label.setText(announcement);
    }
    public void clickedOk(ActionEvent e) throws IOException {
        Main.getApp().getStage(e).close();
    }
}
