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
    public final static int shortRangeTowerRange = 2;
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
    public final static int mapHeightInCells = 10;
    public final static int mapWidthInCells = 15;
    public final static int cellWidth = 40;
    public final static int cellHeight = cellWidth;
    public final static int mapWidthInPixels = mapWidthInCells * cellWidth;
    public final static int mapHeightInPixels = mapHeightInCells * cellHeight;
    public final static int rightPanelWidth = 350;
    //Game Settings
    public final static int fps = 10; //how many times to repaint the map in one second. Animation quality depends on this


    //Other initial states:
    public static int initialGold = 969696;
    public static int towerInitialHP = 1500;
    public static int castleInitialHP = 1500;



    // image paths as strings

    //Obstacles
    public final static String bush = "src/resources/images/Area/Obstacles/bush2.png";
    public final static String rock = "src/resources/images/Area/Obstacles/rock.png";
    public final static String stump = "src/resources/images/Area/Obstacles/stump.png";
    //Grass
    public final static String grass = "src/resources/images/Area/Grass0.png";
    public final static String grass1 = "src/resources/images/Area/Grass1.png";
    public final static String grass2 = "src/resources/images/Area/Grass2.png";
    public final static String grass3 = "src/resources/images/Area/Grass3.png";
    public final static String grass4 = "src/resources/images/Area/Grass4.png";
    public final static String grass5 = "src/resources/images/Area/Grass5.png";
    public final static String grass6 = "src/resources/images/Area/Grass6.png";
    public final static String grass7 = "src/resources/images/Area/Grass7.png";


   //Blue player
     //Buildings
       //Castle
       public final static String blueCastle ="src/resources/images/Blue/Buildings/Castle/Blue.png";

       // LongRange
       public final static String blueLongRangeL1Left ="src/resources/images/Blue/Buildings/LongRange/L1/Left.png";
       public final static String blueLongRangeL1Right ="src/resources/images/Blue/Buildings/LongRange/L1/Right.png";
       public final static String blueLongRangeL2Right ="src/resources/images/Blue/Buildings/LongRange/L2/Right.png";
       public final static String blueLongRangeL2Left ="src/resources/images/Blue/Buildings/LongRange/L2/Left.png";
       //ShortRange
       public final static String blueShortRangeL1Left ="src/resources/images/Blue/Buildings/ShortRange/L1/Left.png";
       public final static String blueShortRangeL1Right ="src/resources/images/Blue/Buildings/ShortRange/L1/Right.png";
       public final static String blueShortRangeL2Right ="src/resources/images/Blue/Buildings/ShortRange/L2/Right.png";
       public final static String blueShortRangeL2Left ="src/resources/images/Blue/Buildings/ShortRange/L2/Left.png";
       //Splash
       public final static String blueSplashL1Left ="src/resources/images/Blue/Buildings/Splash/L1/Left.png";
       public final static String blueSplashL1Right ="src/resources/images/Blue/Buildings/Splash/L1/Right.png";
       public final static String blueSplashL2Right ="src/resources/images/Blue/Buildings/Splash/L2/Right.png";
       public final static String blueSplashL2Left ="src/resources/images/Blue/Buildings/Splash/L2/Left.png";

    //Troop
      //Mag
        //Back
        public final static String blueMagBackStop ="src/resources/images/Blue/Troop/Mag/Back/Stop.png";
        public final static String blueMagBackW1 ="src/resources/images/Blue/Troop/Mag/Back/V1.png";
        public final static String blueMagBackW2 ="src/resources/images/Blue/Troop/Mag/Back/V2.png";

        //Left
        public final static String blueMagLeftStop ="src/resources/images/Blue/Troop/Mag/Left/Stop.png";
        public final static String blueMagLeftW1 ="src/resources/images/Blue/Troop/Mag/Left/V1.png";
        public final static String blueMagLeftW2 ="src/resources/images/Blue/Troop/Mag/Left/V2.png";

        //Right
        public final static String blueMagRightStop ="src/resources/images/Blue/Troop/Mag/Right/Stop.png";
        public final static String blueMagRightW1 ="src/resources/images/Blue/Troop/Mag/Right/V1.png";
        public final static String blueMagRightW2 ="src/resources/images/Blue/Troop/Mag/Right/V2.png";

        //Sword
           //Back
        public final static String blueSwordBackStop ="src/resources/images/Blue/Troop/Sword/Back/Stop.png";
        public final static String blueSwordBackW1 ="src/resources/images/Blue/Troop/Sword/Back/V1.png";
        public final static String blueSwordBackW2 ="src/resources/images/Blue/Troop/Sword/Back/V2.png";

            //Left
            public final static String blueSwordLeftStop ="src/resources/images/Blue/Troop/Sword/Left/Stop.png";
            public final static String blueSwordLeftW1 ="src/resources/images/Blue/Troop/Sword/Left/V1.png";
            public final static String blueSwordLeftW2 ="src/resources/images/Blue/Troop/Sword/Left/V2.png";

            //Right
            public final static String blueSwordRightStop ="src/resources/images/Blue/Troop/Sword/Right/Stop.png";
            public final static String blueSwordRightW1 ="src/resources/images/Blue/Troop/Sword/Right/V1.png";
            public final static String blueSwordRightW2 ="src/resources/images/Blue/Troop/Sword/Right/V2.png";










    //Red player
    //Buildings
    //Castle
    public final static String redCastle ="src/resources/images/Red/Buildings/Castle/Red.png";

    // LongRange
    public final static String redLongRangeL1Left ="src/resources/images/Red/Buildings/LongRange/L1/Left.png";
    public final static String redLongRangeL1Right ="src/resources/images/Red/Buildings/LongRange/L1/Right.png";
    public final static String redLongRangeL2Right ="src/resources/images/Red/Buildings/LongRange/L2/Right.png";
    public final static String redLongRangeL2Left ="src/resources/images/Red/Buildings/LongRange/L2/Left.png";
    //ShortRange
    public final static String redShortRangeL1Left ="src/resources/images/Red/Buildings/ShortRange/L1/Left.png";
    public final static String redShortRangeL1Right ="src/resources/images/Red/Buildings/ShortRange/L1/Right.png";
    public final static String redShortRangeL2Right ="src/resources/images/Red/Buildings/ShortRange/L2/Right.png";
    public final static String redShortRangeL2Left ="src/resources/images/Red/Buildings/ShortRange/L2/Left.png";
    //Splash
    public final static String redSplashL1Left ="src/resources/images/Red/Buildings/Splash/L1/Left.png";
    public final static String redSplashL1Right ="src/resources/images/Red/Buildings/Splash/L1/Right.png";
    public final static String redSplashL2Right ="src/resources/images/Red/Buildings/Splash/L2/Right.png";
    public final static String redSplashL2Left ="src/resources/images/Red/Buildings/Splash/L2/Left.png";
    //Troop
    //Mag
    //Back
    public final static String redMagBackStop ="src/resources/images/Red/Troop/Mag/Back/Stop.png";
    public final static String redMagBackW1 ="src/resources/images/Red/Troop/Mag/Back/V1.png";
    public final static String redMagBackW2 ="src/resources/images/Red/Troop/Mag/Back/V2.png";

    //Left
    public final static String redMagLeftStop ="src/resources/images/Red/Troop/Mag/Left/Stop.png";
    public final static String redMagLeftW1 ="src/resources/images/Red/Troop/Mag/Left/V1.png";
    public final static String redMagLeftW2 ="src/resources/images/Red/Troop/Mag/Left/V2.png";

    //Right
    public final static String redMagRightStop ="src/resources/images/Red/Troop/Mag/Right/Stop.png";
    public final static String redMagRightW1 ="src/resources/images/Red/Troop/Mag/Right/V1.png";
    public final static String redMagRightW2 ="src/resources/images/Red/Troop/Mag/Right/V2.png";

    //Sword
    //Back
    public final static String redSwordBackStop ="src/resources/images/Red/Troop/Sword/Back/Stop.png";
    public final static String redSwordBackW1 ="src/resources/images/Red/Troop/Sword/Back/V1.png";
    public final static String redSwordBackW2 ="src/resources/images/Red/Troop/Sword/Back/V2.png";

    //Left
    public final static String redSwordLeftStop ="src/resources/images/Red/Troop/Sword/Left/Stop.png";
    public final static String redSwordLeftW1 ="src/resources/images/Red/Troop/Sword/Left/V1.png";
    public final static String redSwordLeftW2 ="src/resources/images/Red/Troop/Sword/Left/V2.png";

    //Right
    public final static String redSwordRightStop ="src/resources/images/Red/Troop/Sword/Right/Stop.png";
    public final static String redSwordRightW1 ="src/resources/images/Red/Troop/Sword/Right/V1.png";
    public final static String redSwordRightW2 ="src/resources/images/Red/Troop/Sword/Right/V2.png";

}
