package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Elisabeth on 03.05.2014.
 */
@Entity
@Access(AccessType.FIELD)
public class User implements at.ac.tuwien.big.we14.lab2.api.User{

    @Id
    @Constraints.Required(message = "Der Benutzername ist ein Pflichtfeld!")
    @Constraints.MinLength(value = 4, message = "Der Benutzername muss mindestens 4 Zeichen lang sein!")
    @Constraints.MaxLength(value = 8, message = "Der Benutzername darf maximal 8 Zeichen enthalten!")
    private String name;

    @Constraints.Required(message = "Das Passwort ist ein Pflichtfeld!")
    @Constraints.MinLength(value = 4, message = "Das Passwort muss mindestens 4 Zeichen lang sein!")
    @Constraints.MaxLength(value = 8, message = "Das Passwort darf maximal 8 Zeichen enthalten!")
    private String password;

    private String firstname;

    private String lastname;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
