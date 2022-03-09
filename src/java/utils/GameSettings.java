package utils;

public class GameSettings {
    // Initial Towers' stats:
    public final static int simpleTowerRange = 0;
    public final static int longRangeTowerRange = 0;
    public final static int shortRangeTowerRange = 0;
    public final static int splashTowerRange = 0;
    public final static int simpleTowerAttackDamage = 0;
    public final static int longRangeAttackDamage = 0;
    public final static int shortRangeAttackDamage = 0;
    public final static int splashAttackDamage = 0;
    public final static int simpleTowerCost = 0;
    public final static int longRangeCost = 0;
    public final static int shortRangeCost = 0;
    public final static int splashCost = 0;
    public final static int simpleTowerShotCount = 0;
    public final static int longRangeShotCount = 0;
    public final static int shortRangeShotCount = 0;
    public final static int splashShotCount = 0;
    public final static int simpleTowerReloadTime = 0;
    public final static int longRangeReloadTime = 0;
    public final static int shortRangeReloadTime = 0;
    public final static int splashReloadTime = 0;

    //Initial Troops' stat:
    public final static int SimpleSoldierHP = 0;
    public final static int SimpleSoldierMovementSpeed = 0;
    public final static int SimpleSoldierCost = 0;
    public final static int AttackDamage = 0;
    // ... etc. for rest type of troops

    //Customizable map:
    public static int numberOfObstacles = 0;
    public static int mapWidthInCells = 0;
    public static int mapHeightInCells = 0;
    public static int cellWidth = 0;
    public static int cellHeight = 0;

    //Other initial states:
    public static int initialGold = 1000;
    public static int towerInitialHP = 1500;
    public static int castleInitialHP = 1500;

    public static void setNumberOfObstacles(int numberOfObstacles) {
        GameSettings.numberOfObstacles = numberOfObstacles;
    }
    public static void setMapWidthInCells(int mapWidthInCells) {
        GameSettings.mapWidthInCells = mapWidthInCells;
    }
    public static void setMapHeightInCells(int mapHeightInCells) {
        GameSettings.mapHeightInCells = mapHeightInCells;
    }
    public static void setCellWidth(int cellWidth) {
        GameSettings.cellWidth = cellWidth;
    }
    public static void setCellHeight(int cellHeight) {
        GameSettings.cellHeight = cellHeight;
    }
}
