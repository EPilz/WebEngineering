package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleRound implements Round{
    @Override
    public void initialize(List<User> users, List<Question> questions) {

    }

    @Override
    public Answer getAnswer(int i, User user) {
        return null;
    }

    @Override
    public void answerCurrentQuestion(List<Choice> choices, long l, User user, QuizFactory quizFactory) {

    }

    @Override
    public Question getQuestion(int i) {
        return null;
    }

    @Override
    public Question getCurrentQuestion(User user) {
        return null;
    }

    @Override
    public User getRoundWinner() {
        return null;
    }

    @Override
    public boolean areAllQuestionsAnswered() {
        return false;
    }

    @Override
    public List<Question> getQuestions() {
        return null;
    }
}
