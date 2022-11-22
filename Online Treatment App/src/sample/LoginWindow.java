package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginWindow  {


    @FXML private TextField patientEmailTxtFld;
    @FXML private PasswordField patientPssWrdTxtFld;
    @FXML private TextField doctorEmailTxtFld;
    @FXML private PasswordField doctorPssWrdTxtFld;
    @FXML private Button patLoginButton;
    @FXML private Button docLoginButton;



    public void patientLoginClicked(ActionEvent e) throws IOException {
        Main.getApp().getData().readPatientFile();
        Main.getApp().getData().readDoctorFile();
        if(checkIfPatientInfoIsCorrect()){
            patientEmailTxtFld.clear();
            patientPssWrdTxtFld.clear();
            Main.getApp().getStage(e).close();
            Main.getApp().showPatientWindow();
        }
        else{
            Main.getApp().showPopupWithOk("Please enter the correct information");
        }    }

    public boolean checkIfPatientInfoIsCorrect() {
        if(patientEmailTxtFld.equals("") || patientPssWrdTxtFld.equals(""))
            return false;

        int numberOfPatients = Main.getApp().getData().getPatientList().size();
        for (int i = 0; i < numberOfPatients; i++) {
            if (Main.getApp().getData().getPatientList().get(i).getEmail()
                    .equals(patientEmailTxtFld.getText()) &&
                    Main.getApp().getData().getPatientList().get(i).getPassword()
                            .equals(patientPssWrdTxtFld.getText())) {
                Main.getApp().setUser(Main.getApp().getData().getPatientList().get(i));
                return true;
            }
        }
        return false;
    }


    /****************************************************/
    public void doctorLoginClicked(ActionEvent e) throws IOException {
        Main.getApp().getData().readPatientFile();
        Main.getApp().getData().readDoctorFile();
        if(checkIfDoctorInfoIsCorrect()){
            doctorEmailTxtFld.clear();
            doctorPssWrdTxtFld.clear();
            Main.getApp().getStage(e).close();
            Main.getApp().showDoctorWindow();
        }
        else{
            Main.getApp().showPopupWithOk("Please enter the correct information");
        }
    }

    public boolean checkIfDoctorInfoIsCorrect() {
        if(doctorEmailTxtFld.equals("") || doctorPssWrdTxtFld.equals(""))
            return false;

        int numberOfDoctors = Main.getApp().getData().getDoctorList().size();
        for (int i = 0; i < numberOfDoctors; i++) {
            if (Main.getApp().getData().getDoctorList().get(i).getEmail()
                    .equals(doctorEmailTxtFld.getText()) &&
                    Main.getApp().getData().getDoctorList().get(i).getPassword()
                            .equals(doctorPssWrdTxtFld.getText())) {
                Main.getApp().setUser(Main.getApp().getData().getDoctorList().get(i));
                return true;
            }
        }
        return false;
    }

    public void showSignupWindow(ActionEvent e) throws IOException {
        Main.getApp().getStage(e).hide();
        Main.getApp().showSignUpWindow();
    }

}
