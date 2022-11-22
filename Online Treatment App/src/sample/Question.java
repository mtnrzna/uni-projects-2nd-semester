package sample;

public class Question {
    public Question(Doctor doctor, Patient patient, String question, String answer) {
        this.doctor = doctor;
        this.patient = patient;
        this.question = question;
        if(answer == null)
            answer = null;
        this.answer = answer;
    }

    private Doctor doctor;
    private Patient patient;
    private String question;
    private String answer;

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
