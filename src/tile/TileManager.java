package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel panel;
    Tile[]  tile;

    public TileManager(GamePanel gp){
        panel = gp;
        tile = new Tile[10];
        getTileImage();
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;
       while(col < panel.maxScreenCol && row<panel.maxScreenRow){
           g2.drawImage(tile[0].image,x,y,panel.tileSize, panel.tileSize, null);
           col++;
           x += panel.tileSize;
           if(col == panel.maxScreenCol){
               col = 0;
               x = 0;
               row++;
               y += panel.tileSize;
           }
       }
    }
}
