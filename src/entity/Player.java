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

   private boolean hasKey = false;

   public boolean keyFound = false;
   public boolean potionFound = false;



    public Player(GamePanel gp,Key k){
        this.gp = gp;
        key = k;
        setDefaultValue();
        solidArea = new Rectangle(8,16,32,32);
        solidAdreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getPlayerImage();

    }
    //gets player's image in different directions
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
    //default values for player
    public void setDefaultValue(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "right";
    }
    public void update(){
        if(key.up){
            direction = "up";
            if(!collisionOn){
                y-=speed;
            }

        } else if(key.down){
            direction = "down";
            if(!collisionOn){
                y+=speed;
            }

        } else if(key.left){
            direction = "left";
            if(!collisionOn){
                x-=speed;
            }

        } else if(key.right){
            direction = "right";
            if(!collisionOn){
                x+=speed;
            }
        }
        //check tile collision
        collisionOn = false;
        gp.cT.checkTile(this);
        //check object collision
        //default value equals to true because it is in the player clas
       int objIndex =  gp.cT.checkObject(this,true);
        try{
            pickUpObjects(objIndex);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //interactions between player and objects
    public void pickUpObjects(int i) throws IOException {
        if(i!=999){
            String objectName = gp.obj[i].name;
            switch (objectName){
                case"key":
                gp.playSoundEffect(4);
                hasKey = true;
                gp.obj[i] = null;
                gp.ui.showMessage("Key found!");
                keyFound = true;
                break;
                case "door":
                if(hasKey){
                gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/utilities/dooropen.png"));
                gp.playSoundEffect(3);
                gp.obj[i].collision = false;
                gp.ui.showMessage("Door opened!");
                hasKey = false;
                if(gp.player.solidArea.intersects(gp.obj[i].solidArea)){
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                }
                    }
                break;
                case"speedpotion":
                    gp.playSoundEffect(2);
                    speed+= 2;
                    gp.obj[i] = null;
                    potionFound = true;
                    gp.ui.showMessage("It's the speed up potion!");
                    break;
            }
        }

    }

    //draws out player's image according to different directions
    public void draw(Graphics2D g2){
      //  g2.setColor(Color.WHITE);
       // g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image = null;
        switch (direction) {

            case "up" : {
                ;
                    if(spriteLeft>=1)
                  image = up2;
                    else{
                        image = up;
                    }

            break;
            }
            case "down" :{
                ;
                if(spriteLeft>=1){
                    image = down;
                } else {
                    image = down2;
                }
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
