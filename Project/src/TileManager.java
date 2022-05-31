import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    private final GamePanel gp;
    private final Tile[] tile;
    int[][] map;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[6];
        map = new int[gp.getHeightPanel()][gp.getWidthPanel()];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].img = ImageIO.read(new File("src/resources/map/ur.png"));
            tile[1] = new Tile();
            tile[1].img = ImageIO.read(new File("src/resources/map/ur1.png"));
            tile[2] = new Tile();
            tile[2].img = ImageIO.read(new File("src/resources/map/ur2.png"));
            tile[3] = new Tile();
            tile[3].img = ImageIO.read(new File("src/resources/map/ur3.png"));
            tile[4] = new Tile();
            tile[4].img = ImageIO.read(new File("src/resources/map/nap.png"));
            tile[5] = new Tile();
            tile[5].img = ImageIO.read(new File("src/resources/map/jupiter.png"));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Hiba a map kep belovasasakor!");
        }
    }
    public void loadMap(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("resources/map/map.txt");
            assert inputStream != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            for (int i =0; i<gp.getHeightPanel(); i++){
                String line = br.readLine();
                for (int j=0; j<gp.getWidthPanel(); j++){
                    String[] a = line.split(" ");
                    map[i][j] = Integer.parseInt(a[j]);
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
            br.close();
        }catch (Exception ignored){
            System.out.println("Hiba a map betoltesekor!");
        }
    }
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].img,0,0,gp.getBlock(), gp.getBlock(),null);
        int x=0, y;
        while (x < gp.getWidthPanel()){
            y= 0;
            while (y < gp.getHeightPanel()) {
                int tileNume = map[y][x];
                    g2.drawImage(tile[tileNume].img, x*gp.getBlock(), y*gp.getBlock(), gp.getBlock(), gp.getBlock(), null);
                y++;
            }
            x++;
        }
    }
}
