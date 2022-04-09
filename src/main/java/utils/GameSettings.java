package utils;

import model.Game;

import javax.swing.*;
import java.awt.*;
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
    public final static int simpleTroopMovementPoints = 100;
    //slower but lots of hp
    public final static int slowBigTroopHP = 10;
    public final static int slowBigTroopMovementSpeed = 2;
    public final static int slowBigTroopAttackDamage = 10;
    public final static int slowBigTroopCost = 10;
    public final static int slowBigTroopMovementPoints = 100;
    // ... etc. for rest type of troops

    //Map settings:
    public final static int numberOfObstacles = 3;
    public final static int padding = 25;
    public final static int mapHeightInCells = 15;
    public final static int mapWidthInCells = 20;
    public final static int cellWidth = 70;
    public final static int cellHeight = 70;
    public final static int mapWidthInPixels = mapWidthInCells * cellWidth + 2 * padding;
    public final static int mapHeightInPixels = mapHeightInCells * cellHeight + 2 * padding;
    public final static int rightPanelWidth = 600;
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
    public final static Image goldMine = new ImageIcon("src/main/resources/images/Area/Obstacles/notYet.png").getImage();
    public final static Image treasure = new ImageIcon("src/main/resources/images/Area/Obstacles/notYet.png").getImage();
    //Obstacles
    public final static Image bush = new ImageIcon("src/main/resources/images/Area/Obstacles/bush2.png").getImage();
    public final static Image rock = new ImageIcon("src/main/resources/images/Area/Obstacles/rock.png").getImage();
    public final static Image stump = new ImageIcon("src/main/resources/images/Area/Obstacles/stump.png").getImage();
    //Grass
    public final static Image grass0 = new ImageIcon("src/main/resources/images/Area/Grass0.png").getImage();
    public final static Image grass1 = new ImageIcon("src/main/resources/images/Area/Grass1.png").getImage();
    public final static Image grass2 = new ImageIcon("src/main/resources/images/Area/Grass2.png").getImage();
    public final static Image grass3 = new ImageIcon("src/main/resources/images/Area/Grass3.png").getImage();
    public final static Image grass4 = new ImageIcon("src/main/resources/images/Area/Grass4.png").getImage();
    public final static Image grass5 = new ImageIcon("src/main/resources/images/Area/Grass5.png").getImage();
    public final static Image grass6 = new ImageIcon("src/main/resources/images/Area/Grass6.png").getImage();
    public final static Image grass7 = new ImageIcon("src/main/resources/images/Area/Grass7.png").getImage();
    public final static Image[] grass = {grass0,grass1,grass2,grass3,grass4,grass5,grass6,grass7};




    //Blue player
    //Active Buildings
    //Castle
    public final static Image blueCastle = new ImageIcon("src/main/resources/images/Blue/Buildings/Castle/Blue.png").getImage();

    // LongRange
    public final static Image blueLongRangeL1Left = new ImageIcon("src/main/resources/images/Blue/Buildings/LongRange/L1/Left.png").getImage();
    public final static Image blueLongRangeL1Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/LongRange/L1/Right.png").getImage();
    public final static Image blueLongRangeL2Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/LongRange/L2/Right.png").getImage();
    public final static Image blueLongRangeL2Left = new ImageIcon ("src/main/resources/images/Blue/Buildings/LongRange/L2/Left.png").getImage();
    //ShortRange
    public final static Image blueShortRangeL1Left = new ImageIcon ("src/main/resources/images/Blue/Buildings/ShortRange/L1/Left.png").getImage();
    public final static Image blueShortRangeL1Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/ShortRange/L1/Right.png").getImage();
    public final static Image blueShortRangeL2Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/ShortRange/L2/Right.png").getImage();
    public final static Image blueShortRangeL2Left = new ImageIcon ("src/main/resources/images/Blue/Buildings/ShortRange/L2/Left.png").getImage();
    //Splash
    public final static Image blueSplashL1Left = new ImageIcon ("src/main/resources/images/Blue/Buildings/Splash/L1/Left.png").getImage();
    public final static Image blueSplashL1Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/Splash/L1/Right.png").getImage();
    public final static Image blueSplashL2Right = new ImageIcon ("src/main/resources/images/Blue/Buildings/Splash/L2/Right.png").getImage();
    public final static Image blueSplashL2Left = new ImageIcon ("src/main/resources/images/Blue/Buildings/Splash/L2/Left.png").getImage();

    //Troop
    //Mag
    //Back
    public final static Image blueMagBackStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Back/Stop.png").getImage();
    public final static Image blueMagBackW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Back/V1.png").getImage();
    public final static Image blueMagBackW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Back/V2.png").getImage();
    public final static Image[] blueMagBackWalk = {blueMagBackW1,blueMagBackW2};

    //Left
    public final static Image blueMagLeftStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Left/Stop.png").getImage();
    public final static Image blueMagLeftW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Left/V1.png").getImage();
    public final static Image blueMagLeftW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Left/V2.png").getImage();
    public final static Image[] blueMagLeftWalk = {blueMagLeftW1,blueMagLeftW2};

    //Right
    public final static Image blueMagRightStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Right/Stop.png").getImage();
    public final static Image blueMagRightW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Right/V1.png").getImage();
    public final static Image blueMagRightW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Mag/Right/V2.png").getImage();
    public final static Image[] blueMagRightWalk = {blueMagRightW1,blueMagRightW2};
    //Sword
    //Back
    public final static Image blueSwordBackStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Back/Stop.png").getImage();
    public final static Image blueSwordBackW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Back/V1.png").getImage();
    public final static Image blueSwordBackW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Back/V2.png").getImage();
    public final static Image[] blueSwordBackWalk = {blueSwordBackW1,blueSwordBackW2};
    //Left
    public final static Image blueSwordLeftStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Left/Stop.png").getImage();
    public final static Image blueSwordLeftW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Left/V1.png").getImage();
    public final static Image blueSwordLeftW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Left/V2.png").getImage();
    public final static Image[] blueSwordLeftWalk = {blueSwordLeftW1,blueSwordLeftW2};
    //Right
    public final static Image blueSwordRightStop = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Right/Stop.png").getImage();
    public final static Image blueSwordRightW1 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Right/V1.png").getImage();
    public final static Image blueSwordRightW2 = new ImageIcon ("src/main/resources/images/Blue/Troop/Sword/Right/V2.png").getImage();
    public final static Image[] blueSwordRightWalk = {blueSwordRightW1,blueSwordRightW2};

    //Red player
    //Active Buildings
    //Castle
    public final static Image redCastle = new ImageIcon ("src/main/resources/images/Red/Buildings/Castle/Red.png").getImage();

    // LongRange
    public final static Image redLongRangeL1Left = new ImageIcon ("src/main/resources/images/Red/Buildings/LongRange/L1/Left.png").getImage();
    public final static Image redLongRangeL1Right = new ImageIcon ("src/main/resources/images/Red/Buildings/LongRange/L1/Right.png").getImage();
    public final static Image redLongRangeL2Right = new ImageIcon ("src/main/resources/images/Red/Buildings/LongRange/L2/Right.png").getImage();
    public final static Image redLongRangeL2Left = new ImageIcon ("src/main/resources/images/Red/Buildings/LongRange/L2/Left.png").getImage();
    //ShortRange
    public final static Image redShortRangeL1Left = new ImageIcon ("src/main/resources/images/Red/Buildings/ShortRange/L1/Left.png").getImage();
    public final static Image redShortRangeL1Right = new ImageIcon ("src/main/resources/images/Red/Buildings/ShortRange/L1/Right.png").getImage();
    public final static Image redShortRangeL2Right = new ImageIcon ("src/main/resources/images/Red/Buildings/ShortRange/L2/Right.png").getImage();
    public final static Image redShortRangeL2Left = new ImageIcon ("src/main/resources/images/Red/Buildings/ShortRange/L2/Left.png").getImage();
    //Splash
    public final static Image redSplashL1Left = new ImageIcon ("src/main/resources/images/Red/Buildings/Splash/L1/Left.png").getImage();
    public final static Image redSplashL1Right = new ImageIcon ("src/main/resources/images/Red/Buildings/Splash/L1/Right.png").getImage();
    public final static Image redSplashL2Right = new ImageIcon ("src/main/resources/images/Red/Buildings/Splash/L2/Right.png").getImage();
    public final static Image redSplashL2Left = new ImageIcon ("src/main/resources/images/Red/Buildings/Splash/L2/Left.png").getImage();

    public static Image getShortRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Left;
    }

    public static Image getLongRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redLongRangeL1Left : blueLongRangeL1Left;
    }

    public static Image getSplashL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redSplashL1Left : blueSplashL1Left;
    }

    public static Image getMagLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redMagLeftStop : blueMagLeftStop;
    }

    public static Image getSwordLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals("Red") ? redSwordLeftStop : blueSwordLeftStop;

    }


    //Troop
    //Mag
    //Back
    public final static Image redMagBackStop = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Back/Stop.png").getImage();
    public final static Image redMagBackW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Back/V1.png").getImage();
    public final static Image redMagBackW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Back/V2.png").getImage();
    public final static Image[] redMagBackWalk = {redMagBackW1,redMagBackW2};
    //Left
    public final static Image redMagLeftStop = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Left/Stop.png").getImage();
    public final static Image redMagLeftW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Left/V1.png").getImage();
    public final static Image redMagLeftW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Left/V2.png").getImage();
    public final static Image[] redMagLeftWalk = {redMagLeftW1,redMagLeftW2};
    //Right
    public final static Image redMagRightStop = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Right/Stop.png").getImage();
    public final static Image redMagRightW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Right/V1.png").getImage();
    public final static Image redMagRightW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Mag/Right/V2.png").getImage();
    public final static Image[] redMagRightWalk = {redMagRightW1,redMagRightW2};
    //Sword
    //Back
    public final static Image redSwordBackStop = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Back/Stop.png").getImage();
    public final static Image redSwordBackW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Back/V1.png").getImage();
    public final static Image redSwordBackW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Back/V2.png").getImage();
    public final static Image[] redSwordBackWalk = {redSwordBackW1,redSwordBackW2};
    //Left
    public final static Image redSwordLeftStop = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Left/Stop.png").getImage();
    public final static Image redSwordLeftW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Left/V1.png").getImage();
    public final static Image redSwordLeftW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Left/V2.png").getImage();
    public final static Image[] redSwordLeftWalk = {redSwordLeftW1,redSwordLeftW2};
    //Right
    public final static Image redSwordRightStop = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Right/Stop.png").getImage();
    public final static Image redSwordRightW1 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Right/V1.png").getImage();
    public final static Image redSwordRightW2 = new ImageIcon ("src/main/resources/images/Red/Troop/Sword/Right/V2.png").getImage();
    public final static Image[] redSwordRightWalk = {redSwordRightW1,redSwordRightW2};
}
