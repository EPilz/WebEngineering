package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.io.InputStream;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public abstract class SimpleQuizFactory extends QuizFactory {

    @Override
    public Category createCategory() {
        return new SimpleCategory();
    }

    @Override
    public Question createQuestion() {
        return new SimpleQuestion();
    }

    @Override
    public Choice createChoice() {
        return new SimpleChoice();
    }

    @Override
    public Answer createAnswer() {
        return new SimpleAnswer();
    }

    @Override
    public Round createRound() {
        return new SimpleRound();
    }

    @Override
    public QuizGame createQuizGame() {
        return new SimpleQuizGame();
    }

    @Override
    public User createUser() {
        return new SimpleUser();
    }

}
