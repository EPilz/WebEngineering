package at.ac.tuwien.big.we14.lab2.api;

import java.util.List;

public interface Round {
	
	public Category getCategory();
	
	public void setCategory(Category category);
	
	public void addQuestion(Question question);
	
	public List<Question> getQuestions();
	
	public Answer[] getAnswersPlayer1();
	
	public void setAnswersPlayer1(int index, Answer answer);
	
	public Answer[] getAnswersPlayer2();
	
	public void setAnswersPlayer2(int index, Answer answer);
	
	public void addTimePlayer1(int time);
	
	public void addTimePlayer2(int time);
	
	public Player getRoundWinner();
	
	public String getRoundWinnerText();
}
 