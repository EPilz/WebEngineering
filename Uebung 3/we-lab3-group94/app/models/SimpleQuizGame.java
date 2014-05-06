package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleQuizGame implements at.ac.tuwien.big.we14.lab2.api.QuizGame {

    public static final int ROUND_NUMBERS = 5;

    public static final int QUESTION_NUMBERS_PER_ROUND = 3;

    private QuizFactory quizFactory;

    private List<Round> rounds;

    private List<User> users;

    public SimpleQuizGame(QuizFactory quizFactory, List<Round> rounds, List<User> users) {
        this.quizFactory = quizFactory;
        this.rounds = rounds;
        this.users = users;
    }

    public SimpleQuizGame(QuizFactory quizFactory) {
        this.quizFactory = quizFactory;
        this.rounds = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    @Override
    public Round getCurrentRound() {
        if(rounds.isEmpty()) {
            return null;
        }
        return rounds.get(rounds.size() - 1);
    }

    @Override
    public int getCurrentRoundCount() {
        return rounds.size();
    }

    @Override
    public void startNewRound() {
        Round round = quizFactory.createRound();
        List<Question> questions = new ArrayList<>();
        Category c = chooseCategory();
        for (int i = 0; i < QUESTION_NUMBERS_PER_ROUND; i++) {
            questions.add(nextQuestion(c, questions));
        }
        round.initialize(users, questions);
        rounds.add(round);
    }

    private Category chooseCategory() {
        List<Category> leftCategories = new ArrayList<>();
        leftCategories.addAll(quizFactory.createQuestionDataProvider().loadCategoryData());
        for (Round round : rounds) {
            leftCategories.remove(round.getQuestions().get(0).getCategory());
        }
        Random random = new Random();
        return leftCategories.get(random.nextInt(leftCategories.size()));
    }

    private Question nextQuestion(Category category, List<Question> questions) {
        List<Question> leftQuestion = new ArrayList<>();
        leftQuestion.addAll(category.getQuestions());
        for (Question q : questions) {
            leftQuestion.remove(q);
        }
        Random random = new Random();
        return leftQuestion.get(random.nextInt(leftQuestion.size()));
    }

    @Override
    public boolean isGameOver() {
        if(rounds.size() == ROUND_NUMBERS && isRoundOver()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRoundOver() {
        if (getCurrentRoundCount() > 0) {
            return false;
        }
        return rounds.get(getCurrentRoundCount() - 1).areAllQuestionsAnswered();
    }

    @Override
    public List<User> getPlayers() {
        return users;
    }

    @Override
    public int getWonRounds(User user) {
        int count = 0;
        for (Round round : rounds) {
            if(user.equals(round.getRoundWinner())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public User getWinner() {
        User winner = null;

        for (int i = 0; i < users.size(); i++) {
            for(int k = i + 1; k < users.size(); k++) {
                if(getWonRounds(users.get(i)) > getWonRounds(users.get(k))) {
                    winner = users.get(i);
                }
            }
        }
        return winner;
    }

    @Override
    public void answerCurrentQuestion(User user, List<Choice> choices, long time) {
        getCurrentRound().answerCurrentQuestion(choices, time, user, quizFactory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleQuizGame)) return false;

        SimpleQuizGame that = (SimpleQuizGame) o;

        if (quizFactory != null ? !quizFactory.equals(that.quizFactory) : that.quizFactory != null) return false;
        if (rounds != null ? !rounds.equals(that.rounds) : that.rounds != null) return false;
        if (users != null ? !users.equals(that.users) : that.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quizFactory != null ? quizFactory.hashCode() : 0;
        result = 31 * result + (rounds != null ? rounds.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleQuizGame{" +
                "quizFactory=" + quizFactory +
                ", rounds=" + rounds +
                ", users=" + users +
                '}';
    }
}
