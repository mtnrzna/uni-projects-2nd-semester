package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class PatientWindow implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        patient = (Patient)Main.getApp().getUser();
        patientNameLabel.setText(patient.getFirstName() +" "+patient.getLastName());
        Main.getApp().getData().uploadPatientPhoto(patient);
        patientImageView.setFitHeight(130);
        patientImageView.setFitWidth(134);
        patientImageView.setImage(patient.getPhoto());

        Main.getApp().getData().readExamRequestFile();
        Main.getApp().getData().readQuestionFile();

        specsMenuItemsList = new ArrayList<MenuItem>();
        Doctor.Specialization specs[] = Doctor.Specialization.values();
        int number = Doctor.Specialization.values().length;
        for (int i = 0; i < number; i++) {
            MenuItem menuItem = new MenuItem(specs[i].toString());
            specsMenuItemsList.add(menuItem);
        }

        showHomePagePane();
    }

    private Patient patient;
    @FXML private Label patientNameLabel;
    @FXML private ImageView patientImageView;
    @FXML private Button logoutButton,
            homePageButton,editButton, requestExamButton, yourRequestsButton, messagePaneButton,
            docSearchButton;

    @FXML Pane homePagePane,editPane,examRequestPane, yourRequestsPane,messagePane,
            patientsQuestionsPane,detailPane,searchDocPane;
    //followings are components in edit pane:
    @FXML private TextField firstnameTxtFld, lastnameTxtFld, emailTxtFld, passwordTxtFld,weightTxtFld;
    @FXML private DatePicker dateOfBirth;
    @FXML private RadioButton maleRadioButton, femaleRadioButton;
    @FXML private Button saveEditChangesButton;




    @FXML private Pane sharedPostpane;
    public void showHomePagePane(){
        sharedPostpane.getChildren().clear();
        homePagePane.toFront();
        Main.getApp().getData().readPostFile();
        ArrayList<Post> postList = Main.getApp().getData().getPostList();
        for(int i= 0; i<postList.size();i++){
            showPublishedPost(postList.get(i));
        }

    }


    public void showPublishedPost(Post post){

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxHeight(Region.USE_PREF_SIZE);
        hBox.setMaxWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setPrefHeight(100);
        hBox.setPrefWidth(400);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);

        String postStr = post.getPostString();
        if(post.getPostString().length()>40){
            postStr = post.getPostString().substring(0 ,20)+"\n"
                    +post.getPostString().substring(20);
        }
        Label postContentLabel = new Label(postStr);
            postContentLabel.setPrefWidth(250);
            postContentLabel.setPrefHeight(100);
            postContentLabel.setMaxWidth(Region.USE_PREF_SIZE);
            postContentLabel.setMaxHeight(Region.USE_PREF_SIZE);
            postContentLabel.setMinWidth(Region.USE_PREF_SIZE);
            postContentLabel.setMinHeight(Region.USE_PREF_SIZE);
            Label doctorName = new Label("By: "+ post.getDoctor().getFirstName()+" "+
                    post.getDoctor().getLastName());

        hBox.getChildren().addAll(postContentLabel, doctorName);
        sharedPostpane.getChildren().add(hBox);

    }

/****************************************************/
    /************************************************/
    private File file;
    @FXML Button imageChooser;
    public void chooseImage(){
        file = null;
        Image image = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if(!(selectedFile == null)) {
            file = new File(selectedFile.getPath());
            imageChooser.setText(file.getPath());
        }
    }



    public void showEditPane(){
        firstnameTxtFld.setText(patient.getFirstName());
        lastnameTxtFld.setText(patient.getLastName());
        emailTxtFld.setText(patient.getEmail());
        passwordTxtFld.setText(patient.getPassword());
        passwordTxtFld.setText(patient.getPassword());
        if(patient.getGender() == Patient.GENDER.MALE)
            maleRadioButton.setSelected(true);
        if(patient.getGender() == Patient.GENDER.FEMALE)
            femaleRadioButton.setSelected(true);
        weightTxtFld.setText(String.valueOf(patient.getWeight()));
        editPane.toFront();
    }
    public void saveEditChanges(ActionEvent e) throws IOException {
        if(checkIfPatientEnteredInfosAreValid(e)){
            //if patient's name is changing, then i should create a new image with the patient's
            //new name! cause photos are name by their owners firstname and lastname
            if(!patient.getFirstName().equals(firstnameTxtFld) || !patient.getLastName().
                    equals(lastnameTxtFld)){
                File oldImageFile = new File("pics\\patients\\"+patient.getFirstName()+" " +
                        patient.getLastName()+".jpg");
                BufferedImage bufferedImage = null;
                try
                {
                    bufferedImage = new BufferedImage(500, 500,
                            BufferedImage.TYPE_INT_ARGB);

                    // Reading input file
                    bufferedImage = ImageIO.read(oldImageFile);
                    if(oldImageFile == null)
                        System.out.println("null");
                    else
                        System.out.println("not null");

                }
                catch(IOException e2)
                {
                    System.out.println("The old picture of the patient not found!!!");
                }

                //write the photo
                try
                {
                    File outputFile = new File("pics\\patients\\"+firstnameTxtFld.getText()
                            +" "+lastnameTxtFld.getText()+".jpg");

                    ImageIO.write(bufferedImage, "jpg", outputFile);

                    System.out.println("photo changed...");
                }
                catch(IOException e3)
                {
                    System.out.println("The patient's old photo not found:(");
                }
            }
            patient.setFirstName(firstnameTxtFld.getText());
            patient.setLastName(lastnameTxtFld.getText());
            patient.setEmail(emailTxtFld.getText());
            patient.setPassword(passwordTxtFld.getText());
            if(!(dateOfBirth.getValue() == null)){
            patient.setDateOfBirth(dateOfBirth.getValue());
            }
            if(maleRadioButton.isSelected())
                patient.setGender(Patient.GENDER.MALE);
            else if(femaleRadioButton.isSelected())
                patient.setGender(Patient.GENDER.FEMALE);
            patient.setWeight(Float.parseFloat(weightTxtFld.getText()));
            Main.getApp().getData().updatePatientsFile();

            if(!(file == null)){
                Main.getApp().getData().updatePatientPhoto(patient, file);
                Main.getApp().getData().uploadPatientPhoto(patient);
                patientImageView.setFitHeight(130);
                patientImageView.setFitWidth(134);
                patientImageView.setImage(patient.getPhoto());
            }
            imageChooser.setText("Click to upload your  photo");

            showHomePagePane();

        }
    }

    public boolean checkIfPatientEnteredInfosAreValid(ActionEvent e) throws IOException {
        if(firstnameTxtFld.getText().trim().equals("")|| lastnameTxtFld.getText().trim().equals("") ||
                emailTxtFld.getText().trim().equals("") || passwordTxtFld.getText().trim().equals("") ||
                weightTxtFld.getText().trim().equals("") ||
                (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){
            Main.getApp().showPopupWithOk("Make sure you filled all");
            return false;
        }
        else if(passwordTxtFld.getText().length()<4) {
            Main.getApp().showPopupWithOk("Password must be equal or bigger than 4");
            return false;
        }
        else if(!emailTxtFld.getText().contains("@") || !emailTxtFld.getText().contains(".com")){
            Main.getApp().showPopupWithOk("The email you entered is not valid");
            return false;
        }
        else if(!Main.getApp().getData().isNumeric(weightTxtFld.getText())){
            Main.getApp().showPopupWithOk("Enter a valid number for weight!");
            return false;
        }
        else if(!((Float.parseFloat(weightTxtFld.getText()))< 200)/*kg*/ ||
                !(Float.parseFloat(weightTxtFld.getText()) >=0)) {
            Main.getApp().showPopupWithOk("Please Enter a valid weight");
            return false;
        }
        else
            Main.getApp().showPopupWithOk("Changes have been saved");
        return true;

    }


    /************************************************/
    Doctor choseToSendExRe;
    @FXML MenuButton specsMenuButton;
    @FXML MenuButton doctorsMenuButton;
    private ArrayList<MenuItem> specsMenuItemsList;
    public void showExamRequestPane(){
        examRequestPane.toFront();
        specsMenuButton.getItems().clear();
        int number = specsMenuItemsList.size();
        for (int i = 0; i < number; i++) {
            MenuItem menuItem = specsMenuItemsList.get(i);
            specsMenuButton.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                doctorsMenuButton.getItems().clear();
                doctorsMenuButton.setText("Doctors available");
                specsMenuButton.setText(menuItem.getText());
                showDoctorsAvailable(Doctor.Specialization.valueOf(specsMenuButton.getText()));
            });

        }
    }
    public void showDoctorsAvailable(Doctor.Specialization specialization ){
        for(Doctor doctor: Main.getApp().getData().getDoctorList()){
            if(doctor.getSpecialization() == specialization){
                MenuItem menuItem = new MenuItem(doctor.getFirstName()+" "+doctor.getLastName());
                doctorsMenuButton.getItems().add(menuItem);
                menuItem.setOnAction(e ->{
                    doctorsMenuButton.setText(menuItem.getText());
                    choseToSendExRe = doctor;
                });
            }
        }
    }
    @FXML TextField subjectTextField;
    @FXML TextArea examRequestTextArea;
    public void pressedSendExamRequest() throws IOException {
        if (examRequestTextArea.getText().trim().equals("") ||subjectTextField.getText().trim().equals("")
            || choseToSendExRe == null ||doctorsMenuButton.getText().equals("Doctors available")) {
            Main.getApp().showPopupWithOk("Fill all parts");
        }
        else {
            Main.getApp().getData().createNewExamRequest((choseToSendExRe.getFirstName() + " "
                            + choseToSendExRe.getLastName()), (patient.getFirstName() + " " + patient.getLastName()),
                    examRequestTextArea.getText(),subjectTextField.getText(),"null", "false");
            Main.getApp().getData().updateExamRequestFile();
            choseToSendExRe = null;
            specsMenuButton.setText("Select Specialization");
            doctorsMenuButton.setText("Doctor's available");
            Main.getApp().showPopupWithOk("Request for an examination sent!");
            subjectTextField.setText("");
            examRequestTextArea.setText("");

        }

//        Main.getApp().getData().printData();
    }
/**********************************************************/
    @FXML private Pane answeredPane,notAnsweredPane;
    ArrayList<ExamRequest> thisPatientExReqtsList;

    public void readThisPatientsExamRequests(){
        Main.getApp().getData().readExamRequestFile();
        int numberOfExReq = Main.getApp().getData().getExamRequestList().size();
        for(int i= 0; i<numberOfExReq;i++){
            if(Main.getApp().getData().getExamRequestList().get(i).getPatient().getFirstName().
                equals(patient.getFirstName()) && Main.getApp().getData().getExamRequestList().get(i)
                    .getPatient().getLastName().equals(patient.getLastName())){
                thisPatientExReqtsList.add(Main.getApp().getData().getExamRequestList().get(i));
            }

        }
    }

    public void showYourRequestsPane(){
        answeredPane.getChildren().clear();
        notAnsweredPane.getChildren().clear();
        yourRequestsPane.toFront();
        thisPatientExReqtsList = new ArrayList<ExamRequest>();
        readThisPatientsExamRequests();
        for(int i= 0; i<thisPatientExReqtsList.size();i++){
            showExam(thisPatientExReqtsList.get(i));
        }


    }

    public void showExam(ExamRequest examRequest){

        if(examRequest.getDoctorOpinionString().equals("null")){

            VBox vBox = new VBox();
            vBox.setMaxHeight(Region.USE_PREF_SIZE);
            vBox.setMaxWidth(Region.USE_PREF_SIZE);
            vBox.setMinHeight(Region.USE_PREF_SIZE);
            vBox.setMinWidth(Region.USE_PREF_SIZE);
            vBox.setPrefHeight(160);
            vBox.setPrefWidth(250);
            vBox.setStyle("-fx-border-color: #006600;");
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setSpacing(10);


            Label subjectLabel = new Label(examRequest.getSubjectString());
            subjectLabel.setStyle("-fx-background-color: #E5FFCC;");


            String str = examRequest.getRequestString();
            if(str.length()>45){
                str =str.substring(0, 45)+"\n"+str.substring(45);
            }
            Label contentLabel = new Label(str);
            contentLabel.setAlignment(Pos.TOP_LEFT);
            contentLabel.setPrefHeight(95);
            contentLabel.setPrefWidth(292);
            contentLabel.setMaxWidth(Region.USE_PREF_SIZE);
            contentLabel.setMaxHeight(Region.USE_PREF_SIZE);
            contentLabel.setStyle("-fx-background-color: #E5FFCC;");


            vBox.getChildren().addAll(subjectLabel, contentLabel);
            notAnsweredPane.getChildren().add(vBox);
        }



        if(!examRequest.getDoctorOpinionString().equals("null")){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setMaxHeight(Region.USE_PREF_SIZE);
            hBox.setMaxWidth(Region.USE_PREF_SIZE);
            hBox.setMinHeight(Region.USE_PREF_SIZE);
            hBox.setMinWidth(Region.USE_PREF_SIZE);
            hBox.setPrefHeight(160);
            hBox.setPrefWidth(505);
            hBox.setStyle("-fx-border-color: #006600;");
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(5);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setMaxHeight(Region.USE_PREF_SIZE);
            vBox.setMaxWidth(Region.USE_PREF_SIZE);
            vBox.setMinHeight(Region.USE_PREF_SIZE);
            vBox.setMinWidth(Region.USE_PREF_SIZE);
            vBox.setPrefHeight(160);
            vBox.setPrefWidth(250);
            vBox.setStyle("-fx-border-color: #006600;");
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setSpacing(10);

            VBox vBox2 = new VBox();
            vBox2.setAlignment(Pos.CENTER);
            vBox2.setMaxHeight(Region.USE_PREF_SIZE);
            vBox2.setMaxWidth(Region.USE_PREF_SIZE);
            vBox2.setMinHeight(Region.USE_PREF_SIZE);
            vBox2.setMinWidth(Region.USE_PREF_SIZE);
            vBox2.setPrefHeight(160);
            vBox2.setPrefWidth(250);
            vBox2.setStyle("-fx-border-color: #006600;");
            vBox2.setAlignment(Pos.CENTER_LEFT);
            vBox2.setSpacing(10);


            Label yourExReLabel = new Label("Your examination request: ");
            Label doctorsRespondLabel = new Label("Doctor's respond: ");

            Label subjectLabel = new Label(examRequest.getSubjectString());
            subjectLabel.setStyle("-fx-background-color: #E5FFCC;");
            Label blankLabel = new Label();

            String str = examRequest.getRequestString();
            if(str.length()>50){
                str =str.substring(0, 50)+"\n"+str.substring(50);
            }
            Label contentLabel = new Label(str);
            contentLabel.setAlignment(Pos.TOP_LEFT);
            contentLabel.setPrefHeight(95);
            contentLabel.setPrefWidth(292);
            contentLabel.setMaxWidth(Region.USE_PREF_SIZE);
            contentLabel.setMaxHeight(Region.USE_PREF_SIZE);
            contentLabel.setStyle("-fx-background-color: #E5FFCC;");

            Label docRespondLTextabel = new Label(examRequest.getDoctorOpinionString());
            docRespondLTextabel.setAlignment(Pos.TOP_LEFT);
            docRespondLTextabel.setPrefHeight(95);
            docRespondLTextabel.setPrefWidth(292);
            docRespondLTextabel.setMaxWidth(Region.USE_PREF_SIZE);
            docRespondLTextabel.setMaxHeight(Region.USE_PREF_SIZE);
            docRespondLTextabel.setStyle("-fx-background-color: #E5FFCC;");


            vBox.getChildren().addAll(yourExReLabel, subjectLabel, contentLabel);
            vBox2.getChildren().addAll(doctorsRespondLabel,blankLabel, docRespondLTextabel);
            hBox.getChildren().addAll(vBox,vBox2);
            answeredPane.getChildren().add(hBox);


        }
    }


    /***************************************************/
    //In following block of code, M stands for Message, cause of message pane
    Doctor choseToSendMessage;
    @FXML MenuButton specsMenuButtonM;
    @FXML MenuButton doctorsMenuButtonM;
    public void showMessagePane(){
        messagePane.toFront();
        specsMenuButtonM.getItems().clear();
        int number = specsMenuItemsList.size();
        for (int i = 0; i < number; i++) {
            MenuItem menuItem = specsMenuItemsList.get(i);
            specsMenuButtonM.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                doctorsMenuButtonM.getItems().clear();
                doctorsMenuButtonM.setText("Doctors available");
                specsMenuButtonM.setText(menuItem.getText());
                showDoctorsAvailableM(Doctor.Specialization.valueOf(specsMenuButtonM.getText()));
            });

        }

    }
    public void showDoctorsAvailableM(Doctor.Specialization specialization ){
        for(Doctor doctor: Main.getApp().getData().getDoctorList()){
            if(doctor.getSpecialization() == specialization){
                MenuItem menuItem = new MenuItem(doctor.getFirstName()+" "+doctor.getLastName());
                doctorsMenuButtonM.getItems().add(menuItem);
                menuItem.setOnAction(e ->{
                    doctorsMenuButtonM.setText(menuItem.getText());
                    choseToSendMessage = doctor;
                });
            }
        }
    }

    @FXML TextArea messageTextArea;
    public void pressedSendMessage() throws IOException {
        if (messageTextArea.getText().trim().equals("")||choseToSendMessage == null||
                doctorsMenuButtonM.getText().equals("Doctors name")) {
            Main.getApp().showPopupWithOk("Fill all parts");
        } else {
            Main.getApp().getData().createNewQuestion((choseToSendMessage.getFirstName() + " "
                            + choseToSendMessage.getLastName()), (patient.getFirstName() + " " + patient.getLastName()),
                    messageTextArea.getText(), "null");
            Main.getApp().getData().updateQuestionFile();
            choseToSendMessage = null;
            specsMenuButton.setText("Select specialization");
            doctorsMenuButton.setText("Doctor's name");
            Main.getApp().showPopupWithOk("Message sent!");
            messageTextArea.setText("");
//            Main.getApp().getData().printData();

        }
    }


    /***************************************************/
    @FXML private VBox questionsPane;
    ArrayList<Question> thisPatientsQuestionsList;

    public void readThisPatientsQuestions(){
        thisPatientsQuestionsList = new ArrayList<Question>();
        Main.getApp().getData().readQuestionFile();
        int numberOfAllQuestions = Main.getApp().getData().getQuestionList().size();
        for(int i= 0; i<numberOfAllQuestions;i++){
            if(Main.getApp().getData().getQuestionList().get(i).getPatient().getFirstName().
                    equals(patient.getFirstName()) && Main.getApp().getData().getQuestionList().
                    get(i).getPatient().getLastName().equals(patient.getLastName())){
                thisPatientsQuestionsList.add(Main.getApp().getData().getQuestionList().get(i));
            }

        }
    }

    public void showPatientsQuestionsPane() throws IOException {
        questionsPane.getChildren().clear();
        questionsPane.setSpacing(10);
        patientsQuestionsPane.toFront();
        readThisPatientsQuestions();

        for(int i= 0; i<thisPatientsQuestionsList.size();i++){
                showPatientsQuestions(thisPatientsQuestionsList.get(i));
        }

    }


    public void showPatientsQuestions(Question question) throws IOException {

        HBox hBox = new HBox();
        hBox.setMaxHeight(Region.USE_PREF_SIZE);
        hBox.setMaxWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setPrefHeight(163);
        hBox.setPrefWidth(463);
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.setSpacing(10);



        String str = question.getQuestion();
        if(str.length()>45){
            str =str.substring(0, 45)+"\n"+str.substring(45);
        }
        Label questionLabel = new Label(str);
        questionLabel.setStyle("-fx-background-color: #FFFFFF;");
        questionLabel.setAlignment(Pos.TOP_LEFT);
        questionLabel.setPrefWidth(220);
        questionLabel.setPrefHeight(130);
        questionLabel.setMaxWidth(Region.USE_PREF_SIZE);
        questionLabel.setMaxHeight(Region.USE_PREF_SIZE);
        questionLabel.setMinWidth(Region.USE_PREF_SIZE);
        questionLabel.setMinHeight(Region.USE_PREF_SIZE);




        Label answerLabel = new Label();
        if(question.getAnswer().equals("null")) {
            answerLabel.setText("Doctor hasn't answered yet!!");
            answerLabel.setStyle("-fx-background-color: #777777;");
        }
        else{
            answerLabel.setText(question.getAnswer());
            answerLabel.setStyle("-fx-background-color: #FFFFFF;");
        }
        answerLabel.setAlignment(Pos.TOP_LEFT);
        answerLabel.setPrefWidth(220);
        answerLabel.setPrefHeight(130);
        answerLabel.setMaxWidth(Region.USE_PREF_SIZE);
        answerLabel.setMaxHeight(Region.USE_PREF_SIZE);
        answerLabel.setMinWidth(Region.USE_PREF_SIZE);
        answerLabel.setMinHeight(Region.USE_PREF_SIZE);




        hBox.getChildren().addAll(questionLabel, answerLabel);
        questionsPane.getChildren().add(hBox);

    }



/***************************************************************/
    @FXML MenuButton specsMenuButtonSearch;
    @FXML TextField nameSearchTxtFld;
    @FXML VBox doctorsBySpecsPane, doctorsByNamePane;
    ArrayList<Doctor> arrayOfDocs;
    public void showSearchDocPane () {
        doctorsBySpecsPane.getChildren().clear();
        doctorsByNamePane.getChildren().clear();
        arrayOfDocs = new ArrayList<Doctor>();
        searchDocPane.toFront();
        specsMenuButtonSearch.getItems().clear();
        for(MenuItem menuItem: specsMenuItemsList){
            specsMenuButtonSearch.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                specsMenuButtonSearch.setText(menuItem.getText());
            });
        }
    }

    public void searchBySpecs() throws IOException {
        doctorsBySpecsPane.getChildren().clear();
        arrayOfDocs = new ArrayList<Doctor>();
        if(!specsMenuButtonSearch.getText().equals("Select Specialization")) {
            Doctor.Specialization specialization = Doctor.Specialization.valueOf
                    (specsMenuButtonSearch.getText());
            for (Doctor doctor : Main.getApp().getData().getDoctorList()) {
                if (doctor.getSpecialization() == specialization) {
                    Button button = new Button(doctor.getFirstName() + " " + doctor.getLastName());
                    button.setPrefWidth(200);
                    button.setPrefHeight(40);
                    button.setStyle("-fx-background-color: #99FFFF");
                    doctorsBySpecsPane.getChildren().add(button);
                    button.setOnAction(e -> {
                        showDocDetailPane(doctor);
                    });
                    arrayOfDocs.add(doctor);
                }
            }
            if(arrayOfDocs.size()== 0){
                Button button = new Button("No results!!");
                doctorsBySpecsPane.getChildren().add(button);
            }
        }
        else {
            Main.getApp().showPopupWithOk("First select a specialization");
        }
    }


    public void searchByName() throws IOException {
        doctorsByNamePane.getChildren().clear();
        arrayOfDocs = new ArrayList<Doctor>();
        String input = nameSearchTxtFld.getText().trim();
        if(!input.equals("")) {
            for (Doctor doctor : Main.getApp().getData().getDoctorList()) {
                if (doctor.getFirstName().toLowerCase().equals(input.toLowerCase()) ||
                        doctor.getLastName().toLowerCase().equals(input.toLowerCase()) ||
                        input.toLowerCase().equals(doctor.getFirstName().toLowerCase()+" "+
                                doctor.getFirstName().toLowerCase())) {
                    Button button = new Button(doctor.getFirstName() + " " + doctor.getLastName());
                    button.setPrefWidth(200);
                    button.setPrefHeight(40);
                    button.setStyle("-fx-background-color: #99FFFF");
                    doctorsByNamePane.getChildren().add(button);
                    button.setOnAction(e -> {
                        showDocDetailPane(doctor);
                    });
                    arrayOfDocs.add(doctor);
                }
            }
            if(arrayOfDocs.size()== 0){
                Button button = new Button("No results!!");
                doctorsByNamePane.getChildren().add(button);
            }
        }
        else {
            Main.getApp().showPopupWithOk("First type something!!");
        }
    }


    @FXML ImageView doctorsImage;
    @FXML Label doctorsNameLabel, doctorsGenderLabel,doctorsAgeLabel, doctorsSpecLabel;
    public void showDocDetailPane(Doctor doctor){
        doctorsNameLabel.setText(doctor.getFirstName()+" "+doctor.getLastName());
        doctorsGenderLabel.setText(doctor.getGender().toString().toLowerCase());
        //calculating patient's age
        LocalDate today = LocalDate.now();
        LocalDate doctorsBD = doctor.getDateOfBirth();
        int  age;
        if(today.getMonthValue() ==doctorsBD.getMonthValue() ){
            if(today.getDayOfMonth() >= doctorsBD.getDayOfMonth()){
                age = today.getYear() - doctorsBD.getYear();
            }
            else{
                age = today.getYear() - doctorsBD.getYear()-1;
            }
        }
        else if(today.getMonthValue()>doctorsBD.getMonthValue()){
            age = today.getYear() - doctorsBD.getYear();
        }
        else{
            age = today.getYear() - doctorsBD.getYear()-1;
        }
        doctorsAgeLabel.setText("Doctor's age: "+Integer.toString(age));
        doctorsSpecLabel.setText("Doctor's Specialization: "+ doctor.getSpecialization().
                toString().toLowerCase());

        Main.getApp().getData().uploadDoctorPhoto(doctor);
        doctorsImage.setFitHeight(150);
        doctorsImage.setFitWidth(200);
        doctorsImage.setImage(doctor.getPhoto());

        detailPane.toFront();
    }



    public void gobackFromDetail(){
        specsMenuButtonSearch.setText("Select Specialization");
        nameSearchTxtFld.setText("");
        showSearchDocPane();
    }

/*****************************************************************/
    public void logoutButtonPressed (ActionEvent e) throws IOException {
        Main.getApp().showPopupWithYesNo("Are you sure you want to log out?", e);
        Main.getApp().setUser(null);
        Main.getApp().getData().setPatientList(null);
        Main.getApp().getData().setDoctorList(null);
        Main.getApp().getData().setExamRequestList(null);
        Main.getApp().getData().setQuestionList(null);
        Main.getApp().getData().setPostList(null);
    }

}
