package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    Game game;

    public GameWindow(Game game) throws HeadlessException {
        this.game = game;
        System.out.println(game.getPlayer1().getName() + " || " + game.getPlayer2().getName());
    }

    public GameWindow(GraphicsConfiguration gc, Game game) {
        super(gc);
        this.game = game;
    }

    public GameWindow(String title, Game game) throws HeadlessException {
        super(title);
        this.game = game;
    }

    public GameWindow(String title, GraphicsConfiguration gc, Game game) {
        super(title, gc);
        this.game = game;
    }
}
