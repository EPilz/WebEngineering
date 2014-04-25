package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.Arrays;

/**
 * Created by Fitschi on 23.04.14.
 */
public class SimplePlay {

    private int id;
    private SimplePlayer player1;
    private SimplePlayer player2;
    /**Diese Variable speichert nur 1 oder 2, 1 bedeutet Spieler eins hat diese Runde gewonnen,
     * 2 bedeutet Spieler 2 hat diese Runde gewonnen
     */
    private int[] round;
    /**
     * count zählt für die Runden mit
     */
    private int count;

    public SimplePlay(){
        this.round = new int[4];
    }

    public SimplePlay(SimplePlayer player1, SimplePlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.round = new int[4];

    }
    public String roundWinner(){
        int player1Win = player1.getAnswer().rightAnswer();
        int player2Win = player2.getAnswer().rightAnswer();

        if(player1Win > player2Win){
            if(count < 5){
                count++;
            }else{
                count = 0;
            }
            round[count] = 1;
            return player1.getName();
        }

        if(count < 5){
            count++;
        }else{
            count = 0;
        }
        round[count] = 2;

        return player2.getName();
    }

    public String winner(){

        int countForPlayer1 = 0;
        int countForPlayer2 = 0;

        for(int i = 0; i < round.length; i++){
            int help = round[i];
            if(help == 1){
                countForPlayer1++;
            }else{
                countForPlayer2++;
            }

        }

        if(countForPlayer1 > countForPlayer2){
            return player1.getName();
        }
        return player2.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimplePlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(SimplePlayer player1) {
        this.player1 = player1;
    }

    public SimplePlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(SimplePlayer player2) {
        this.player2 = player2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getRound() {
        return round;
    }

    public void setRound(int[] round) {
        this.round = round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePlay)) return false;

        SimplePlay that = (SimplePlay) o;

        if (count != that.count) return false;
        if (id != that.id) return false;
        if (player1 != null ? !player1.equals(that.player1) : that.player1 != null) return false;
        if (player2 != null ? !player2.equals(that.player2) : that.player2 != null) return false;
        if (!Arrays.equals(round, that.round)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player1 != null ? player1.hashCode() : 0);
        result = 31 * result + (player2 != null ? player2.hashCode() : 0);
        result = 31 * result + (round != null ? Arrays.hashCode(round) : 0);
        result = 31 * result + count;
        return result;
    }
}