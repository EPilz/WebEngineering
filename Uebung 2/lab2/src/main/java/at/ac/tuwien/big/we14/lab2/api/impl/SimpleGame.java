package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Game;
import at.ac.tuwien.big.we14.lab2.api.Player;
import at.ac.tuwien.big.we14.lab2.api.Round;

public class SimpleGame implements Game {

	private List<Round> rounds;
	private Player player1;
	private Player player2;
	

	public SimpleGame() {
		this.rounds = new ArrayList<>();
	}

	public SimpleGame(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.rounds = new ArrayList<>();
	}

	@Override
	public Player getPlayer1() {
		return player1;
	}


	@Override
	public Player getPlayer2() {
		return player2;
	}

	@Override
	public List<Round> getRounds() {
		return rounds;
	}

	@Override
	public void addRound(Round round) {
		rounds.add(round);
		
	}

	@Override
	public int getPlayer1WinCount() {
		int player1Win = 0;
		
		for (Round round : rounds) {
			if(round.getRoundWinner() != null && round.getRoundWinner().equals(player1)) {
				player1Win++;
			}
		}
		
		return player1Win;
	}

	@Override
	public int getPlayer2WinCount() {
		int player2Win = 0;
		
		for (Round round : rounds) {
			if(round.getRoundWinner() != null && round.getRoundWinner().equals(player2)) {
				player2Win++;
			}
		}
		
		return player2Win;
	}

	@Override
	public String getWinnerText() {
		int win1 = getPlayer1WinCount();
		int win2 = getPlayer2WinCount();
		if(win1 > win2) {
			return player1.getName() + " gewinnt!";
		} else if(win1 < win2) {
			return player2.getName() + " gewinnt!";
		} else {
			return "Unentschieden!";
		}
	}

	@Override
	public void clearRounds() {
		rounds.clear();	
	}
}
