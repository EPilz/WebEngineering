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
@Access(AccessType.FIELD)
public class SimpleUser implements at.ac.tuwien.big.we14.lab2.api.User {

    private static final String TEXT = Messages.get("required.userName");

    @Id
    private String name;

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

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (name == null || name.isEmpty()) {
            errors.add(new ValidationError("name", Messages.get("required.username")));
        } else if(name.length() < 4) {
            errors.add(new ValidationError("name", Messages.get("toShort.username")));
        } else if(name.length() > 8) {
            errors.add(new ValidationError("name", Messages.get("toLong.username")));
        }

        if (password == null || password.isEmpty()) {
            errors.add(new ValidationError("password", Messages.get("required.password")));
        } else if(password.length() < 4) {
            errors.add(new ValidationError("password", Messages.get("toShort.password")));
        } else if(password.length() > 8) {
            errors.add(new ValidationError("password", Messages.get("toLong.password")));
        }
        if(errors.isEmpty()) {
            errors = null;
        }
        return errors;
    }
}
