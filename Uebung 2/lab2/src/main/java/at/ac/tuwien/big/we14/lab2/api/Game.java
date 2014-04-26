package at.ac.tuwien.big.we14.lab2.api;

import java.util.List;

public interface Game {
	
	public Player getPlayer1();
	
	public Player getPlayer2();
	
	public List<Round> getRounds();
	
	public void clearRounds();
	
	public void addRound(Round round);
	
	public int getPlayer1WinCount();
	
	public int getPlayer2WinCount();
	
	public String getWinnerText();

}
