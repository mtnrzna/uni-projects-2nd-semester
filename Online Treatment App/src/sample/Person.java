package sample;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.Date;

public abstract class Person {
    enum GENDER{
        MALE,FEMALE;
    }

    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    LocalDate dateOfBirth;
    Image photo;
    GENDER gender;
    float weight;

}
