public class HitHandler {
    private final Player player;
    private final EnemyShipManager esm;
    private final ShootManager shootManager;

    public HitHandler(Player player, EnemyShipManager esm, ShootManager shootManager) {
        this.player = player;
        this.esm = esm;
        this.shootManager = shootManager;
    }

    public void update(){
        for (Shoot i:shootManager.getShoots()){
            hit(i,player,0);
            for(EnemyShip j: esm.getShips()){
                hit(i,j,1);
            }
        }
    }

    public void hit (Shoot s, Entity e, int who){
        // this type of bullet hit the player, other hit the enemies
        if(who == 0){
            if(s.getType() == 0){
                if(e.getY() + e.getHitbox().y <= s.getY() + s.getHitbox().y + s.getHitbox().height &&
                        e.getX() + e.getHitbox().x < s.getX() + s.getHitbox().x + s.getHitbox().width && e.getX() + e.getHitbox().x + e.getHitbox().width > s.getX() + s.getHitbox().x){
                    System.out.println("Player got a hit front!");
                    s.setShow(false);
                    e.setLife(e.getLife() - 1);
                }
            }
        }
        else{
            if(s.getType() != 0){
                if(e.getY() + e.getHitbox().y + e.getHitbox().height >= s.getY() + s.getHitbox().y &&
                        e.getX() + e.getHitbox().x < s.getX() + s.getHitbox().x + s.getHitbox().width && e.getX() + e.getHitbox().x + e.getHitbox().width > s.getX() + s.getHitbox().x){
                    System.out.println("Enemy got a hit front!");
                    s.setShow(false);
                    e.setLife(e.getLife() - 1);
                }
            }
        }

    }
}
