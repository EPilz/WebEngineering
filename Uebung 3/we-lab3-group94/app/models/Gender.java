package models;

import scala.collection.Seq;

/**
 * Created by Elisabeth on 03.05.2014.
 */
public enum Gender {
    FEMALE("Weiblich"),
    MALE("Männlich");

    String text;

    Gender(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
