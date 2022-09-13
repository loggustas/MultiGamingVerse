package helperMethods;


public class Constants {

    public static class Level{
        public static final int AMOUNT = 10;

        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;
        public static final int NINE = 9;
        public static final int TEN = 10;


        public static int LEVEL = 1;

        public static void setLEVEL (int level){
            LEVEL = level;
        }
    }

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class Tiles{
        public static final int WATER = 0;
        public static final int GRASS = 1;
        public static final int ROAD = 2;
    }
    public static class Enemies{
        public static final int GNOME = 0;      //very weak enemy
        public static final int GUARD = 1;      //speedy
        public static final int ZOMBIE = 2;     //tank
        public static final int ALIEN = 3;      //tank and speedy
        public static final int GHOST = 4;
        public static final int CREEPER = 5;

        public static int GetGoldReward(int enemyType) {
            switch (enemyType) {

                case GNOME:
                    return 5;
                case GUARD:
                    return 8;
                case ZOMBIE:
                    return 10;
                case ALIEN:
                    return 20;
                case GHOST:
                    return 30;
                case CREEPER:
                    return 50;
            }
            return 0;
        }
        public static int GetHPLost(int enemyType) {
            switch (enemyType) {

                case GNOME:
                    return 10;
                case GUARD:
                    return 15;
                case ZOMBIE:
                    return 25;
                case ALIEN:
                    return 30;
                case GHOST:
                    return 50;
                case CREEPER:
                    return 75;
            }
            return 0;
        }
        public static float GetSpeed(int enemyType) {
            switch (enemyType) {

                case GNOME:
                    return 0.4f;
                case GUARD:
                    return 0.7f;
                case ZOMBIE:
                    return 0.4f;
                case ALIEN:
                    return 0.55f;
                case GHOST:
                    return 0.55f;
                case CREEPER:
                    return 0.4f;
            }
                    return 0;
            }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {

                case GNOME:
                    return 100;
                case GUARD:
                    return 180;
                case ZOMBIE:
                    return 850;
                case ALIEN:
                    return 650;
                case GHOST:
                    return 1750;
                case CREEPER:
                    return 2500;
            }
            return 0;
        }
    }

    public static class Towers{
        public static final int HUNTER = 0;
        public static final int MONKEY = 1;
        public static final int FIREMAN = 2;
        public static final int MARIO = 3;
        public static final int PACMAN = 4;
        public static final int SONIC = 5;

        public static int GetTowerPrice(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return 100;
                case MONKEY:
                    return 50;
                case FIREMAN:
                    return 250;
                case MARIO:
                    return 400;
                case PACMAN:
                    return 500;
                case SONIC:
                    return 800;
                default:
                    return 0;
            }
        }

        public static String GetName(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return "Hunter";
                case MONKEY:
                    return "Monkey";
                case FIREMAN:
                    return "Fireman";
                case MARIO:
                    return "Mario";
                case PACMAN:
                    return "PacMan";
                case SONIC:
                    return "Sonic";
                default:
                    return "";
            }
        }
        public static String GetSpecification(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return "(Range)";
                case MONKEY:
                    return "(Fast)";
                case FIREMAN:
                    return "(Splash dmg)";
                case MARIO:
                    return "(Fast)";
                case PACMAN:
                    return "(Fast/Range)";
                case SONIC:
                    return "(High DPS)";
                default:
                    return "";
            }
        }

        public static int GetStartDmg(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return 40;
                case MONKEY:
                    return 15;
                case FIREMAN:
                    return 30;
                case MARIO:
                    return 40;
                case PACMAN:
                    return 40;
                case SONIC:
                    return 70;
                default:
                    return 0;
            }
        }

        public static float GetDefaultAttackSpeed(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return 80;
                case MONKEY:
                    return 40;
                case FIREMAN:
                    return 50;
                case MARIO:
                    return 30;
                case PACMAN:
                    return 30;
                case SONIC:
                    return 25;
                default:
                    return 0;
            }
        }

        public static float GetDefaultRange(int towerType) {
            switch (towerType) {
                case HUNTER:
                    return 150;
                case MONKEY:
                    return 80;
                case FIREMAN:
                    return 110;
                case MARIO:
                    return 110;
                case PACMAN:
                    return 140;
                case SONIC:
                    return 110;
                default:
                    return 0;
            }
        }

    }
    public static class Projectiles {
        public static final int BULLET = 3;
        public static final int BANANA = 2;
        public static final int FIREBALL = 0;
        public static final int MUSHROOM = 1;
        public static final int PEA = 4;
        public static final int RING = 5;

        public static float GetSpeed(int projectileType) {
            switch (projectileType) {
                case BULLET:
                    return 11f;
                case BANANA:
                    return 7f;
                case FIREBALL:
                    return 7f;
                case MUSHROOM:
                    return 7f;
                case PEA:
                    return 7f;
                case RING:
                    return 7f;
                default:
                    return 0f;
            }
        }
    }
}
