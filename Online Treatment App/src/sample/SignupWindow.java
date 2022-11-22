package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignupWindow implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getApp().getData().readPatientFile();
        Main.getApp().getData().readDoctorFile();
        file = null;

        specsMenuItemsList = new ArrayList<MenuItem>();
        Doctor.Specialization specs[] = Doctor.Specialization.values();
        int number = Doctor.Specialization.values().length;
        for (int i = 0; i < number; i++) {
            MenuItem menuItem = new MenuItem(specs[i].toString());
            specsMenuItemsList.add(menuItem);
        }
        addSpecializationToMB();
    }




    @FXML private TextField firstnameTxtFld, lastnameTxtFld, emailTxtFld, passwordTxtFld,weightTxtFld;
    @FXML private DatePicker dateOfBirth;
    @FXML private RadioButton maleRadioButton, femaleRadioButton;



/**********************************************************/
    private File file;
    @FXML Button patientImageChooser;
    public void choosePatientImage(){
        Image image = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if(!(selectedFile == null)) {
            file = new File(selectedFile.getPath());
            patientImageChooser.setText(file.getPath());
        }
    }


    public void patientSignupPressed(ActionEvent e) throws IOException {
        if(checkIfPatientEnteredInfosAreValid(e)){
            Patient.GENDER gender = null;
            if(maleRadioButton.isSelected())
                gender = Patient.GENDER.MALE;
            else if(femaleRadioButton.isSelected())
                gender = Patient.GENDER.FEMALE;


            Main.getApp().getData().createPatientData(firstnameTxtFld.getText().trim()
                    ,lastnameTxtFld.getText().trim(),emailTxtFld.getText().trim()
                    ,passwordTxtFld.getText().trim(),dateOfBirth.getValue().toString().trim(),
                    gender.toString(),weightTxtFld.getText().trim());

            int numberOfAllPatients = Main.getApp().getData().getPatientList().size();
            Main.getApp().getData().updatePatientPhoto(Main.getApp().getData().
                    getPatientList().get(numberOfAllPatients-1),file);
            file = null;
            Main.getApp().getData().updatePatientsFile();


            Main.getApp().getStage(e).close();
            Main.getApp().getSignupStage().close();
            Main.getApp().showLoginWindow();
        }
    }

    public boolean checkIfPatientEnteredInfosAreValid(ActionEvent e) throws IOException {
        for(Patient patient: Main.getApp().getData().getPatientList()){
            if(emailTxtFld.getText().trim().equals(patient.getEmail())) {
                Main.getApp().showPopupWithOk("This email has an account");
                return false;
            }
        }
        for(Doctor doctor: Main.getApp().getData().getDoctorList()) {
            if (emailTxtFld.getText().trim().equals(doctor.getEmail())) {
                Main.getApp().showPopupWithOk("This email has an account");
                return false;
            }
        }


        if(firstnameTxtFld.getText().trim().equals("")|| lastnameTxtFld.getText().trim().equals("") ||
            emailTxtFld.getText().trim().equals("") || passwordTxtFld.getText().trim().equals("") ||
            weightTxtFld.getText().trim().equals("") || dateOfBirth.getValue() == null ||
            file == null ||
            (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){
            Main.getApp().showPopupWithOk("Make sure you filled all");
            return false;
        }
        else if(passwordTxtFld.getText().length()<4) {
            Main.getApp().showPopupWithOk("Password should be equal or more than 5 characters!");
            return false;
        }
        else if(!emailTxtFld.getText().contains("@") || !emailTxtFld.getText().contains(".com")){
            Main.getApp().showPopupWithOk("The email you entered is not valid");

            return false;
        }
         else if(!Main.getApp().getData().isNumeric(weightTxtFld.getText())) {
            Main.getApp().showPopupWithOk("Please Enter a valid weight");
             return false;
         }
         else if(!((Float.parseFloat(weightTxtFld.getText()))< 200)/*kg*/ ||
                !(Float.parseFloat(weightTxtFld.getText()) >=0)){
            Main.getApp().showPopupWithOk("Please enter a weight between 0 and 200 KGs");
            return false;
         }

         Main.getApp().showPopupWithOk("Sign up was successful!");
         return true;

    }


    /**********************************************************/
    @FXML private MenuButton specializationMenuButton;
    //"d" at the end stands 4 "doctor"
    @FXML private TextField firstnameTxtFldd, lastnameTxtFldd, emailTxtFldd,
            passwordTxtFldd, medLicenceTxtFld,weightTxtFldd;
    @FXML private DatePicker dateOfBirthd;
    @FXML private RadioButton maleRadioButtond, femaleRadioButtond;
    @FXML private Button doctorImageChooser;
    public void chooseDoctorImage() {
        Image image = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (!(selectedFile == null)) {
            file = new File(selectedFile.getPath());
            doctorImageChooser.setText(file.getPath());
        }
    }

    private ArrayList<MenuItem> specsMenuItemsList;
    public void addSpecializationToMB(){
        specializationMenuButton.getItems().clear();
        for(MenuItem menuItem: specsMenuItemsList){
            specializationMenuButton.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                specializationMenuButton.setText(menuItem.getText());
            });
        }
    }

    public void doctorSignupPressed(ActionEvent e) throws IOException {
        if(checkIfDoctorEnteredInfosAreValid(e)){
            Doctor.GENDER gender = null;
            if(maleRadioButtond.isSelected())
                gender = Doctor.GENDER.MALE;
            else if(femaleRadioButtond.isSelected())
                gender = Doctor.GENDER.FEMALE;


            Main.getApp().getData().createDoctorData(firstnameTxtFldd.getText().trim()
                    ,lastnameTxtFldd.getText().trim(),emailTxtFldd.getText().trim()
                    ,passwordTxtFldd.getText().trim(),dateOfBirthd.getValue().toString(),
                    gender.toString(),weightTxtFldd.getText().trim(),specializationMenuButton.getText(),
                    medLicenceTxtFld.getText().trim(), "null");

            int numberOfAllDoctors = Main.getApp().getData().getDoctorList().size();
            Main.getApp().getData().updateDoctorPhoto(Main.getApp().getData().
                    getDoctorList().get(numberOfAllDoctors-1),file);
            file = null;
            Main.getApp().getData().updateDoctorsFile();


            Main.getApp().getStage(e).close();
            Main.getApp().getSignupStage().close();
            Main.getApp().showLoginWindow();
        }
    }

    public boolean checkIfDoctorEnteredInfosAreValid(ActionEvent e) throws IOException {
        for(Patient patient: Main.getApp().getData().getPatientList()){
            if(emailTxtFldd.getText().trim().equals(patient.getEmail())) {
                Main.getApp().showPopupWithOk("This email has an account");
                return false;
            }
        }
        for(Doctor doctor: Main.getApp().getData().getDoctorList()){
            if(emailTxtFldd.getText().trim().equals(doctor.getEmail())){
                Main.getApp().showPopupWithOk("This email has an account");
                return false;
            }
        }

        if(firstnameTxtFldd.getText().trim().equals("")|| lastnameTxtFldd.getText().trim().equals("") ||
                emailTxtFldd.getText().trim().equals("") || passwordTxtFldd.getText().trim().equals("") ||
                weightTxtFldd.getText().trim().equals("") || dateOfBirthd.getValue() == null ||
                specializationMenuButton.getItems().equals("Specilization")|| medLicenceTxtFld.getText()
                .trim().equals("")|| file == null ||
                (!maleRadioButtond.isSelected() && !femaleRadioButtond.isSelected())){
            Main.getApp().showPopupWithOk("Make sure you filled all");
            return false;
        }
        else if(passwordTxtFldd.getText().length()<4) {
            Main.getApp().showPopupWithOk("Password should be equal or more than 5 characters!");

            return false;
        }
        else if(!emailTxtFldd.getText().contains("@") || !emailTxtFldd.getText().contains(".com")){
            Main.getApp().showPopupWithOk("The email you entered is not valid");

            return false;
        }
        else if(!Main.getApp().getData().isNumeric(weightTxtFldd.getText())) {
            Main.getApp().showPopupWithOk("Please Enter a valid weight");
            return false;
        }
        else if(!((Float.parseFloat(weightTxtFldd.getText()))< 200)/*kg*/ ||
                !(Float.parseFloat(weightTxtFldd.getText()) >=0)){
            Main.getApp().showPopupWithOk("Please enter a weight between 0 and 200 KGs");
        }
        else if(!Main.getApp().getData().isNumeric(medLicenceTxtFld.getText())) {
            Main.getApp().showPopupWithOk("Enter a number for your medical number!");
            return false;
        }

        Main.getApp().showPopupWithOk("Sign up was successful!");
        return true;
    }

    /***********************************************/
    public void goBack(ActionEvent e) throws IOException {
        Main.getApp().showPopupWithYesNo("You haven't saved your infos, Are you sure you want to go back?", e);
    }

}
