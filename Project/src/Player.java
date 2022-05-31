import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    private final GamePanel gp;
    private final KeyHandler keyHandler;
    private final ShootManager shootManager;
    private BufferedImage hp;

    public Player(GamePanel gp, KeyHandler keyHandler, int x, int y, int speed, int life, String diretion, ShootManager shootManager) {
        super();
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
        this.direction = diretion;
        this.shootManager = shootManager;

        hitbox = new Rectangle(9,15,36,27);

        this.getPlayerImage();
    }

    public void init(int x, int y,int speed, int life, String diretion){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
        this.direction = diretion;
    }

    public void getPlayerImage(){
        try{
            stands1 = ImageIO.read(new File("src/resources/player/Hajo00.png"));
            stands2 = ImageIO.read(new File("src/resources/player/Hajo01.png"));
            up1 = ImageIO.read(new File("src/resources/player/Hajo02-elore.png"));
            up2 = ImageIO.read(new File("src/resources/player/Hajo03-elore.png"));
            down1 = ImageIO.read(new File("src/resources/player/Hajo08-vissza.png"));
            down2 = ImageIO.read(new File("src/resources/player/Hajo09-vissza.png"));
            left1 = ImageIO.read(new File("src/resources/player/Hajo04-balra.png"));
            left2 = ImageIO.read(new File("src/resources/player/Hajo05-balra.png"));
            right1 = ImageIO.read(new File("src/resources/player/Hajo06-jobbra.png"));
            right2 = ImageIO.read(new File("src/resources/player/Hajo07-jobbra.png"));
            hp = ImageIO.read(new File("src/resources/player/HP.png"));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Hiba a player kep belovasasakor!");
        }

    }

    public void update(){
        if(!isAlive()){
            gp.setGameState(gp.getDeadState());
        }
        if(keyHandler.isShoot()){
            shooted++;
        }
        if(keyHandler.isUp() || keyHandler.isDown() || keyHandler.isLeft() || keyHandler.isRight()) {
            if (keyHandler.isUp() && y > (gp.getBlock() * gp.getHeightPanel() / 3 + gp.getBlock())) {
                y -= speed;
                direction = "up";
            }
            if (keyHandler.isDown() && y < (gp.getBlock() * gp.getHeightPanel()) - 1.9 * gp.getBlock()) {
                y += speed;
                direction = "down";
            }
            if (keyHandler.isLeft() && x > 0) {
                x -= speed;
                direction = "left";
            }
            if (keyHandler.isRight() && x < (gp.getBlock() * gp.getWidthPanel()) - 1.3 * gp.getBlock()) {
                x += speed;
                direction = "right";
            }
        }
        else{
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
            if(shooted > 0){
                shootManager.newShoot(x+((gp.getBlock()/8)*3),y,8,1);
                shooted = 0;
            }
            shootCounter = 0;

            // score
            gp.setScore(gp.getScore()+5);
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
        // draw HP
        for(int i=1; i<=life; i++){
            g2.drawImage(hp,24 * i,10, gp.getBlock()/2, gp.getBlock()/2, null);
        }
    }
}
