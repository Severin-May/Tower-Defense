package model;


public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    private static Game instance = null;
    private Building buildingHover;
    private boolean isPreparationTime = true;

    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;
    }

    public static void initialise(Player player1, Player player2) {
        if (instance == null) {
            instance = new Game(player1, player2);
        }
    }

    public static Game getInstance() {
        return instance;
    }

    public void startGame() {

    }

    public void refresh() {

    }

    public void changeTurn() {
        currentTurn = currentTurn == player1 ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    //to check if tower is clicked and being hovered on the screen
    public boolean isPlacingTower() {
        return this.buildingHover != null;
    }

    //get the building that the user clicked and hovering now
    public Building getBuildingHover() {
        return buildingHover;
    }

    /**
     * set what building user chose to build to start hovering it. IMPORTANT: Set it to null to disable hovering
     */
    public void setBuildingHover(Building buildingHover) {
        this.buildingHover = buildingHover;
    }

    public boolean isGameEnded() {
        return player1.getCastle().isDestroyed() || player2.getCastle().isDestroyed();
    }

    public boolean isPreparationTime() {
        return isPreparationTime;
    }

    public void setPreparationTime(boolean preparationTime) {
        isPreparationTime = preparationTime;
    }
}
