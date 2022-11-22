package sample;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.Date;

public class Doctor extends Person {
    enum Specialization {
        GENERAL_SURGERY, NEUROLOGY,CARDIOLOGY,DENTISTRY, ORTHOPEDICS , DERMATOLOGY,
        INTERNAL_MEDICINE, PEDIATRICS, UROLOGY,PHYSICAL_THERAPY, NEPHROLOGY, OTHERS;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private Image photo;
    private GENDER gender;
    private float weight;
    private Specialization specialization;
    private int MedicalLicenceNom;

    public Image getPhoto() {
        return photo;
    }
    public void setPhoto(Image photo) {
        this.photo = photo;
    }
    private Image MedicalLicencePicture;
    private String bio;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public GENDER getGender() {
        return gender;
    }
    public void setGender(GENDER gender) {
        this.gender = gender;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public Specialization getSpecialization() {
        return specialization;
    }
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    public int getMedicalLicenceNom() {
        return MedicalLicenceNom;
    }
    public void setMedicalLicenceNom(int medicalLicenceNom) {
        MedicalLicenceNom = medicalLicenceNom;
    }
    public Image getMedicalLicencePicture() {
        return MedicalLicencePicture;
    }
    public void setMedicalLicencePicture(Image medicalLicencePicture) {
        MedicalLicencePicture = medicalLicencePicture;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

}
