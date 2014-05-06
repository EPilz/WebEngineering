package models;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleRound implements Round {

    private List<User> users;
    private List<Question> questions;
    private Map<User, List<Answer>> answers;

    public SimpleRound() {
        this.users = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.answers = new HashMap<>();
    }

    public SimpleRound(List<User> users, List<Question> questions, Map<User, List<Answer>> answers) {
        this.users = users;
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public void initialize(List<User> users, List<Question> questions) {
        this.users = users;
        this.questions = questions;
    }

    @Override
    public Answer getAnswer(int i, User user) {
        return answers.get(user).get(i - 1);
    }

    @Override
    public void answerCurrentQuestion(List<Choice> choices, long time, User user, QuizFactory quizFactory) {
        Answer answer = quizFactory.createAnswer();
        answer.setChoices(choices);
        answer.setPlayer(user);
        answer.setRound(this);
        answer.setTime(time);
        int currentQ = answers.get(user).size();
    }

    @Override
    public Question getQuestion(int i) {
        if(i > questions.size() || i < 0) {
            return null;
        }
        return questions.get(i);
    }

    @Override
    public Question getCurrentQuestion(User user) {
        int currentQ = answers.get(user).size();
        if(currentQ == questions.size()) {
            return null;
        }
        return questions.get(currentQ);
    }

    @Override
    public User getRoundWinner() {
        List<User> winner = new ArrayList<>();
        winner.add(users.get(0));
        int correct1 = 0;
        int correct2 = 0;
        int time1 = 0;
        int time2 = 0;

        for(User u : users.subList(1, users.size())) {
            for (Answer listA : answers.get(winner.get(0))) {
                if(listA.isCorrect()) {
                    correct1++;
                } else {
                    time1 += listA.getTime();
                }
            }

            for (Answer listA : answers.get(u)) {
                   if(listA.isCorrect()) {
                       correct2++;
                   } else {
                       time2 += listA.getTime();
                   }
            }

            if(correct1 < correct2) {
                winner.clear();
                winner.add(u);
            } else if(correct1 == correct2) {
                if(time1 < time2) {
                    winner.clear();
                    winner.add(u);
                } else if(time1 == time2) {
                    winner.add(u);
                }
            }
        }
        return winner.size() > 1 ? null : winner.get(0);
    }

    @Override
    public boolean areAllQuestionsAnswered() {
        for (User u : users) {
            if(answers.get(u).size() != SimpleQuizGame.QUESTION_NUMBERS_PER_ROUND) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleRound)) return false;

        SimpleRound that = (SimpleRound) o;

        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;
        if (users != null ? !users.equals(that.users) : that.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = users != null ? users.hashCode() : 0;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleRound{" +
                "users=" + users +
                ", questions=" + questions +
                ", answers=" + answers +
                '}';
    }
}
