package at.ac.tuwien.big.we14.lab2.api.impl;

import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fitschi on 23.04.14.
 */
public class SimpleAnswer {

    private int id;
    private String[] answer;
    private Question question;

    public SimpleAnswer(){
        this.answer = new String[3];

    }

    public SimpleAnswer(String[] answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    public int rightAnswer(){
        int count = 0;
        List<Choice> correctChoice = question.getCorrectChoices();

        for(int i = 0; i < correctChoice.size(); i++) {
            answer[i] = correctChoice.get(i).getText();
            count++;
        }
        return count;

    }


    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleAnswer)) return false;

        SimpleAnswer that = (SimpleAnswer) o;

        if (id != that.id) return false;
        if (!Arrays.equals(answer, that.answer)) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answer != null ? Arrays.hashCode(answer) : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
