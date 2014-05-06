package models;


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.i18n.Messages;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Elisabeth on 03.05.2014.
 */
@Entity
//@Table(name = "user")
@Access(AccessType.FIELD)
public class SimpleUser implements at.ac.tuwien.big.we14.lab2.api.User {

    private static final String TEXT = Messages.get("required.userName");

    @Id
    private String userName;

 /*   @Constraints.Required //(message = "Das Passwort ist ein Pflichtfeld!")
    @Constraints.MinLength(value = 4)//, message = "Das Passwort muss mindestens 4 Zeichen lang sein!")
    @Constraints.MaxLength(value = 8) // message = "Das Passwort darf maximal 8 Zeichen enthalten!")*/
    private String password;

    private String firstname;

    private String lastname;

    @Formats.DateTime(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public SimpleUser() {
    }

    public SimpleUser(String name, String password) {
        this.userName = name;
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
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = null;
        if (userName == null || userName.isEmpty()) {
            errors = new ArrayList<ValidationError>();
            errors.add(new ValidationError("username", Messages.get("required.username")));
        }
        return errors;
    }
}
