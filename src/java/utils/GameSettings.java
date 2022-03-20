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
    public final static int shortRangeTowerRange = 4;
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

    //Map settings:
    public final static int numberOfObstacles = 3;
    public final static int mapHeightInCells = 20;
    public final static int mapWidthInCells = 30;
    public final static int cellWidth = 45;
    public final static int cellHeight = cellWidth;
    public final static int mapWidthInPixels = mapWidthInCells * cellWidth;
    public final static int mapHeightInPixels = mapHeightInCells * cellHeight;
    public final static int rightPanelWidth = 300;
    //Game Settings
    public final static int fps = 2; //how many times to repaint the map in one second


    //Other initial states:
    public static int initialGold = 1000;
    public static int towerInitialHP = 1500;
    public static int castleInitialHP = 1500;
}
