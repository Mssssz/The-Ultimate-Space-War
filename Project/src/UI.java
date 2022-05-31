import java.awt.*;

public class UI {
    GamePanel gp;
    KeyHandler keyHandler;
    private int actualCommand = 0, stepCounter = 0;
    private final int commandNr = 1;
    private int up = 0, down = 0, interact = 0;

    public UI(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
    }

    public void init(){
        up = 0;
        down = 0;
        interact = 0;
        stepCounter = 0;
    }

    public void update(){
        if(gp.getGameState() == gp.getTitleState()){
            updateTitleState();
        }
        if(gp.getGameState() == gp.getDeadState()){
            updateDeadState();
        }
    }

    public void updateDeadState(){
        if(keyHandler.isUp()){
            up++;
        }
        if(keyHandler.isDown()){
            down++;
        }
        if(keyHandler.isInteract()){
            interact++;
        }
        stepCounter++;
        if(stepCounter == 20){
            if(interact > 0){
                //
                if(actualCommand == 0) {
                    System.out.println("New game");
                    gp.initGame();
                    init();
                    gp.setGameState(gp.getPlayState());
                }
                if(actualCommand == 1){
                    init();
                    gp.setGameState(gp.getTitleState());
                }
            }
            else{
                if(up > 0){
                    actualCommand--;
                    if(actualCommand < 0){
                        actualCommand = commandNr;
                    }
                }
                else if(down > 0){
                    actualCommand++;
                    if(actualCommand > commandNr){
                        actualCommand = 0;
                    }
                }
            }
            up = 0;
            down = 0;
            interact = 0;
            stepCounter = 0;
        }
    }

    public void updateTitleState(){
        if(keyHandler.isUp()){
            up++;
        }
        if(keyHandler.isDown()){
            down++;
        }
        if(keyHandler.isInteract()){
            interact++;
        }
        stepCounter++;
        if(stepCounter == 20){
            if(interact > 0){
                //
                if(actualCommand == 0) {
                    gp.initGame();
                    init();
                    gp.setGameState(gp.getPlayState());
                }
                if(actualCommand == 1){
                    System.exit(0);
                }
            }
            else{
                if(up > 0){
                    actualCommand--;
                    if(actualCommand < 0){
                        actualCommand = commandNr;
                    }
                }
                else if(down > 0){
                    actualCommand++;
                    if(actualCommand > commandNr){
                        actualCommand = 0;
                    }
                }
            }
            up = 0;
            down = 0;
            interact = 0;
            stepCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        if (gp.getGameState() == gp.getPlayState()){
            drawPlayScreen(g2);
        }
        if(gp.getGameState() == gp.getTitleState()){
            drawTitleScreen(g2);
        }
        if(gp.getGameState() == gp.getDeadState()){
            drawDeadScreen(g2);
        }
        if(gp.getGameState() == gp.getPauseState()){
            drawPauseScreen(g2);
        }
    }

    public void drawPlayScreen(Graphics2D g2){
        // Score
        String score = "Score: " + gp.getScore();
        g2.setFont(new Font("Impact", Font.PLAIN, 24));
        int x = gp.getWidthPanel() + (gp.getBlock() * 12);
        int y = 30;
        g2.setColor(Color.YELLOW);
        g2.drawString(score,x,y);
    }

    public void drawDeadScreen(Graphics2D g2){
        // Title
        String text = "YOU ARE DEAD";
        g2.setFont(new Font("Impact", Font.PLAIN, 64));
        int x = getXinCenter(text, g2);
        int y = gp.getBlock()*3;
        g2.setColor(Color.YELLOW);
        g2.drawString(text,x,y);

        // Score
        String score = "Score: " + gp.getScore();
        g2.setFont(new Font("Impact", Font.PLAIN, 50));
        x = getXinCenter(score, g2);
        y = gp.getBlock()*4;
        g2.drawString(score,x,y);

        // Menu
        String restart = "Restart";
        g2.setFont(new Font("Impact", Font.PLAIN, 50));
        if(actualCommand == 0) {
            g2.setColor(Color.WHITE);
        }
        else{
            g2.setColor(Color.YELLOW);
        }
        x = getXinCenter(restart,g2);
        y = gp.getBlock()*6;
        g2.drawString(restart,x,y);

        String home = "Home";
        if(actualCommand == 1) {
            g2.setColor(Color.WHITE);
        }
        else{
            g2.setColor(Color.YELLOW);
        }
        x = getXinCenter(home,g2);
        y = gp.getBlock()*7;
        g2.drawString(home,x,y);
    }

    public void drawTitleScreen(Graphics2D g2){
        // Title
        String text = "The Ultimate Space War";
        g2.setFont(new Font("Impact", Font.PLAIN, 64));
        int x = getXinCenter(text, g2);
        int y = gp.getBlock()*5/2;
        g2.setColor(Color.YELLOW);
        g2.drawString(text,x,y);

        // Menu
        String start = "Start";
        g2.setFont(new Font("Impact", Font.PLAIN, 50));
        if(actualCommand == 0) {
            g2.setColor(Color.WHITE);
        }
        else{
            g2.setColor(Color.YELLOW);
        }
        x = getXinCenter(start,g2);
        y = gp.getBlock()*6;
        g2.drawString(start,x,y);

        String quit = "Quit";
        if(actualCommand == 1) {
            g2.setColor(Color.WHITE);
        }
        else{
            g2.setColor(Color.YELLOW);
        }
        x = getXinCenter(quit,g2);
        y = gp.getBlock()*7;
        g2.drawString(quit,x,y);
    }

    public void drawPauseScreen(Graphics2D g2){
        String text = "Paused";
        g2.setFont(new Font("Impact", Font.PLAIN, 40));
        g2.setColor(Color.YELLOW);
        int x = getXinCenter(text, g2);
        int y = (gp.getBlock() * gp.getHeightPanel())/2;
        g2.drawString(text,x,y);
    }

    public int getXinCenter(String text,Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return (gp.getBlock()) * gp.getWidthPanel() / 2 - gp.getBlock() / 4 - length / 2;
    }
}
