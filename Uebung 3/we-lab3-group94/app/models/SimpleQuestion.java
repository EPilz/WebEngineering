package models;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class SimpleQuestion implements Question{
    @Override
    public void setId(int i) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void setText(String s) {

    }

    @Override
    public long getMaxTime() {
        return 0;
    }

    @Override
    public void setMaxTime(long l) {

    }

    @Override
    public List<Choice> getAllChoices() {
        return null;
    }

    @Override
    public List<Choice> getCorrectChoices() {
        return null;
    }

    @Override
    public void addChoice(Choice choice, boolean b) {

    }

    @Override
    public void removeChoice(Choice choice) {

    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public void setCategory(Category category) {

    }
}
