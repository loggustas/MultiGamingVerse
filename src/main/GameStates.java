package main;

public enum GameStates {

    PLAYING,
    CHOOSE_PLAYING,
    MENU,
    SETTINGS,
    EDIT,
    GAME_OVER,
    YOU_WON,
    ABOUT;

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {
        gameState = state;
    }


}
