package models;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleCategory implements Category{
    @Override
    public void setName(String s) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Question> getQuestions() {
        return null;
    }

    @Override
    public void addQuestion(Question question) {

    }

    @Override
    public void removeQuestion(Question question) {

    }
}
