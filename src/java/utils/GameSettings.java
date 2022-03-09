package utils;

public class GameSettings {
    // Initial Towers' stats:
    public final static int TowerRange = 0;
    public final static int SimpleTowerAttackDamage = 0;
    public final static int LongRangeAttackDamage = 0;
    public final static int ShortRangeAttackDamage = 0;
    public final static int SplashAttackDamage = 0;
    public final static int SimpleTowerCost = 0;
    public final static int LongRangeCost = 0;
    public final static int ShortRangeCost = 0;
    public final static int SplashCost = 0;
    public final static int SimpleTowerShotCount = 0;
    public final static int LongRangeShotCount = 0;
    public final static int ShortRangeShotCount = 0;
    public final static int SplashShotCount = 0;
    public final static int SimpleTowerReloadTime = 0;
    public final static int LongRangeReloadTime = 0;
    public final static int ShortRangeReloadTime = 0;
    public final static int SplashReloadTime = 0;

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
