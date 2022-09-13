package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.ZOMBIE;

public class Zombie extends Enemy{

    public Zombie(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, ZOMBIE, enemyManager);

    }
}
