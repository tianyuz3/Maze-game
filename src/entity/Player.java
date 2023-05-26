package entity;

import main.GamePanel;
import main.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    Key key;

    public Player(GamePanel gp,Key k){
        this.gp = gp;
        key = k;
        setDefaultValue();
        getPlayerImage();
    }
    public void getPlayerImage(){
        try{
          left = ImageIO.read((getClass().getResourceAsStream("/sprite/left.png")));
          right = ImageIO.read((getClass().getResourceAsStream("/sprite/right.png")));
          up = ImageIO.read((getClass().getResourceAsStream("/sprite/up.png")));
          down = ImageIO.read((getClass().getResourceAsStream("/sprite/down.png")));
          up2 = ImageIO.read((getClass().getResourceAsStream("/sprite/up2.png")));
          down2= ImageIO.read((getClass().getResourceAsStream("/sprite/down2.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDefaultValue(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "right";
    }
    public void update(){
        if(key.up){
            direction = "up";
            y -=speed;
        } else if(key.down){
            direction = "down";
            y += speed;
        } else if(key.left){
            direction = "left";
            x -=speed;
            spriteLeft++;
        } else if(key.right){
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
      //  g2.setColor(Color.WHITE);
       // g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image = null;
        switch (direction) {

            case "up" : {
                ;

                  image = up2;

            break;
            }
            case "down" :{
                ;
                image = down;
                break;
            }
            case "left" : {
                ;
                image = left;
                break;
            }
            case "right" : {
                ;
                image = right;
                break;
            }
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);


    }
}
