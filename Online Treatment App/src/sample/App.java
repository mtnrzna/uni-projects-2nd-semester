package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App {
    public App(Stage primaryStage) throws IOException {
        loginStage = primaryStage;
        loginStage.setTitle("Medical Treatment App.");
        loginRoot = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
        loginScene = new Scene(loginRoot);
        data = new Data();
    }

    Data data;
    public Data getData() {
        return data;
    }

    private Stage loginStage;
    private Parent loginRoot;
    private Scene loginScene;
    private Stage signupStage;
    private Parent signupRoot;
    private Scene signupScene;
    private Stage patientStage;
    private Parent patientRoot;
    private Scene patientScene;
    private Stage doctorStage;
    private Parent doctorRoot;
    private Scene doctorScene;

    public Stage getPatientStage() {
        return patientStage;
    }
    public Stage getDoctorStage() {
        return doctorStage;
    }

    private Stage incorrectInfoPopupStage;
    private Parent incorrectInfoPopupRoot;
    private Scene incorrectInfoPopupScene;
    private Stage notSavedPopupStage;
    private Parent notSavedPopupRoot;
    private Scene notSavedPopupScene;

    public Stage getSignupStage() {
        return signupStage;
    }


    private Boolean userSaved;
    public Boolean getUserSaved() {
        return userSaved;
    }


    private Person user;
    public Person getUser() {
        return user;
    }
    public void setUser(Person user) {
        this.user = user;
    }



    public void showLoginWindow() throws IOException {
        loginStage.setScene(loginScene);
        loginStage.show();
    }


    public void showPatientWindow() throws IOException {
        patientStage = new Stage();
        patientStage.setTitle("Patient Window");
        patientRoot = FXMLLoader.load(getClass().getResource("patientWindow.fxml"));
        patientScene = new Scene(patientRoot);
        patientStage.setScene(patientScene);
        System.out.println("Signed in as: " +((Patient) user).getFirstName()+" " +((Patient) user).getLastName());
        patientStage.show();
    }

    public void showDoctorWindow() throws IOException {
        doctorStage = new Stage();
        doctorStage.setTitle("Doctor Window");
        doctorRoot = FXMLLoader.load(getClass().getResource("doctorWindow.fxml"));
        doctorScene = new Scene(doctorRoot);
        doctorStage.setScene(doctorScene);
        System.out.println("Signed in as: " +((Doctor) user).getFirstName()+" " +((Doctor) user).getLastName());
        doctorStage.show();
    }


    public void showSignUpWindow() throws  IOException{
        signupStage = new Stage();
        signupStage.setTitle("Sign Up");
        signupRoot = FXMLLoader.load(getClass().getResource("signUpWindow.fxml"));
        signupScene = new Scene(signupRoot);
        signupStage.setScene(signupScene);
        signupStage.show();
    }


    public void showPopupWithOk(String announcement) throws IOException {
        incorrectInfoPopupStage = new Stage();
        incorrectInfoPopupStage.setResizable(false);
        incorrectInfoPopupStage.setTitle("Popup");
        PopupWithOk.announcement = announcement;
        incorrectInfoPopupRoot = FXMLLoader.load(getClass().getResource("popupWithOk.fxml"));
        incorrectInfoPopupScene = new Scene(incorrectInfoPopupRoot);
        incorrectInfoPopupStage.setScene(incorrectInfoPopupScene);
        incorrectInfoPopupStage.initModality(Modality.APPLICATION_MODAL);
        incorrectInfoPopupStage.showAndWait();
    }

    public void showPopupWithYesNo(String announcement, ActionEvent e) throws IOException {
        notSavedPopupStage = new Stage();
        notSavedPopupStage.setResizable(false);
        notSavedPopupStage.setTitle("Popup");
        PopupWithYesNo.announcement = announcement;
        PopupWithYesNo.e = e;
        notSavedPopupRoot = FXMLLoader.load(getClass().getResource("popupWithYesNo.fxml"));
        notSavedPopupScene = new Scene(notSavedPopupRoot);
        notSavedPopupStage.setScene(notSavedPopupScene);
        notSavedPopupStage.initModality(Modality.APPLICATION_MODAL);
        notSavedPopupStage.showAndWait();
    }
    

    public Stage getStage(ActionEvent e) throws IOException {
        return  (Stage) ((Node) e.getSource()).getScene().getWindow();
    }

    public Scene getScene(ActionEvent e) throws IOException {
        return  (Scene) ((Node) e.getSource()).getScene();
    }




}
