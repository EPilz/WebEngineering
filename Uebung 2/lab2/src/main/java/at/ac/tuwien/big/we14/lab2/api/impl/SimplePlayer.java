package at.ac.tuwien.big.we14.lab2.api.impl;

/**
 * Created by Fitschi on 23.04.14.
 */
public class SimplePlayer{

    private int id;
    private String name;
    private SimpleAnswer answer;
    private SimpleQuestion question;

    public SimplePlayer(){
        this.name = "Spieler " + id;

    }

    public SimplePlayer(String name) {
        this.name = name;

    }

    public SimplePlayer(String name,  SimpleAnswer answer, SimpleQuestion question) {
        this.name = name;
        this.answer = answer;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(SimpleAnswer answer) {
        this.answer = answer;
    }

    public SimpleQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SimpleQuestion question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePlayer)) return false;

        SimplePlayer that = (SimplePlayer) o;

        if (id != that.id) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
