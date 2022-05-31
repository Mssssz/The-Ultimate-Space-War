import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemyShip extends Entity{
    private final GamePanel gp;
    private final ShootManager shootManager;
    private final Random rand;
    private int shoot = 0, move=4;

    public EnemyShip(GamePanel gp, int x, int y, int speed, int life, String diretion, ShootManager shootManager) {
        super();
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
        this.direction = diretion;
        this.shootManager = shootManager;

        hitbox = new Rectangle(9,15,36,27);

        rand = new Random();
        this.getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            stands1 = ImageIO.read(new File("src/resources/enemy/Enemy8.png"));
            stands2 = ImageIO.read(new File("src/resources/enemy/Enemy9.png"));
            up1 = ImageIO.read(new File("src/resources/enemy/Enemy6-vissza.png"));
            up2 = ImageIO.read(new File("src/resources/enemy/Enemy7-vissza.png"));
            down1 = ImageIO.read(new File("src/resources/enemy/Enemy0-elore.png"));
            down2 = ImageIO.read(new File("src/resources/enemy/Enemy1-elore.png"));
            left1 = ImageIO.read(new File("src/resources/enemy/Enemy2-bal.png"));
            left2 = ImageIO.read(new File("src/resources/enemy/Enemy3-bal.png"));
            right1 = ImageIO.read(new File("src/resources/enemy/Enemy4-jobb.png"));
            right2 = ImageIO.read(new File("src/resources/enemy/Enemy5-jobb.png"));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Hiba a player kep belovasasakor!");
        }
    }

    public void update(){
        if(shoot == 1){
            shooted++;
        }
        if (move == 0 && y >= 0) {
            y -= speed;
            direction = "up";
        }
        if (move == 1 && y <= (gp.getBlock() * gp.getHeightPanel() / 3)) {
            y += speed;
            direction = "down";
        }
        if (move == 2 && x > 0) {
            x -= speed;
            direction = "left";
        }
        if (move == 3 && x < (gp.getBlock() * gp.getWidthPanel()) - 1.3 * gp.getBlock()) {
            x += speed;
            direction = "right";
        }
        if(move == 4){
            direction = "stands";
        }
        animationCounter++;
        if(animationCounter > 15){
            if(animationNum == 1){ animationNum = 2;}
            else if (animationNum == 2){ animationNum =1;}
            animationCounter = 0;
        }
        shootCounter++;
        if(shootCounter == 15){
            move = rand.nextInt(5);
            shoot = rand.nextInt(2);
            if(shooted > 0){
                shootManager.newShoot(x+((gp.getBlock()/8)*3),y+gp.getBlock(),-8,0);
                shooted = 0;
            }
            shootCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image;
        switch (direction){
            case "up":
                if(animationNum == 1) {
                    image = up1;
                }
                else {
                    image = up2;
                }
                break;
            case "down":
                if(animationNum == 1) {
                    image = down1;
                }
                else {
                    image = down2;
                }
                break;
            case "left":
                if(animationNum == 1) {
                    image = left1;
                }
                else {
                    image = left2;
                }
                break;
            case "right":
                if(animationNum == 1) {
                    image = right1;
                }
                else {
                    image = right2;
                }
                break;
            default:
                if(animationNum == 1) {
                    image = stands1;
                }
                else {
                    image = stands2;
                }
                break;
        }
        if (image == null){
            System.out.println("Nincs kep!");
        }
        g2.drawImage(image,x,y,gp.getBlock(),gp.getBlock(),null);
    }
}
