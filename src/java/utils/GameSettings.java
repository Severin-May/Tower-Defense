package utils;

public class GameSettings {
    // Initial Towers' stats:
        //simple towers:
    public final static int simpleTowerRange = 10;
    public final static int simpleTowerAttackDamage = 10;
    public final static int simpleTowerCost = 10;
    public final static int simpleTowerShotCount = 10;
    public final static int simpleTowerReloadTime = 10;
        //long ranged:
    public final static int longRangeTowerRange = 10;
    public final static int longRangeAttackDamage = 10;
    public final static int longRangeCost = 10;
    public final static int longRangeShotCount = 10;
    public final static int longRangeReloadTime = 10;
        //short ranged:
    public final static int shortRangeTowerRange = 10;
    public final static int shortRangeAttackDamage = 10;
    public final static int shortRangeCost = 10;
    public final static int shortRangeShotCount = 10;
    public final static int shortRangeReloadTime = 10;
        //splash:
    public final static int splashTowerRange = 10;
    public final static int splashAttackDamage = 10;
    public final static int splashCost = 10;
    public final static int splashShotCount = 10;
    public final static int splashReloadTime = 10;

    //Initial Troops' stat:
        //simple troop:
    public final static int simpleTroopHP = 10;
    public final static int simpleTroopMovementSpeed = 10;
    public final static int simpleTroopAttackDamage = 10;
    public final static int simpleTroopCost = 10;
    //slower but lots of hp
    public final static int slowBigTroopHP = 10;
    public final static int slowBigTroopMovementSpeed = 10;
    public final static int slowBigTroopAttackDamage = 10;
    public final static int slowBigTroopCost = 10;
    // ... etc. for rest type of troops

    //Customizable map:
    public static int numberOfObstacles = 10;
    public static int mapWidthInCells = 10;
    public static int mapHeightInCells = 10;
    public static int cellWidth = 10;
    public static int cellHeight = 10;

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
