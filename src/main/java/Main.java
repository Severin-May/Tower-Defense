import model.Game;
import view.MainWindow;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new MainWindow();
        Thread.sleep(5000L);
        while (!Game.everyThingReady.get()){
            //waiting till everything ready
        }
        Game game = Game.getInstance();
        while (!game.isGameEnded()){
            if (game.isFightingStage()){
                game.startGame();
            }
        }
    }
}
