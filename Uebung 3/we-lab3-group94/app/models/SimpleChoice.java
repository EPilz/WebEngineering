package models;

import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleChoice implements Choice{
    @Override
    public String getText() {
        return null;
    }

    @Override
    public void setText(String s) {

    }

    @Override
    public void setQuestion(Question question) {

    }

    @Override
    public Question getQuestion() {
        return null;
    }

    @Override
    public void setId(int i) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
