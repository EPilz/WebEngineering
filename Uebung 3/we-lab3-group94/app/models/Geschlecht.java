package models;

/**
 * Created by Elisabeth on 03.05.2014.
 */
public enum Geschlecht {
    WEIBLICH("Weiblich"),
    MAENNLICH("Männlich");

    String text;

    Geschlecht(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
