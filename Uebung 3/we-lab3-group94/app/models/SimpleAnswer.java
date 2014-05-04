package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleAnswer implements Answer{
    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public void setTime(long l) {

    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public void setPlayer(User user) {

    }

    @Override
    public User getPlayer() {
        return null;
    }

    @Override
    public void setChoices(List<Choice> choices) {

    }

    @Override
    public List<Choice> getChoices() {
        return null;
    }

    @Override
    public void setRound(Round round) {

    }

    @Override
    public Round getRound() {
        return null;
    }

    @Override
    public void setQuestion(Question question) {

    }

    @Override
    public Question getQuestion() {
        return null;
    }
}
