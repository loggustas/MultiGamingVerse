package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.GNOME;

public class Gnome extends Enemy{
    public Gnome(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, GNOME, enemyManager);
    }
}
