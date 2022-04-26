package utils;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Most important constants of the game are saved here.
 * This class also keeps images preloaded
 */
public class GameSettings {
    // Initial Towers' stats:
    public final static int towerShotSpriteSpeed = 15;
    //long ranged:
    public final static int longRangeTowerRange = 4;
    public final static int longRangeAttackDamage = 2;
    public final static int longRangeShotCount = 10;
    public final static int longRangeReloadTime = 3;
    //short ranged:
    public final static int shortRangeTowerRange = 2;
    public final static int shortRangeAttackDamage = 2;
    public final static int shortRangeShotCount = 10;
    public final static int shortRangeReloadTime = 1;
    //splash:
    public final static int splashTowerRange = 3;
    public final static int splashAttackDamage = 1;
    public final static int splashShotCount = 10;
    public final static int splashReloadTime = 5;

    // Upgraded Towers' stats:
    //upgraded long ranged:
    public final static int upgradedLongRangeTowerRange = longRangeTowerRange + 2;
    public final static int upgradedLongRangeAttackDamage = longRangeAttackDamage + 5;
    public final static int upgradedLongRangeShotCount = longRangeShotCount + 2;
    public final static int upgradedLongRangeReloadTime = longRangeReloadTime - 1;
    //upgraded short ranged:
    public final static int upgradedShortRangeTowerRange = shortRangeTowerRange + 2;
    public final static int upgradedShortRangeAttackDamage = shortRangeAttackDamage + 5;
    public final static int upgradedShortRangeShotCount = shortRangeShotCount + 2;
    public final static int upgradedShortRangeReloadTime = shortRangeReloadTime - 1;
    //upgraded splash:
    public final static int upgradedSplashTowerRange = splashTowerRange + 2;
    public final static int upgradedSplashAttackDamage = splashAttackDamage + 5;
    public final static int upgradedSplashShotCount = splashShotCount + 2;
    public final static int upgradedSplashReloadTime = splashReloadTime - 1;

    // earning/spending
    public final static int longRangeCost = 10;
    public final static int shortRangeCost = 10;
    public final static int splashCost = 10;
    public final static int goldMineCost = 25;
    public final static int swordManCost = 2;
    public final static int magCost = 2;
    public final static int goldMineIncomePerRound = 5;
    public final static int awardForKillingTroop = 2;
    public final static int awardForPickingTreasure = 15;
    public final static int towerUpgradeCost = 10;


    //Initial Troops' stat:
    //sword man troop:
    public final static int swordManHp = 10;
    public final static int swordManSpeed = 2;
    public final static int swordManAttackDamage = 10;
    public final static int swordManMovementPoints = 4;
    //mag troop
    public final static int magHp = 7;
    public final static int magSpeed = 3;
    public final static int magAttackDamage = 10;
    public final static int magMovementPoints = 6;

    //Map settings:
    public final static int numberOfObstacles = 6;
    public final static int padding = 25;
    public final static int mapHeightInCells = 15;
    public final static int mapWidthInCells = 20;
    public final static int cellWidth = 30;
    public final static int cellHeight = 30;
    public final static int mapWidthInPixels = mapWidthInCells * cellWidth + 2 * padding;
    public final static int mapHeightInPixels = mapHeightInCells * cellHeight + 2 * padding;
    public final static int additionalBlankSpaceBelowInPixels = 200; // blank space where the cell info is displayed
    public final static int rightPanelWidth = 600;
    //Game Settings
    public final static int fps = 15; //how many times to repaint the map in one second. Animation quality depends on this


    //Other initial states:
    public static int initialGold = 150;
    public static int towerInitialHP = 50;
    public static int castleInitialHP = 100;
    public static int minIdistance = 10;
    public static int minJdistance = 10;


    // Image sizes in pixels:
    public final static int castleWidth = (int) (cellWidth * 1.2);
    public final static int castleHeight = (int) (cellHeight * 1.4);
    public final static int towerWidth = (int) (cellWidth * 1.0);
    public final static int towerHeight = (int) (cellHeight * 1.2);
    public final static int upgradedTowerWidth = (int) (towerWidth * 1.2);
    public final static int upgradedTowerHeight = (int) (towerHeight * 1.4);
    public final static int goldMineWidth = cellWidth;
    public final static int goldMineHeight = cellHeight;
    public final static int treasureChestWidth = cellWidth;
    public final static int treasureChestHeight = cellHeight;
    public final static int obstacleWidth = cellWidth;
    public final static int obstacleHeight = cellHeight;
    // IMPORTANT: Troop size MUST fit into cell (troopWidth <= cellWidth && troopHeight <= cellHeight) otherwise it makes it move in one direction infinitely because it always tries to fit himself into the cell
    public final static int troopWidth = (int)(cellWidth * 0.9);
    public final static int troopHeight = (int)(cellHeight * 0.99);
    // Image paths as strings
    public final static Icon menuBackground0 = new ImageIcon("src/main/resources/images/Title_Image0.png");
    public final static Icon menuBackground1 = new ImageIcon("src/main/resources/images/Title_Image1.png");
    //Passive Buildings
    // Gold Mine
    public final static Image goldMine = new ImageIcon("src/main/resources/images/GoldMine.png").getImage();
    public final static Image treasure = new ImageIcon("src/main/resources/images/Treasure.png").getImage();
    //Obstacles
    public final static Image bush = new ImageIcon("src/main/resources/images/Area/Obstacles/bush2.png").getImage();
    public final static Image rock = new ImageIcon("src/main/resources/images/Area/Obstacles/rock.png").getImage();
    public final static Image stump = new ImageIcon("src/main/resources/images/Area/Obstacles/stump.png").getImage();
    public final static Image[] obstacles = {bush,rock,stump};
    //Grass
    public final static Image grass0 = new ImageIcon("src/main/resources/images/Area/Grass0.png").getImage();
    public final static Image grass1 = new ImageIcon("src/main/resources/images/Area/Grass3.png").getImage();
    public final static Image grass2 = new ImageIcon("src/main/resources/images/Area/Grass2.png").getImage();
    public final static Image grass3 = new ImageIcon("src/main/resources/images/Area/Grass3.png").getImage();
    public final static Image grass4 = new ImageIcon("src/main/resources/images/Area/Grass4.png").getImage();
    public final static Image grass5 = new ImageIcon("src/main/resources/images/Area/Grass5.png").getImage();
    public final static Image grass6 = new ImageIcon("src/main/resources/images/Area/Grass0.png").getImage();
    public final static Image grass7 = new ImageIcon("src/main/resources/images/Area/Grass7.png").getImage();
    public final static Image[] grass = {grass0,grass1,grass2,grass3,grass4,grass5,grass6,grass7};

    public final static Image magBall = new ImageIcon("src/main/resources/images/MagBall.png").getImage();
    public final static int magBallSize = 12;
    public final static Image swordBall = new ImageIcon("src/main/resources/images/SwordBall.png").getImage();
    public final static int swordBallSize = 10;
    public final static Image splashBall = new ImageIcon("src/main/resources/images/SplashBall.png").getImage();
    public final static int splashBallSize = 15;

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

    public static Image getShortRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals(Color.red) ? redShortRangeL1Left : blueShortRangeL1Left;
    }

    public static Image getLongRangeL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals(Color.red) ? redLongRangeL1Left : blueLongRangeL1Left;
    }

    public static Image getSplashL1Left() {
        return Game.getInstance().getCurrentTurn().getColor().equals(Color.red) ? redSplashL1Left : blueSplashL1Left;
    }

    public static Image getMagLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals(Color.red) ? redMagLeftStop : blueMagLeftStop;
    }

    public static Image getSwordLeftStop() {
        return Game.getInstance().getCurrentTurn().getColor().equals(Color.red) ? redSwordLeftStop : blueSwordLeftStop;
    }

    public static Image getGoldMine(){
        return goldMine;
    }

    //Rules
    public static ImageIcon allow_to_build_radius = new ImageIcon("src/main/resources/images/allowed_to_build_radius.jpg");
    public final static String welcomeMessage =         "Welcome to the game of tower defense!" +
                                                        "\nYou can read game rules by clicking \"next\" or just close this thing for noobs and go straightly play";
    public final static String aboutGameGoal =          "Initially you and your opponent have a castle each. The goal of the game is to destroy the enemy castle before your opponent destroys yours!" +
                                                        "\n The game loops between \"Preparation stage\" and \"Fighting stage\"";
    public final static String preparationStage =       "First you start with preparation stage where you and your opponent take turns to prepare for the battle. " +
                                                        "\nWhen it is your turn, you can purchase either different troops or different towers or the gold mine";
    public final static String aboutTroops =            "Goal of the trained troop is to move towards the enemy castle finding shortest path to it. " +
                                                        "\nIf reached the enemy castle without dying then deals its damage and disappears";
    public final static String aboutTroopsAttributes =  "A troop has these attributes:" +
                                                        "\nHP => Health Points" +
                                                        "\nCost => How much gold needed to purchase" +
                                                        "\nMovement speed => How fast troop moves" +
                                                        "\nMovement points => Number of cells the troop can move in one fighting stage" +
                                                        "\nAttack Damage => How much damage it deals to Castle when reaching it";
    public final static String aboutSwordManTroop =     "\nHealth Points: " + swordManHp +
                                                        "\nCost: " + swordManCost +
                                                        "\nMovement Speed: " + swordManSpeed +
                                                        "\nMovement Points: " + swordManMovementPoints +
                                                        "\nAttack Damage: " + swordManAttackDamage;
    public final static String aboutMagTroop =
                                                        "\nHealth Points: " + magHp +
                                                        "\nCost: " + magCost +
                                                        "\nMovement Speed: " + magSpeed +
                                                        "\nMovement Points: " + magMovementPoints +
                                                        "\nAttack Damage: " + magAttackDamage;
    public final static String aboutTowers =            "If troops are needed to attack, towers are there for defending" +
                                                        "\nGoal of the tower is to shoot enemy troops if they are within the tower's \"range\". " +
                                                        "\nIt kills the troop if after the hit, the hp of the troop drops to zero";
    public final static String aboutTowerAttributes =   "A tower has these attributes:" +
                                                        "\nAttack Radius => in what radius the tower is allowed to shoot(read next page for more detail)" +
                                                        "\nCost => How much gold needed to purchase" +
                                                        "\nShot count => Number of shots the tower can do in one fighting stage" +
                                                        "\nReload time => How many seconds tower is not allowed to shoot after the last shot" +
                                                        "\nAttack Damage => How much damage one shot deals to the enemy troop when shooting";
    public final static String aboutTowerRadius =       "Whole concept of the radius in the game is considered to be stairs-like shape" +
                                                        "\nSee example on the left. If we consider that tower radius is 2, then tower will shoot enemy troops only in indicated range";
    public final static String aboutShortRangeTower =   "\nAttack Radius : " + shortRangeTowerRange +
                                                        "\nCost: " + shortRangeCost +
                                                        "\nShot count: " + shortRangeShotCount +
                                                        "\nReload time: " + shortRangeReloadTime +
                                                        "\nAttack Damage: " + shortRangeAttackDamage;
    public final static String aboutLongRangeTower =    "\nAttack Radius : " + longRangeTowerRange +
                                                        "\nCost: " + longRangeCost +
                                                        "\nShot count: " + longRangeShotCount +
                                                        "\nReload time: " + longRangeReloadTime +
                                                        "\nAttack Damage: " + longRangeAttackDamage;
    public final static String aboutSplashTower =       "\nAttack Radius : " + splashTowerRange +
                                                        "\nCost: " + splashCost +
                                                        "\nShot count: " + splashShotCount +
                                                        "\nReload time: " + splashReloadTime +
                                                        "\nAttack Damage: " + splashAttackDamage;
    public final static String aboutBuildingWithinAllyBuildingRange =   "You are not allowed to build too far away from your own buildings." +
                                                                        "\nYou can build maximum in 2 cells away radius from your own building (Remember about stairs-like radius)";
    public final static String aboutBuildingWithinEnemyBuildingRange =  "You are not allowed to build too near to the enemy building" +
                                                                        "\nYou should build at least in 3 cells radius away from your enemy's building (Remember about stairs-like radius)";
    public final static String aboutBuildingWithoutBlockingPath =       "Also you are not allowed to build in a way that the building you try to build will block the path from your castle to enemy castle" +
                                                                        "\nThis means there will always be a path (which makes sense)";
    public final static String aboutEarningGold =                       "So far we talked about spending gold to purchase troops and towers. How about earning gold?" +
                                                                        "\nThere are three ways you can increase your gold: build a goldmine, kill enemy troops, pick up the treasure chest";
    public final static String aboutGoldMines =                         "Besides building towers to defend, you can invest your money into building a goldmine each of which will grant you \n"
                                                                        + goldMineIncomePerRound + " gold at the beginning of every new preparation stage. \n Building one costs: " + goldMineCost + " golds";
    public final static String aboutEarningMoneyFromTroopKills =        "You earn money when your tower kills an enemy troop. The reward for each killed enemy is: " + awardForKillingTroop + " golds";
}
