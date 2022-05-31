import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyShipManager {
    private final ArrayList<EnemyShip> ships;
    private final GamePanel gp;
    private ShootManager shootManager;
    private int level = 1;

    public EnemyShipManager(GamePanel gp) {
        this.gp = gp;
        ships = new ArrayList<>();
    }

    public void init(ShootManager shootManager){
        this.shootManager = shootManager;
        level = 1;
        ships.clear();
        newEnemyShip((gp.getBlock() * gp.getWidthPanel())/2 - (gp.getBlock()/2),((gp.getBlock() * gp.getHeightPanel())/4),5,3,"stand", shootManager);
    }

    public void nextLevel(){
        level++;
        Random r = new Random();
        for(int i=0; i<level; i++){
            newEnemyShip(r.nextInt(gp.getBlock() * gp.getWidthPanel() - gp.getBlock()),r.nextInt(gp.getBlock() * gp.getHeightPanel() / 3),5,3,"stand", shootManager);
        }
    }

    public void newEnemyShip(int x, int y, int speed, int life, String diretion, ShootManager shootManager){
        EnemyShip enemyShip = new EnemyShip(gp,x,y,speed,life,diretion,shootManager);
        ships.add(enemyShip);
    }

    public void update(){
        if(ships.size() == 0){
            nextLevel();
        }
        int shipNumber = ships.size();
        ships.removeIf(i-> !i.isAlive());
        if(shipNumber > ships.size()){
            gp.setScore(gp.getScore() + 1000);
        }
        for (EnemyShip i:ships){
            i.update();
        }
    }

    public void draw(Graphics2D g2){
        for (EnemyShip i:ships){
            i.draw(g2);
        }
    }

    public ArrayList<EnemyShip> getShips() {
        return ships;
    }
}
