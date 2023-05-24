package entity;

import main.GamePanel;
import main.Key;

import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    Key key;

    public Player(GamePanel gp,Key k){
        this.gp = gp;
        key = k;
        setDefaultValue();
    }
    public void setDefaultValue(){
        x = 100;
        y = 100;
        speed = 5;
    }
    public void update(){
        if(key.up){
            y -=speed;
        } else if(key.down){
            y += speed;
        } else if(key.left){
            x -=speed;
        } else if(key.right){
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
    }
}
