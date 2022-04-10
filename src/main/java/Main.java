import model.Game;
import view.MainWindow;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new MainWindow();
        while (!Game.everyThingReady.get()){
            //waiting till everything ready
        }
        Game game = Game.getInstance();
        while (!Game.gameOver.get()){
            if (game.isFightingStage()){
                game.startGame();
            }
        }
        System.out.println("Game is over!");
        System.out.println("The winner color is: " + (Game.getInstance().getPlayer1().getCastle().isDestroyed() ? Game.getInstance().getPlayer2().getColor() : Game.getInstance().getPlayer1().getColor()));
        System.exit(0);
    }
}
