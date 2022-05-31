import javax.swing.*;

public class Game extends JFrame{
    public Game(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Space ship");

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        setBounds(50,50,gamePanel.getWidthPanel() * gamePanel.getBlock(), gamePanel.getHeightPanel() * gamePanel.getBlock());

        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.startGameThread();
    }
    public static void main(String[] args) {
        new Game();
    }
}
