package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Game;
import at.ac.tuwien.big.we14.lab2.api.Player;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.Round;

public class SimpleRound implements Round {
	
	private Game game;
	private Category category;
	private List<Question> questions;
	private Answer[] answersPlayer1;
	private Answer[] answersPlayer2;
	public int timePlayer1;
	public int timePlayer2;
	public int roundNumber;

	public SimpleRound() {
		this.questions = new ArrayList<>();
		this.answersPlayer1 = new Answer[] {Answer.UNKNOWN, Answer.UNKNOWN, Answer.UNKNOWN};
		this.answersPlayer2 = new Answer[] {Answer.UNKNOWN, Answer.UNKNOWN, Answer.UNKNOWN};
		this.timePlayer1 = 0;
		this.timePlayer2 = 0;
		this.roundNumber = 0;
	}

	public SimpleRound(Category category, Game game, int roundNumber) {
		super();
		this.game = game;
		this.category = category;
		this.questions = new ArrayList<>();
		this.roundNumber = roundNumber;
		this.answersPlayer1 = new Answer[] {Answer.UNKNOWN, Answer.UNKNOWN, Answer.UNKNOWN};
		this.answersPlayer2 = new Answer[] {Answer.UNKNOWN, Answer.UNKNOWN, Answer.UNKNOWN};
		this.timePlayer1 = 0;
		this.timePlayer2 = 0;	
	}

	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public void addQuestion(Question question) {
		questions.add(question);	
	}

	@Override
	public Answer[] getAnswersPlayer1() {
		return answersPlayer1;
	}

	@Override
	public void setAnswersPlayer1(int index, Answer answer) {
		answersPlayer1[index] = answer;	
	}

	@Override
	public Answer[] getAnswersPlayer2() {
		return answersPlayer2;
	}

	@Override
	public void setAnswersPlayer2(int index, Answer answer) {
		answersPlayer2[index] = answer;		
	}
	
	/**
	 * 
	 * @return player1 - for Player 1 win
	 * 		   player2 - for Player 2 win
	 * 		   null - for undecided
	 */
	public Player getRoundWinner() {
		int correct1 = 0;
		int correct2 = 0;
		for (Answer a : answersPlayer1) {
			if(a.equals(Answer.CORRECT)) {
				correct1++;
			}
		}
		for (Answer a : answersPlayer2) {
			if(a.equals(Answer.CORRECT)) {
				correct2++;
			}
		}
		
		if(correct1 > correct2) {
			return game.getPlayer1();
		} else if(correct1 < correct2) {
			return game.getPlayer2();
		} else {
			if(timePlayer1 > timePlayer2) {
				return game.getPlayer1();
			} else if(timePlayer1 < timePlayer2) {
				return game.getPlayer2();
			} else {
				return null;
			}
		}
	}
	
	public String getRoundWinnerText() {
		Player winner = getRoundWinner();
		if(winner != null) {
			return winner.getName() + " gewinnt Runde " + roundNumber + "!";
		} else {
			return "Runde " + roundNumber + " unentschieden!";
		}
	}

	@Override
	public void addTimePlayer1(int time) {
		this.timePlayer1 += time;	
	}

	@Override
	public void addTimePlayer2(int time) {
		this.timePlayer2 += time;		
	}

}
