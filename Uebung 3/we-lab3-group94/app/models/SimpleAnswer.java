package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleAnswer implements Answer {

    private long time;
    private User user;
    private List<Choice> choices;
    private Round round;
    private Question question;

    public SimpleAnswer() {
        this.time = 0;
        this.user = null;
        this.choices = new ArrayList<>();
        this.round = null;
        this.question = null;
    }

    public SimpleAnswer(long time, User user, List<Choice> choices, Round round, Question question) {
        this.time = time;
        this.user = user;
        this.choices = choices;
        this.round = round;
        this.question = question;
    }

    @Override
    public boolean isCorrect() {
        if(question.getCorrectChoices().containsAll(choices) &&
                question.getCorrectChoices().size() == choices.size()) {
            return true;
        }
        return false;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public User getPlayer() {
        return user;
    }

    @Override
    public void setPlayer(User user) {
        this.user = user;
    }

    @Override
    public List<Choice> getChoices() {
        return choices;
    }

    @Override
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public Round getRound() {
        return round;
    }

    @Override
    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleAnswer)) return false;

        SimpleAnswer that = (SimpleAnswer) o;

        if (time != that.time) return false;
        if (choices != null ? !choices.equals(that.choices) : that.choices != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (round != null ? !round.equals(that.round) : that.round != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (time ^ (time >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (choices != null ? choices.hashCode() : 0);
        result = 31 * result + (round != null ? round.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleAnswer{" +
                "time=" + time +
                ", user=" + user +
                ", choices=" + choices +
                ", round=" + round +
                ", question=" + question +
                '}';
    }
}
