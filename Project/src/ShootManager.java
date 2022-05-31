import java.awt.*;
import java.util.ArrayList;

public class ShootManager {
    private final ArrayList<Shoot> shoots;
    private final GamePanel gp;

    public ShootManager(GamePanel gp) {
        this.gp = gp;
        shoots = new ArrayList<>();
    }

    public void newShoot(int x, int y, int speed, int type){
        Shoot a = new Shoot(x,y,speed,type,gp,true);
        shoots.add(a);
    }

    public void update(){
        shoots.removeIf(i -> !i.isShow());
        for (Shoot i:shoots){
            i.update();
        }
    }

    public void draw(Graphics2D g2){
        for (Shoot i:shoots){
            i.draw(g2);
        }
    }

    public ArrayList<Shoot> getShoots(){
        return shoots;
    }
}
