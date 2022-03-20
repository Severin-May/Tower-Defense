package model;

import view.GameWindow;

import javax.swing.*;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    private static Game instance = null;
    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;
    }

    public static void initialise(Player player1, Player player2){
        if (instance != null){
            instance = new Game(player1,player2);
        }
    }
    public static Game getInstance(){
        return instance;
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
