import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shoot {
    private BufferedImage image, image2;
    private int y;
    private final int type, speed, x;
    private int animationCounter = 0, animationNum = 1;
    private final GamePanel gp;
    private boolean show;
    private final Rectangle hitbox;

    public Shoot(int x, int y, int speed, int type, GamePanel gp, boolean show) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.gp = gp;
        this.show = show;
        this.type = type;

        hitbox = new Rectangle(4,0,4,8);

        getShootImage();
        gp.playSE(2);
    }

    public void getShootImage(){
        try{
            if (type == 1) {
                image = ImageIO.read(new File("src/resources/shoot/Bulets-new0.png"));
                image2 = ImageIO.read(new File("src/resources/shoot/Bulets-new1.png"));
            }
            else {
                image = ImageIO.read(new File("src/resources/shoot/Bulets-new2.png"));
                image2 = ImageIO.read(new File("src/resources/shoot/Bulets-new3.png"));
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Hiba a lovedek kep belovasasakor!");
        }
    }

    public void update(){
        if (y > -10 && y < gp.getBlock()* gp.getHeightPanel() ){
            y-=speed;
        }
        else {
            show = false;
        }
        animationCounter++;
        if(animationCounter > 15){
            if(animationNum == 1){ animationNum = 2;}
            else if (animationNum == 2){ animationNum =1;}
            animationCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        if (show) {
            if(animationNum == 1) {
                g2.drawImage(image, x, y, gp.getBlock() / 4, gp.getBlock() / 2, null);
            }
            else {
                g2.drawImage(image2, x, y, gp.getBlock() / 4, gp.getBlock() / 2, null);
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show){
        this.show = show;
    }

    public int getType() {
        return type;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
