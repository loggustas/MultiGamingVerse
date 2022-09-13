package managers;

import Enemies.Enemy;
import Scenes.Playing;
import helperMethods.Constants;
import helperMethods.LoadSave;
import objects.Projectile;
import objects.Tower;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Projectiles.*;
import static helperMethods.Constants.Towers.*;

public class ProjectileManager {


    private int projectileID = 0;

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    private BufferedImage[] projectileImgs, fireballAnimationImages;
    private final int PROJECTILE_AMOUNT = 6, FIREBALL_ANIMATIONS = 7;

    private ArrayList<FireballAnimation> fireballAnimations = new ArrayList<>();
    private Point2D.Float fireballPos;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        projectileImgs = new BufferedImage[PROJECTILE_AMOUNT];

        importProjectileImgs();

    }

    private void importProjectileImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImgs[0] = atlas.getSubimage(0*32, 3*32, 32, 32);

        for(int i = 1 ; i < PROJECTILE_AMOUNT ; i++) {
            projectileImgs[i] = atlas.getSubimage((6+i)*32, 32*3, 32, 32);
        }
        importAnimations(atlas);
    }

    private void importAnimations(BufferedImage atlas) {  //add array for animations and import them here if new animations come
        fireballAnimationImages = new BufferedImage[FIREBALL_ANIMATIONS];

        for(int i = 0 ; i < FIREBALL_ANIMATIONS ; i++) {
            fireballAnimationImages[i] = atlas.getSubimage(i * 32, 3 * 32, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {  //adds a new projectile
        int projectileType = getProjType(t);

        int xDist = (int)(t.getX() - e.getX());
        int yDist = (int)(t.getY() - e.getY());
        int totalDistance = Math.abs(xDist) + Math.abs(yDist);

        float xPercentage = (float) Math.abs(xDist) / totalDistance;
        float yPercentage = 1.0f - xPercentage;

        float xSpeed = xPercentage * Constants.Projectiles.GetSpeed(projectileType);
        float ySpeed = yPercentage * Constants.Projectiles.GetSpeed(projectileType);

        if(t.getX() > e.getX()) {  //if tower is on the right of enemy, projectile must move to left, so speedX is negative
            xSpeed = xSpeed * -1;
        }
        if(t.getY() > e.getY()) {    //if tower is below enemy in y, projectile must move upwards, so negative y
            ySpeed = ySpeed * -1;
        }

        float rotate = 0;
        //add a rotation angle
        if(t.getTowerType() != MARIO && t.getTowerType() != FIREMAN && t.getTowerType() != PACMAN && t.getTowerType() != SONIC) { //only then we need rotation
            float arcValue = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcValue);

            if(xDist < 0) {
                rotate = rotate + 180;
            }
        }



        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, projectileID++, projectileType, xSpeed, ySpeed, t.getDamage(), rotate));
        //for projectile to shoot from the middle of the tower, offset needs to be fixed by adding 16 to both x and y

    }

    private int getProjType(Tower t) {  ///return a projectile type, depending on tower
        switch (t.getTowerType()) {
            case HUNTER:
                return BULLET;
            case MONKEY:
                return BANANA;
            case FIREMAN:
                return FIREBALL;
            case  MARIO:
                return MUSHROOM;
            case PACMAN:
                return PEA;
            case SONIC:
                return RING;
            default:
                return 0;
        }
    }

    public void update() {  //will move every projectile every UPDATE
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                p.move();
                if(isProjectileHittingEnemy(p)) {  //checks if this projectile hits any enemy
                    //enemy was hit it isProjectileHittingEnemy method
                    p.setActive(false); //set projectile as inactive
                    if(p.getProjectileType() == FIREBALL) { ///////////////////CHECK IF PROJECTILE IS ANIMATED
                        fireballAnimations.add(new FireballAnimation(p.getPos()));  //add instance of animation to the list
                        splashDamageOnEnemies(p);
                    }
                } else if(isProjectileOutsideBounds(p)){
                    //nothing
                    p.setActive(false);
                }
            }
        }

        for(FireballAnimation a : fireballAnimations) {  //looping through all fireball Animation explosions
            if(a.getIndex() < FIREBALL_ANIMATIONS) {    //if index larger tha animation amount, no need to work with it
                a.update();     //updating status of each of them
            }
        }

    }

    private boolean isProjectileOutsideBounds(Projectile p) {
        if(p.getPos().x >= 0) {
            if(p.getPos().x <= 640) {
                if(p.getPos().y >= 0) {
                    if(p.getPos().y <= 640) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void splashDamageOnEnemies(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.isAlive()) {
                float radius = 48.0f;

                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());

                float distanceToEnemy = (float)Math.hypot(xDist, yDist);

                if(distanceToEnemy <= radius) {
                    e.hurt(p.getDmg());
                }
            }

        }
    }

    private boolean isProjectileHittingEnemy(Projectile p) {
        for(Enemy e : playing.getEnemyManager().getEnemies()) { //loops through enemies and checks if projectile hits any enemy
            if(e.isAlive()) {
                if(e.getBounds().contains(p.getPos())) {  //check if projectile is inside the ENEMY BOUNDS
                    e.hurt(p.getDmg());  //hurt enemy if yes
                    return true;
                }
            }

        }
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for(Projectile p : projectiles) {   //drawing projectiles in their coordinates
            if(p.isActive()) {  //if projectile is active hit the enemy
                if(p.getProjectileType() != MUSHROOM && p.getProjectileType() != FIREBALL) { //if need rotation
                    g2d.translate((int)p.getPos().x, (int)p.getPos().y);
                    g2d.rotate(Math.toRadians(p.getRotation()));
                    g2d.drawImage(projectileImgs[p.getProjectileType()], -16, -16 , null);
                    g2d.rotate(-Math.toRadians(p.getRotation()));
                    g2d.translate(-(int)p.getPos().x, -(int)p.getPos().y);

                } else {  // when don't need rotation
                    g.drawImage(projectileImgs[p.getProjectileType()], (int)p.getPos().x, (int)p.getPos().y , null);
                }

            }
        }

        drawFireballAnimation(g2d);
    }

    private void drawFireballAnimation(Graphics2D g2d) {
        for(FireballAnimation a : fireballAnimations) {
            if(a.getIndex() < FIREBALL_ANIMATIONS) { //if animationIndex is larger than animation number, don't draw
                g2d.drawImage(fireballAnimationImages[a.getIndex()], (int)a.getPos().x - 16, (int)a.getPos().y - 16 , null);
            }
        }
    }

    public class FireballAnimation {

        private Point2D.Float pos;

        private int fireballTick, fireballIndex;     //IN UPDATES
        private final int fireballAnimationSpeed = 10;  //the duration of one animation in UPDATES

        public FireballAnimation(Point2D.Float pos) {
            this.pos = pos;
        }
        public void update() {

                fireballTick++;
                if(fireballTick >= fireballAnimationSpeed) {
                    fireballTick = 0;
                    fireballIndex++;
                }
        }

        public int getIndex() {
            return fireballIndex;
        }
        public Point2D.Float getPos() {
            return pos;
        }
    }

    public void reset() {
        projectiles.clear();
        fireballAnimations.clear();

        projectileID = 0;
    }
}
