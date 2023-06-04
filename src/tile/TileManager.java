package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel panel;
   public Tile[] tile;

   public int map[][];

    public TileManager(GamePanel gp) {
        panel = gp;
        tile = new Tile[10];
        map = new int[panel.maxScreenCol][panel.maxScreenRow];
        getTileImage();
        drawMap("/maps/map.txt");

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[0].collision = true;
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(String file) {
        try {
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < panel.maxScreenCol && row < panel.maxScreenRow) {
                String line = reader.readLine();
                while (col < panel.maxScreenCol) {
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    map[col][row] = num;
                    col++;
                }
                if (col == panel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < panel.maxScreenCol && row < panel.maxScreenRow) {
            int tileNum = map[col][row];
            g2.drawImage(tile[tileNum].image, x, y, panel.tileSize, panel.tileSize, null);
            col++;
            x += panel.tileSize;
            if (col == panel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += panel.tileSize;
            }
        }

    }
}


