package at.ac.tuwien.big.we14.lab2.api;

import at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer;

/**
 * Created by Fitschi on 23.04.14.
 */
public interface Play {

    public String winner();

    public int getScorePlayer1();

    public int getScorePlayer2();

    public SimplePlayer getPlayer1();

    public void setPlayer1(SimplePlayer player1);

    public SimplePlayer getPlayer2();

    public void setPlayer2(SimplePlayer player2);
}
