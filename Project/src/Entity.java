import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x, y, speed;

    protected BufferedImage stands1, stands2, up1, up2, down1, down2, left1, left2, right1, right2;
    protected String direction;
    protected int animationCounter = 0, animationNum = 1, shootCounter = 0;
    protected int shooted = 0, life = 3;
    protected Rectangle hitbox;

    public Rectangle getHitbox(){
        return hitbox;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isAlive(){return life>0;}

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
