package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.ALIEN;

public class Alien extends Enemy{

    public Alien(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, ALIEN, enemyManager);

    }
}
