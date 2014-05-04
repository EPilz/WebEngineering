package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleQuizGame implements at.ac.tuwien.big.we14.lab2.api.QuizGame{
    @Override
    public Round getCurrentRound() {
        return null;
    }

    @Override
    public int getCurrentRoundCount() {
        return 0;
    }

    @Override
    public void startNewRound() {

    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isRoundOver() {
        return false;
    }

    @Override
    public List<User> getPlayers() {
        return null;
    }

    @Override
    public int getWonRounds(User user) {
        return 0;
    }

    @Override
    public User getWinner() {
        return null;
    }

    @Override
    public void answerCurrentQuestion(User user, List<Choice> choices, long l) {

    }
}
