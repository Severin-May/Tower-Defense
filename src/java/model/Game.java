package model;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;
    }

    public void startGame(){

    }

    public void refresh(){

    }

    public void changeTurn(){
        currentTurn = currentTurn == player1 ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isGameEnded(){
        return player1.getCastle().isDestroyed() || player2.getCastle().isDestroyed();
    }
}
