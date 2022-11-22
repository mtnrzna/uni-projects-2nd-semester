package sample;

public class ExamRequest {
    public ExamRequest(Doctor doctor, Patient patient, String requestString,
                       String subjectString,String doctorOpinionString, boolean seen) {
        this.doctor = doctor;
        this.patient = patient;
        this.requestString = requestString;
        this.subjectString = subjectString;
        this.doctorOpinionString = doctorOpinionString;
        this.seen = seen;
    }

    private Doctor doctor;
    private Patient patient;
    private String requestString;
    private String subjectString;
    private String doctorOpinionString;
    private boolean seen;


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
    public String getRequestString() {
        return requestString;
    }
    public void setRequestString(String requestString) {
        this.requestString = requestString;
    }
    public String getSubjectString() {
        return subjectString;
    }
    public void setSubjectString(String subjectString) {
        this.subjectString = subjectString;
    }
    public String getDoctorOpinionString() {
        return doctorOpinionString;
    }
    public void setDoctorOpinionString(String doctorOpinionString) {
        this.doctorOpinionString = doctorOpinionString;
    }
    public boolean isSeen() {
        return seen;
    }
    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
