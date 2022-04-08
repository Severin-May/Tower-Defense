package utils;

import model.Game;

public class GameSettings {
    // Initial Towers' stats:
    //long ranged:
    public final static int longRangeTowerRange = 3;
    public final static int longRangeAttackDamage = 10;
    public final static int longRangeCost = 10;
    public final static int longRangeShotCount = 10;
    public final static int longRangeReloadTime = 10;
    //short ranged:
    public final static int shortRangeTowerRange = 2;
    public final static int shortRangeAttackDamage = 10;
    public final static int shortRangeCost = 10;
    public final static int shortRangeShotCount = 10;
    public final static int shortRangeReloadTime = 10;
    //splash:
    public final static int splashTowerRange = 3;
    public final static int splashAttackDamage = 10;
    public final static int splashCost = 10;
    public final static int splashShotCount = 10;
    public final static int splashReloadTime = 10;

    //Initial Troops' stat:
    //simple troop:
    public final static int simpleTroopHP = 10;
    public final static int simpleTroopMovementSpeed = 2;
    public final static int simpleTroopAttackDamage = 10;
    public final static int simpleTroopCost = 10;
    //slower but lots of hp
    public final static int slowBigTroopHP = 10;
    public final static int slowBigTroopMovementSpeed = 2;
    public final static int slowBigTroopAttackDamage = 10;
    public final static int slowBigTroopCost = 10;
    // ... etc. for rest type of troops

    //Map settings:
    public final static int numberOfObstacles = 3;
    public final static int padding = 25;
    public final static int mapHeightInCells = 10;
    public final static int mapWidthInCells = 15;
    public final static int cellWidth = 40;
    public final static int cellHeight = 40;
    public final static int mapWidthInPixels = mapWidthInCells * cellWidth + 2 * padding;
    public final static int mapHeightInPixels = mapHeightInCells * cellHeight + 2 * padding;
    public final static int rightPanelWidth = 400;
    //Game Settings
    public final static int fps = 10; //how many times to repaint the map in one second. Animation quality depends on this


    //Other initial states:
    public static int initialGold = 969696;
    public static int towerInitialHP = 1500;
    public static int castleInitialHP = 1500;


    // Image sizes in pixels:
    public final static int castleWidth = (int) (cellWidth * 1.2);
    public final static int castleHeight = (int) (cellHeight * 1.4);
    public final static int towerWidth = (int) (cellWidth * 1.0);
    public final static int towerHeight = (int) (cellHeight * 1.2);
    public final static int upgradedTowerWidth = (int) (towerWidth * 1.2);
    public final static int upgradedTowerHeight = (int) (towerHeight * 1.2);
    public final static int goldMineWidth = cellWidth;
    public final static int goldMineHeight = cellHeight;
    public final static int treasureChestWidth = cellWidth;
    public final static int treasureChestHeight = cellHeight;
    public final static int obstacleWidth = cellWidth;
    public final static int obstacleHeight = cellHeight;
    public final static int troopWidth = cellWidth;
    public final static int troopHeight = cellHeight;


    // Image paths as strings
    //Passive Buildings
    // Gold Mine
    public final static String goldMine = "src/main/resources/images/Area/Obstacles/notYet.png";
    public final static String treasure = "src/main/resources/images/Area/Obstacles/notYet.png";
    //Obstacles
    public final static String bush = "src/main/resources/images/Area/Obstacles/bush2.png";
    public final static String rock = "src/main/resources/images/Area/Obstacles/rock.png";
    public final static String stump = "src/main/resources/images/Area/Obstacles/stump.png";
    //Grass
    //All the 8 types of grasses are located with the following name convention : src/main/resources/images/Area/Grass + [0-7] + .png
    //So when accessing the pictures' location you must concatenate "X.png" to this constant where X ranges from 0 to 7
    public final static String grass = "src/main/resources/images/Area/Grass";


    //Blue player
    //Active Buildings
    //Castle
    public final static String blueCastle = "src/main/resources/images/Blue/Buildings/Castle/Blue.png";

    // LongRange
    public final static String blueLongRangeL1Left = "src/main/resources/images/Blue/Buildings/LongRange/L1/Left.png";
    public final static String blueLongRangeL1Right = "src/main/resources/images/Blue/Buildings/LongRange/L1/Right.png";
    public final static String blueLongRangeL2Right = "src/main/resources/images/Blue/Buildings/LongRange/L2/Right.png";
    public final static String blueLongRangeL2Left = "src/main/resources/images/Blue/Buildings/LongRange/L2/Left.png";
    //ShortRange
    public final static String blueShortRangeL1Left = "src/main/resources/images/Blue/Buildings/ShortRange/L1/Left.png";
    public final static String blueShortRangeL1Right = "src/main/resources/images/Blue/Buildings/ShortRange/L1/Right.png";
    public final static String blueShortRangeL2Right = "src/main/resources/images/Blue/Buildings/ShortRange/L2/Right.png";
    public final static String blueShortRangeL2Left = "src/main/resources/images/Blue/Buildings/ShortRange/L2/Left.png";
    //Splash
    public final static String blueSplashL1Left = "src/main/resources/images/Blue/Buildings/Splash/L1/Left.png";
    public final static String blueSplashL1Right = "src/main/resources/images/Blue/Buildings/Splash/L1/Right.png";
    public final static String blueSplashL2Right = "src/main/resources/images/Blue/Buildings/Splash/L2/Right.png";
    public final static String blueSplashL2Left = "src/main/resources/images/Blue/Buildings/Splash/L2/Left.png";

    //Troop
    //Mag
    //Back
    public final static String blueMagBackStop = "src/main/resources/images/Blue/Troop/Mag/Back/Stop.png";
    public final static String blueMagBackW1 = "src/main/resources/images/Blue/Troop/Mag/Back/V1.png";
    public final static String blueMagBackW2 = "src/main/resources/images/Blue/Troop/Mag/Back/V2.png";

    //Left
    public final static String blueMagLeftStop = "src/main/resources/images/Blue/Troop/Mag/Left/Stop.png";
    public final static String blueMagLeftW1 = "src/main/resources/images/Blue/Troop/Mag/Left/V1.png";
    public final static String blueMagLeftW2 = "src/main/resources/images/Blue/Troop/Mag/Left/V2.png";

    //Right
    public final static String blueMagRightStop = "src/main/resources/images/Blue/Troop/Mag/Right/Stop.png";
    public final static String blueMagRightW1 = "src/main/resources/images/Blue/Troop/Mag/Right/V1.png";
    public final static String blueMagRightW2 = "src/main/resources/images/Blue/Troop/Mag/Right/V2.png";

    //Sword
    //Back
    public final static String blueSwordBackStop = "src/main/resources/images/Blue/Troop/Sword/Back/Stop.png";
    public final static String blueSwordBackW1 = "src/main/resources/images/Blue/Troop/Sword/Back/V1.png";
    public final static String blueSwordBackW2 = "src/main/resources/images/Blue/Troop/Sword/Back/V2.png";

    //Left
    public final static String blueSwordLeftStop = "src/main/resources/images/Blue/Troop/Sword/Left/Stop.png";
    public final static String blueSwordLeftW1 = "src/main/resources/images/Blue/Troop/Sword/Left/V1.png";
    public final static String blueSwordLeftW2 = "src/main/resources/images/Blue/Troop/Sword/Left/V2.png";

    //Right
    public final static String blueSwordRightStop = "src/main/resources/images/Blue/Troop/Sword/Right/Stop.png";
    public final static String blueSwordRightW1 = "src/main/resources/images/Blue/Troop/Sword/Right/V1.png";
    public final static String blueSwordRightW2 = "src/main/resources/images/Blue/Troop/Sword/Right/V2.png";


    //Red player
    //Active Buildings
    //Castle
    public final static String redCastle = "src/main/resources/images/Red/Buildings/Castle/Red.png";

    // LongRange
    public final static String redLongRangeL1Left = "src/main/resources/images/Red/Buildings/LongRange/L1/Left.png";
    public final static String redLongRangeL1Right = "src/main/resources/images/Red/Buildings/LongRange/L1/Right.png";
    public final static String redLongRangeL2Right = "src/main/resources/images/Red/Buildings/LongRange/L2/Right.png";
    public final static String redLongRangeL2Left = "src/main/resources/images/Red/Buildings/LongRange/L2/Left.png";
    //ShortRange
    public final static String redShortRangeL1Left = "src/main/resources/images/Red/Buildings/ShortRange/L1/Left.png";
    public final static String redShortRangeL1Right = "src/main/resources/images/Red/Buildings/ShortRange/L1/Right.png";
    public final static String redShortRangeL2Right = "src/main/resources/images/Red/Buildings/ShortRange/L2/Right.png";
    public final static String redShortRangeL2Left = "src/main/resources/images/Red/Buildings/ShortRange/L2/Left.png";
    //Splash
    public final static String redSplashL1Left = "src/main/resources/images/Red/Buildings/Splash/L1/Left.png";
    public final static String redSplashL1Right = "src/main/resources/images/Red/Buildings/Splash/L1/Right.png";
    public final static String redSplashL2Right = "src/main/resources/images/Red/Buildings/Splash/L2/Right.png";
    public final static String redSplashL2Left = "src/main/resources/images/Red/Buildings/Splash/L2/Left.png";

    public static String getShortRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Left;
    }

    public static String getLongRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redLongRangeL1Left : blueLongRangeL1Left;
    }

    public static String getSplashL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redSplashL1Left : blueSplashL1Left;
    }

    public static String getMagLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redMagLeftStop : blueMagLeftStop;
    }

    public static String getSwordLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redSwordLeftStop : blueSwordLeftStop;

    }


    //Troop
    //Mag
    //Back
    public final static String redMagBackStop = "src/main/resources/images/Red/Troop/Mag/Back/Stop.png";
    public final static String redMagBackW1 = "src/main/resources/images/Red/Troop/Mag/Back/V1.png";
    public final static String redMagBackW2 = "src/main/resources/images/Red/Troop/Mag/Back/V2.png";

    //Left
    public final static String redMagLeftStop = "src/main/resources/images/Red/Troop/Mag/Left/Stop.png";
    public final static String redMagLeftW1 = "src/main/resources/images/Red/Troop/Mag/Left/V1.png";
    public final static String redMagLeftW2 = "src/main/resources/images/Red/Troop/Mag/Left/V2.png";

    //Right
    public final static String redMagRightStop = "src/main/resources/images/Red/Troop/Mag/Right/Stop.png";
    public final static String redMagRightW1 = "src/main/resources/images/Red/Troop/Mag/Right/V1.png";
    public final static String redMagRightW2 = "src/main/resources/images/Red/Troop/Mag/Right/V2.png";

    //Sword
    //Back
    public final static String redSwordBackStop = "src/main/resources/images/Red/Troop/Sword/Back/Stop.png";
    public final static String redSwordBackW1 = "src/main/resources/images/Red/Troop/Sword/Back/V1.png";
    public final static String redSwordBackW2 = "src/main/resources/images/Red/Troop/Sword/Back/V2.png";

    //Left
    public final static String redSwordLeftStop = "src/main/resources/images/Red/Troop/Sword/Left/Stop.png";
    public final static String redSwordLeftW1 = "src/main/resources/images/Red/Troop/Sword/Left/V1.png";
    public final static String redSwordLeftW2 = "src/main/resources/images/Red/Troop/Sword/Left/V2.png";

    //Right
    public final static String redSwordRightStop = "src/main/resources/images/Red/Troop/Sword/Right/Stop.png";
    public final static String redSwordRightW1 = "src/main/resources/images/Red/Troop/Sword/Right/V1.png";
    public final static String redSwordRightW2 = "src/main/resources/images/Red/Troop/Sword/Right/V2.png";

}
