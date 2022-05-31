import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    private boolean up, down, left, right, shoot, interact;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // play state
        if (gp.getGameState() == gp.getPlayState()) {
            if (code == KeyEvent.VK_W) {
                up = true;
            }
            if (code == KeyEvent.VK_S) {
                down = true;
            }
            if (code == KeyEvent.VK_A) {
                left = true;
            }
            if (code == KeyEvent.VK_D) {
                right = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                shoot = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                System.out.println("Esc play");
                gp.setGameState(gp.getPauseState());
            }
        }

        // pause state
        else if (gp.getGameState() == gp.getPauseState()) {
            if (code == KeyEvent.VK_ESCAPE) {
                System.out.println("Esc pause");
                gp.setGameState(gp.getPlayState());
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getGameState() == gp.getPauseState()) {
                    gp.setGameState(gp.getPlayState());
                }
            }
        }

        // title state
        else if (gp.getGameState() == gp.getTitleState()) {
            if (code == KeyEvent.VK_ENTER) {
                interact = true;
            }
            else if (code == KeyEvent.VK_SPACE) {
                interact = true;
            }
            else {
                if (code == KeyEvent.VK_W) {
                    up = true;
                }
                if (code == KeyEvent.VK_S) {
                    down = true;
                }
            }
        }

        // dead state
        else if (gp.getGameState() == gp.getDeadState()) {
            if (code == KeyEvent.VK_ENTER) {
                interact = true;
            }
            else {
                if (code == KeyEvent.VK_W) {
                    up = true;
                }
                if (code == KeyEvent.VK_S) {
                    down = true;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // play state
        if (code == KeyEvent.VK_SPACE) {
            interact = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            interact = false;
        }
        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }

    public boolean isDown() {
        return down;
    }

    public boolean isInteract() {
        return interact;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isShoot() {
        return shoot;
    }

    public boolean isUp() {
        return up;
    }
}
