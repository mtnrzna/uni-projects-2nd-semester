package sample;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    public Data() {
//        this.patientsData = new ArrayList<String>();
//        this.doctorsData = new ArrayList<String>();

        doctorList = new ArrayList<Doctor>();
        patientList = new ArrayList<Patient>();
        examRequestList = new ArrayList<ExamRequest>();
        questionList = new ArrayList<Question>();
        postList = new ArrayList<Post>();
    }


    private ArrayList<Patient> patientList;
    private ArrayList<Doctor> doctorList;
    private ArrayList<ExamRequest> examRequestList ;
    private ArrayList<Question> questionList ;
    private ArrayList<Post> postList;


    public ArrayList<Patient> getPatientList() {
        return patientList;
    }
    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }
    public ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }
    public void setDoctorList(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
    public ArrayList<ExamRequest> getExamRequestList() {
        return examRequestList;
    }
    public void setExamRequestList(ArrayList<ExamRequest> examRequestList) {
        this.examRequestList = examRequestList;
    }
    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
    public ArrayList<Post> getPostList() {
        return postList;
    }
    public void setPostList(ArrayList<Post> postList) {
        this.postList = postList;
    }

    /************************Handling Patient Data*************************/

    public void readPatientFile(){
        patientList = new ArrayList<Patient>();
        try {
            File patientsFile = new File("patient data.txt");
            Scanner scanner = new Scanner(patientsFile);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();

                String arrayOfData[] = new String[7];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    i++;
                }
                createPatientData(arrayOfData[0], arrayOfData[1], arrayOfData[2],
                        arrayOfData[3],arrayOfData[4], arrayOfData[5], arrayOfData[6]);
            }
            System.out.println("number of patients imported: "+patientList.size());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("the file for patients' data is missing!");
        }

    }


    public void createPatientData(String firstName, String lastName, String email, String passWord,
            String dateOfBirth, String gender, String weight) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPassword(passWord);
        patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
        patient.setGender(Patient.GENDER.valueOf(gender.toUpperCase()));
        patient.setWeight(Float.parseFloat(weight));

        patientList.add(patient);

    }


    public void updatePatientsFile(){
        try {
            File patientsFile = new File("patient data.txt");
            FileWriter fileWriter = new FileWriter(patientsFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i= 0; i<patientList.size(); i++) {
                Patient patient = patientList.get(i);
                printWriter.println(patient.getFirstName() + "$" + patient.getLastName() + "$"
                        + patient.getEmail() + "$" + patient.getPassword() + "$"
                        + patient.getDateOfBirth() + "$" + patient.getGender() + "$"
                        + patient.getWeight() + "$");
            }
            printWriter.close();
            System.out.println("patient file updated");
        }
        catch (IOException e){
            System.out.println("patients' file is missing!");
        }

    }

    /************************Handling Patient Data*************************/

    public void readDoctorFile(){
        doctorList = new ArrayList<Doctor>();
        try {
            File doctorsFile = new File("doctor data.txt");
            Scanner scanner = new Scanner(doctorsFile);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();

                String arrayOfData[] = new String[10];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    i++;
//                }
                }
                createDoctorData(arrayOfData[0],arrayOfData[1],arrayOfData[2],arrayOfData[3],
                    arrayOfData[4],arrayOfData[5],arrayOfData[6],arrayOfData[7],arrayOfData[8],
                    arrayOfData[9]);
            }
            System.out.println("number of doctors imported: "+doctorList.size());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("the file for doctors' data is missing!");
        }

    }


    public void createDoctorData(String firstName, String lastName, String email, String passWord,
                                 String dateOfBirth, String gender, String weight, String specialization,
                                 String medLicenceNom, String bio){
        Doctor doctor = new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setEmail(email);
        doctor.setPassword(passWord);
        doctor.setDateOfBirth(LocalDate.parse(dateOfBirth));
        doctor.setGender(Doctor.GENDER.valueOf(gender.toUpperCase()));
        doctor.setWeight(Float.parseFloat(weight));
        doctor.setSpecialization(Doctor.Specialization.valueOf(specialization.toUpperCase()));
        doctor.setMedicalLicenceNom(Integer.parseInt(medLicenceNom));
        doctor.setBio(bio);

        doctorList.add(doctor);


    }



    public void updateDoctorsFile(){
        try {
            File patientsFile = new File("doctor data.txt");
            FileWriter fileWriter = new FileWriter(patientsFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i= 0; i<doctorList.size(); i++) {
                Doctor doctor = doctorList.get(i);
                printWriter.println(doctor.getFirstName() + "$" + doctor.getLastName() + "$"
                        + doctor.getEmail() + "$" + doctor.getPassword() + "$"
                        + doctor.getDateOfBirth() + "$" + doctor.getGender() + "$"
                        + doctor.getWeight() + "$"+ doctor.getSpecialization()+"$"
                        +doctor.getMedicalLicenceNom()+"$"+doctor.getBio()+"$");
            }
            printWriter.close();
            System.out.println("doctor file updated");
        }
        catch (IOException e){
            System.out.println("doctors' file is missing!");
        }

    }


    /************************Handling Exam Requests Data*************************/

    public void readExamRequestFile(){
        examRequestList = new ArrayList<ExamRequest>();
        try {
            File examRequestsFile = new File("examrequests.txt");
            Scanner scanner = new Scanner(examRequestsFile);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();


                String arrayOfData[] = new String[6];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    i++;
                }

                createNewExamRequest(arrayOfData[0] , arrayOfData[1], arrayOfData[2], arrayOfData[3]
                        , arrayOfData[4], arrayOfData[5]);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("the file for exam requests' is missing!");
        }

    }


    public void createNewExamRequest(String doctorName, String patientName , String requestString,
                                     String subject,String doctorsOpinionString, String seen) {
        Doctor doctor = null;
        Patient patient = null;
        boolean seenBoolean = false;
        String doctorFirstName = doctorName.substring(0, doctorName.indexOf(" ") + 1);
        String doctorLastName = doctorName.replace(doctorFirstName, "");
        doctorFirstName = doctorFirstName.trim();
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorFirstName.equals(doctorList.get(i).getFirstName()) &&
                    doctorLastName.equals(doctorList.get(i).getLastName())) {
                doctor = doctorList.get(i);
                break;
            }
        }

        String patientFirstName = patientName.substring(0, patientName.indexOf(" ") + 1);
        String patientLastName = patientName.replace(patientFirstName, "");
        patientFirstName = patientFirstName.trim();
        for (int i = 0; i < patientList.size(); i++) {
            if (patientFirstName.equals(patientList.get(i).getFirstName()) &&
                    patientLastName.equals(patientList.get(i).getLastName())) {
                patient = patientList.get(i);
                break;
            }
        }
        if (seen.toLowerCase().equals("true"))
            seenBoolean = true;
        else if (seen.toLowerCase().equals("false"))
            seenBoolean = false;

        if (doctor != null && patient != null) {
            ExamRequest examRequest = new ExamRequest(doctor, patient, requestString,subject,doctorsOpinionString, seenBoolean);
            examRequestList.add(examRequest);
        }
    }


        public void updateExamRequestFile(){
            try {
                File patientsFile = new File("examrequests.txt");
                FileWriter fileWriter = new FileWriter(patientsFile, false);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for(ExamRequest examRequest: examRequestList) {
                    printWriter.println(examRequest.getDoctor().getFirstName() + " " +
                            examRequest.getDoctor().getLastName() + "$" + examRequest.getPatient().
                            getFirstName() +" " + examRequest.getPatient().getLastName() + "$" +
                            examRequest.getRequestString()+"$"+examRequest.getSubjectString()+"$" +
                            examRequest.getDoctorOpinionString()+"$"+
                            examRequest.isSeen()+"$");
                }
                printWriter.close();
                System.out.println("Examination file has been updated...");
            }
            catch (IOException e){
                System.out.println("Examination file is missing!");
            }


    }

    /************************Handling Messages Data*************************/

    public void readQuestionFile(){
        questionList = new ArrayList<Question>();
        try {
            File examRequestsFile = new File("questions.txt");
            Scanner scanner = new Scanner(examRequestsFile);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();


                String arrayOfData[] = new String[4];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
//                System.out.println(arrayOfData[i]);
                    s = s.replace(temp, "");
                    i++;
                }

                createNewQuestion(arrayOfData[0] , arrayOfData[1], arrayOfData[2], arrayOfData[3]);
//                System.out.println("Line imported is: "+line);
//                parseExamRequestData(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("the file for exam requests' is missing!");
        }

    }


    public void createNewQuestion(String doctorName, String patientName , String questionString, String answer) {
        Doctor doctor = null;
        Patient patient = null;
        String doctorFirstName = doctorName.substring(0, doctorName.indexOf(" ") + 1);
        String doctorLastName = doctorName.replace(doctorFirstName, "");
        doctorFirstName = doctorFirstName.trim();
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorFirstName.equals(doctorList.get(i).getFirstName()) &&
                    doctorLastName.equals(doctorList.get(i).getLastName())) {
                doctor = doctorList.get(i);
                break;
            }
        }

        String patientFirstName = patientName.substring(0, patientName.indexOf(" ") + 1);
        String patientLastName = patientName.replace(patientFirstName, "");
        patientFirstName = patientFirstName.trim();
        for (int i = 0; i < patientList.size(); i++) {
            if (patientFirstName.equals(patientList.get(i).getFirstName()) &&
                    patientLastName.equals(patientList.get(i).getLastName())) {
                patient = patientList.get(i);
                break;
            }
        }


        if (doctor != null && patient != null) {
            Question question = new Question(doctor, patient,questionString , answer);
            questionList.add(question);
        }
    }


    public void updateQuestionFile(){
        try {
            File patientsFile = new File("questions.txt");
            FileWriter fileWriter = new FileWriter(patientsFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Question question: questionList) {
                printWriter.println(question.getDoctor().getFirstName() + " " +
                        question.getDoctor().getLastName() + "$" + question.getPatient().getFirstName() +
                        " " + question.getPatient().getLastName() + "$" + question.getQuestion() +
                        "$" + question.getAnswer()+"$");
            }
            printWriter.close();
            System.out.println("Question file has been updated...");
        }
        catch (IOException e){
            System.out.println("Question file is missing!");
        }


    }
/*******************************************************/
public void readPostFile(){
    postList = new ArrayList<Post>();
    try {
        File examRequestsFile = new File("post.txt");
        Scanner scanner = new Scanner(examRequestsFile);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();


            String arrayOfData[] = new String[2];

            int i = 0;
            while (!s.equals("")) {
                String temp = s.substring(0, s.indexOf("$") +1);
                arrayOfData[i] = temp.replace("$", "");
//                System.out.println(arrayOfData[i]);
                s = s.replace(temp, "");
                i++;
            }

            createNewPost(arrayOfData[0] , arrayOfData[1]);
        }
    }
    catch (FileNotFoundException e){
        e.printStackTrace();
        System.out.println("the file for posts' is missing!");
    }

}


    public void createNewPost(String doctorName, String postString) {
        Doctor doctor = null;
        String doctorFirstName = doctorName.substring(0, doctorName.indexOf(" ") + 1);
        String doctorLastName = doctorName.replace(doctorFirstName, "");
        doctorFirstName = doctorFirstName.trim();
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorFirstName.equals(doctorList.get(i).getFirstName()) &&
                    doctorLastName.equals(doctorList.get(i).getLastName())) {
                doctor = doctorList.get(i);
                break;
            }
        }


        if (doctor != null) {
            Post post = new Post(doctor, postString);
            postList.add(post);
        }
    }


    public void updatePostFile(){
        try {
            File patientsFile = new File("post.txt");
            FileWriter fileWriter = new FileWriter(patientsFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Post post: postList) {
                printWriter.println(post.getDoctor().getFirstName() + " " +
                        post.getDoctor().getLastName() + "$" + post.getPostString()+"$");
            }
            printWriter.close();
            System.out.println("Post file has been updated...");
        }
        catch (IOException e){
            System.out.println("Post file is missing!");
        }

    }

    /******************************************/

    public void uploadPatientPhoto(Patient patient){
        File file = new File("pics\\patients\\"+patient.getFirstName()+" "+
                patient.getLastName()+".jpg");
        Image image = new Image(file.toURI().toString());
        patient.setPhoto(image);
    }

    public void uploadDoctorPhoto(Doctor doctor){
        File file = new File("pics\\doctors\\"+doctor.getFirstName()+" "+
                doctor.getLastName()+".jpg");
        Image image = new Image(file.toURI().toString());
        doctor.setPhoto(image);
    }



    public void updatePatientPhoto(Patient patient,File inputFile){
        BufferedImage bufferedImage = null;
        try
        {
            bufferedImage = new BufferedImage(500, 500,
                    BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            bufferedImage = ImageIO.read(inputFile);

        }
        catch(IOException e)
        {
            System.out.println("the photo you chose not found!!!");
        }

        //write the photo
        try
        {
            File outputFile = new File("pics\\patients\\"+patient.getFirstName()+" "+
                    patient.getLastName()+".jpg");

            ImageIO.write(bufferedImage, "jpg", outputFile);

            System.out.println("photo changed...");
            uploadPatientPhoto(patient);
        }
        catch(IOException e)
        {
            System.out.println("The patient's old photo not found:(");
        }
    }



    public void updateDoctorPhoto(Doctor doctor,File inputFile ){
        BufferedImage bufferedImage = null;
        try
        {
            bufferedImage = new BufferedImage(500, 500,
                    BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            bufferedImage = ImageIO.read(inputFile);

        }
        catch(IOException e)
        {
            System.out.println("the photo you chose not found!!!");
        }

        //write the photo
        try
        {
            File outputFile = new File("pics\\doctors\\"+doctor.getFirstName()+" "+
                    doctor.getLastName()+".jpg");

            ImageIO.write(bufferedImage, "jpg", outputFile);

            System.out.println("photo changed...");
            uploadDoctorPhoto(doctor);
        }
        catch(IOException e)
        {
            System.out.println("The doctor's old photo not found:(");
        }
    }

/***************************************************************************/
//just a test method
    public void printData(){
        System.out.println("Doctors:");
        for(Doctor doctor:doctorList){
            System.out.println(doctor.getFirstName()+" "+ doctor.getLastName());
        }
        System.out.println("Patients:");
        for(Patient patient:patientList){
            System.out.println(patient.getFirstName()+" "+ patient.getLastName());
        }
        System.out.println("ExamRequests: ");
        for(ExamRequest examRequest:examRequestList){
            System.out.println("to doctor: "+examRequest.getDoctor().getLastName()+" "+"subject: "
                    +examRequest.getSubjectString());
        }
        System.out.println("Questions:");
        for(Question question:questionList){
            System.out.println("to doctor: "+question.getDoctor().getLastName()+" "+"question: "
                    +question.getQuestion());        }
    }


    public boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
