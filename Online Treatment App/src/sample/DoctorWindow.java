package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class DoctorWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doctor= (Doctor) Main.getApp().getUser();
        Main.getApp().getData().uploadDoctorPhoto(doctor);
        doctorImageView.setFitHeight(130);
        doctorImageView.setFitWidth(134);
        doctorImageView.setImage(doctor.getPhoto());

        homePagePane.toFront();
        doctorNameLabel.setText(doctor.getFirstName() +" "+doctor.getLastName());
    }

    @FXML private ImageView doctorImageView;
    @FXML private Label doctorNameLabel;
    @FXML private Button logoutButton,
            homePageButton,examRequestButton,messagesButton,postButton;
    @FXML private Pane homePagePane,bioPane,examRequestPane,messagesPane,postPane, detailsPane;
    public Label getDoctorNameLabel() {
        return doctorNameLabel;
    }

    private Doctor doctor;

    public void showHomePagePane(){
        homePagePane.toFront();
    }




/**********************************************************/

    @FXML private Pane readRequestPane, unreadRequestPane;
    public void showExamRequestsPane(){
        readRequestPane.getChildren().clear();
        unreadRequestPane.getChildren().clear();
        examRequestPane.toFront();
        Main.getApp().getData().readExamRequestFile();
        System.out.println("Number of All requests imported: "+Main.getApp().getData().getExamRequestList().size());
        int numberOfExReq = Main.getApp().getData().getExamRequestList().size();
        for(int i= 0; i<numberOfExReq;i++){
            if(Main.getApp().getData().getExamRequestList().get(i).getDoctor() == doctor){
                showExam(Main.getApp().getData().getExamRequestList().get(i));
            }

        }

    }

    public void showExam(ExamRequest examRequest){
        HBox examHBox = new HBox();
        Label examLabel = new Label(examRequest.getSubjectString());
        examHBox.setStyle("-fx-background-color: #BDF3FC;");
//        examLabel.setStyle("-fx-border-color:#202020");
        examHBox.setSpacing(10);
        examHBox.setPrefHeight(40);
        examHBox.setPrefWidth(504);
        examHBox.setAlignment(Pos.TOP_RIGHT);
        examLabel.setPrefHeight(40);
        examLabel.setPrefWidth(350);
        examLabel.setAlignment(Pos.CENTER);
        Button seeDetailsButton = new Button("Patient's Detail");
        examHBox.setMargin(seeDetailsButton, new Insets(8, 0, 0, 0));
        seeDetailsButton.setPrefHeight(USE_COMPUTED_SIZE);
        seeDetailsButton.setPrefWidth(USE_COMPUTED_SIZE);
        examHBox.getChildren().addAll(examLabel, seeDetailsButton);
        if(examRequest.isSeen()) {
            readRequestPane.getChildren().add(examHBox);
            seeDetailsButton.setOnAction(e -> {
                showDetailsPane(examRequest);
            });
        }
        else if(!examRequest.isSeen()) {
            unreadRequestPane.getChildren().add(examHBox);
            seeDetailsButton.setOnAction(e -> {
                showDetailsPane(examRequest);
            });
        }
    }

    @FXML Label patientsName,patientsGender, patientsAge, patientsWeight;
    @FXML ImageView patientsImage;
    public void showDetailsPane(ExamRequest examRequest){
        if(!examRequest.isSeen())
            examRequest.setSeen(true);
            Main.getApp().getData().updateExamRequestFile();
        Patient patient = examRequest.getPatient();
        patientsName.setText(patient.getFirstName()+" "+patient.getLastName());
        patientsGender.setText(patient.getGender().toString().toLowerCase());
        //calculating patient's age
        LocalDate today = LocalDate.now();
        LocalDate patiensBD = patient.getDateOfBirth();
        int  age;
        if(today.getMonthValue() ==patiensBD.getMonthValue() ){
            if(today.getDayOfMonth() >= patiensBD.getDayOfMonth()){
                age = today.getYear() - patiensBD.getYear();
            }
            else{
                age = today.getYear() - patiensBD.getYear()-1;
            }
        }
        else if(today.getMonthValue()>patiensBD.getMonthValue()){
            age = today.getYear() - patiensBD.getYear();
        }
        else{
            age = today.getYear() - patiensBD.getYear()-1;
        }
        patientsAge.setText(Integer.toString(age));
        patientsWeight.setText(Float.toString(patient.getWeight()));
        Main.getApp().getData().uploadPatientPhoto(patient);
        patientsImage.setFitHeight(150);
        patientsImage.setFitWidth(200);
        patientsImage.setImage(patient.getPhoto());
        detailsPane.toFront();
    }


    public void gobackFromDetail(){
        showExamRequestsPane();
    }



    /***************************************************/
    @FXML private VBox questionsPane;
    ArrayList<Question> thisDoctorsQuestionsList;

    public void readThisDoctorsQuestions(){
        thisDoctorsQuestionsList = new ArrayList<Question>();
        Main.getApp().getData().readQuestionFile();
        int numberOfAllQuestions = Main.getApp().getData().getQuestionList().size();
        for(int i= 0; i<numberOfAllQuestions;i++){
            if(Main.getApp().getData().getQuestionList().get(i).getDoctor().getFirstName().
                    equals(doctor.getFirstName()) && Main.getApp().getData().getQuestionList().
                    get(i).getDoctor().getLastName().equals(doctor.getLastName())){
                thisDoctorsQuestionsList.add(Main.getApp().getData().getQuestionList().get(i));
            }

        }
    }

    ArrayList<HBox> arrayListOfNotAnsweredQuestions;
    public void showMessagesPane() throws IOException {
        questionsPane.getChildren().clear();
        questionsPane.setSpacing(10);
        messagesPane.toFront();
        readThisDoctorsQuestions();

        for(int i= 0; i<thisDoctorsQuestionsList.size();i++){
            if(thisDoctorsQuestionsList.get(i).getAnswer().equals("null"))
                showNotAnsweredQuestions(thisDoctorsQuestionsList.get(i));
        }

    }


    public void showNotAnsweredQuestions(Question question) throws IOException {

        HBox hBox = new HBox();
        hBox.setMaxHeight(Region.USE_PREF_SIZE);
        hBox.setMaxWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setPrefHeight(163);
        hBox.setPrefWidth(463);
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.setSpacing(10);


        Label questionLabel = new Label(question.getQuestion());
        questionLabel.setStyle("-fx-background-color: #FFFFFF;");
        questionLabel.setAlignment(Pos.TOP_LEFT);
        questionLabel.setPrefWidth(220);
        questionLabel.setPrefHeight(130);
        questionLabel.setMaxWidth(Region.USE_PREF_SIZE);
        questionLabel.setMaxHeight(Region.USE_PREF_SIZE);
        questionLabel.setMinWidth(Region.USE_PREF_SIZE);
        questionLabel.setMinHeight(Region.USE_PREF_SIZE);

        VBox vBox = new VBox();
        vBox.setPrefHeight(100);
        vBox.setPrefWidth(229);
        vBox.setAlignment(Pos.CENTER);


        TextArea answerTextArea = new TextArea();
        answerTextArea.setPromptText("Type your answer here...");
        answerTextArea.setPrefWidth(220);
        answerTextArea.setPrefHeight(130);
        answerTextArea.setMaxWidth(Region.USE_PREF_SIZE);
        answerTextArea.setMaxHeight(Region.USE_PREF_SIZE);
        answerTextArea.setMinWidth(Region.USE_PREF_SIZE);
        answerTextArea.setMinHeight(Region.USE_PREF_SIZE);


        Button sendButton = new Button("send");
        sendButton.setStyle("-fx-background-color:  #33FF99;");

        vBox.getChildren().addAll(answerTextArea, sendButton);
        VBox.setMargin(sendButton, new Insets(5, 0, 0, 0));

        hBox.getChildren().addAll(questionLabel, vBox);
        questionsPane.getChildren().add(hBox);



        sendButton.setOnAction(e -> {
            try {
                if(!answerTextArea.getText().trim().equals("")) {
                    sendButtonClicked(question, answerTextArea.getText());
                }
                else if(answerTextArea.getText().trim().equals("")){
                    Main.getApp().showPopupWithOk("Please type something!!!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            });
    }

    public void sendButtonClicked(Question question, String answer) throws IOException {
        question.setAnswer(answer);
        Main.getApp().getData().updateQuestionFile();
        showMessagesPane();
    }



    /***************************************************/
    @FXML Label doctorNameLabel2,genderLabel,doctorAgeLabel,specializationLabel,doctorWeightLabel,
            medLicLabel;
    @FXML TextArea bioTextArea;
    @FXML ImageView doctorImage;
    public void showBioPane(){
        bioPane.toFront();
        doctorNameLabel2.setText(doctor.getFirstName() + " " + doctor.getLastName());
        genderLabel.setText(doctor.getGender().toString().toLowerCase());
        LocalDate today = LocalDate.now();
        LocalDate doctorBD = doctor.getDateOfBirth();
        int  age;
        if(today.getMonthValue() ==doctorBD.getMonthValue() ){
            if(today.getDayOfMonth() >= doctorBD.getDayOfMonth()){
                age = today.getYear() - doctorBD.getYear();
            }
            else{
                age = today.getYear() - doctorBD.getYear()-1;
            }
        }
        else if(today.getMonthValue()>doctorBD.getMonthValue()){
            age = today.getYear() - doctorBD.getYear();
        }
        else{
            age = today.getYear() - doctorBD.getYear()-1;
        }
        doctorAgeLabel.setText("Age: "+Float.toString(age));
        specializationLabel.setText("Specialization: "+doctor.getSpecialization().toString().toLowerCase());
        doctorWeightLabel.setText("Weight: "+Float.toString(doctor.getWeight()));
        medLicLabel.setText("Medical licence number: "+Integer.toString(doctor.getMedicalLicenceNom()));
        if(!doctor.getBio().equals("null"))
            bioTextArea.setText(doctor.getBio());

        Main.getApp().getData().uploadDoctorPhoto(doctor);
        doctorImage.setFitHeight(150);
        doctorImage.setFitWidth(200);
        doctorImage.setImage(doctor.getPhoto());
    }

    public void saveBio() throws IOException {
        doctor.setBio(bioTextArea.getText());
        Main.getApp().getData().updateDoctorsFile();
        Main.getApp().showPopupWithOk("Changes saved...");
        showHomePagePane();
    }

    /**************************************************/
    @FXML TextArea newPostTextArea;
    @FXML Pane sharePostpane;
    ArrayList<Post> thisDoctorPostsList = new ArrayList<Post>();

    public void readThisDoctorPostsList(){
        Main.getApp().getData().readPostFile();
        int numberOfAllPosts = Main.getApp().getData().getPostList().size();
        for(int i= 0; i<numberOfAllPosts;i++){
            if(Main.getApp().getData().getPostList().get(i).getDoctor().getFirstName().
                    equals(doctor.getFirstName()) && Main.getApp().getData().getPostList().get(i)
                    .getDoctor().getLastName().equals(doctor.getLastName())){
                thisDoctorPostsList.add(Main.getApp().getData().getPostList().get(i));
            }

        }
    }
    public void showPostPane(){
        thisDoctorPostsList.clear();
        sharePostpane.getChildren().clear();
        postPane.toFront();
        readThisDoctorPostsList();
        for(int i= 0; i<thisDoctorPostsList.size();i++){
            showPublishedPost(thisDoctorPostsList.get(i));
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


            Label postContentLabel = new Label(post.getPostString());
//            postContentLabel.setPrefWidth(250);
//            postContentLabel.setPrefHeight(100);
//            postContentLabel.setMaxWidth(Region.USE_PREF_SIZE);
//            postContentLabel.setMaxHeight(Region.USE_PREF_SIZE);
//            postContentLabel.setMinWidth(Region.USE_PREF_SIZE);
//            postContentLabel.setMinHeight(Region.USE_PREF_SIZE);
//            Label doctorName = new Label("By: "+ post.getDoctor().getFirstName()+" "+
//                    post.getDoctor().getLastName());

            hBox.getChildren().addAll(postContentLabel);
            sharePostpane.getChildren().add(hBox);

    }

    public void pressedShare() throws IOException {
        if (!newPostTextArea.getText().trim().equals("")) {
            Main.getApp().getData().createNewPost((doctor.getFirstName() + " " + doctor.getLastName())
                    , newPostTextArea.getText());
            Main.getApp().getData().updatePostFile();
            newPostTextArea.setText("");
            newPostTextArea.setPromptText("Type in a new post....");
            Main.getApp().showPopupWithOk("Post shared!");
            showPostPane();
        }
        else{
            Main.getApp().showPopupWithOk("Please type something then click share.");
        }
    }
    /****************************************************/



    public void logoutButtonPressed (ActionEvent e) throws IOException {
        Main.getApp().showPopupWithYesNo("Are you sure you want to log out?", e);
        Main.getApp().setUser(null);
        Main.getApp().getData().setPatientList(null);
        Main.getApp().getData().setDoctorList(null);
        Main.getApp().getData().setExamRequestList(null);
        Main.getApp().getData().setQuestionList(null);
    }
}
