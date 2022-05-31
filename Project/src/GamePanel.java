import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // panel size
    private final int originalBlockSize = 16; // 16x16 pixel
    private final int scale = 3; // mennyire skalazza fel
    private final int block = originalBlockSize * scale;
    private final int width = 16;
    private final int height = 12;

    // thread
    private Thread thread;

    // system and entity
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final TileManager tileManager = new TileManager(this);
    private final ShootManager shootManager = new ShootManager(this);
    private final EnemyShipManager esm = new EnemyShipManager(this);
    private final Player player = new Player(this, keyHandler,(block * width)/2 - (block/2),((block * height)/4)*3,5,5, "stand",shootManager);
    private final HitHandler hitHandler = new HitHandler(player,esm,shootManager);
    private final Sound sound = new Sound();

    // game state
    private final UI ui = new UI(this, keyHandler);
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int deadState = 3;

    // score
    private int score;

    public GamePanel(){
        this.setPreferredSize(new Dimension(block * width, block * height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        thread = new Thread(this);
        thread.start();
    }

    public void setupGame() {
        gameState = titleState;
        playMusic(0);
        initGame();
    }

    public void initGame() {
        esm.init(shootManager);
        player.init((block * width)/2 - (block/2),((block * height)/4)*3,5,5, "stand");
        score = 0;
    }

    @Override
    public void run(){
        int FPS = 60;
        long interval = 1000/ FPS; // 0.016666 secundumonket rajzoljon ki
        long time1, time2, time3;
        setupGame();
        while (thread != null){
            time1 = System.nanoTime();

            update();
            repaint();

            time2 = System.nanoTime() - time1;
            time3 = interval - time2 / 1000000;
            //System.out.println(time3);
            try {
                thread.sleep(Math.abs(time3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (gameState == playState) {
            // game screen
            hitHandler.update();
            player.update();
            esm.update();
            shootManager.update();
        }
        ui.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // plus function
        if (gameState == playState) {
            // game screen
            tileManager.draw(g2);
            shootManager.draw(g2);
            esm.draw(g2);
            player.draw(g2);
        }
        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }

    public int getBlock() {
        return block;
    }

    public int getHeightPanel() {
        return height;
    }

    public int getWidthPanel() {
        return width;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getTitleState() {
        return titleState;
    }

    public int getDeadState(){
        return deadState;
    }

    public int getScore() {return score;}
    public void setScore(int score) { this.score = score;}
}
