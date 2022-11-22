package sample;

public class Post {
    public Post(Doctor doctor, String postString) {
        this.doctor = doctor;
        this.postString = postString;
    }
    private  Doctor doctor;
    private String postString;



    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public String getPostString() {
        return postString;
    }
    public void setPostString(String postString) {
        this.postString = postString;
    }
}
