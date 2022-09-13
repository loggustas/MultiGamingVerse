package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;  //stores two float values(useful because have a lot of good methods)
    private int id, projectileType;
    private boolean active = true;
    private float xSpeed, ySpeed, rotation;
    private int dmg;


    public Projectile(float x, float y, int id, int projectileType, float xSpeed, float ySpeed, int dmg, float rotation) {
        this.pos = new Point2D.Float(x, y);
        this.id = id;
        this.projectileType = projectileType;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotation;
    }

    public void move() { //will be called every UPDATE
        pos.x = pos.x + xSpeed;
        pos.y = pos.y + ySpeed;
    }


    //setters and getters
    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRotation() {
        return rotation;
    }
}
