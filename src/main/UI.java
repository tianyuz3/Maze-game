package main;

import utilities.Object;
import utilities.ObjectKey;
import utilities.SpeedPotion;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40 , arial_80B;
    BufferedImage potionImage;
    public boolean messageOn = false;
    public String message = "";
    BufferedImage keyImage;

    public int messageCounter = 0;

    public boolean gameFinished = false;
    public UI(GamePanel panel){
        gp = panel;
        arial_40 = new Font("Arial",Font.PLAIN, 20);
        arial_80B = new Font("Arial",Font.BOLD,80);
        SpeedPotion potion = new SpeedPotion();
        ObjectKey key = new ObjectKey();
        potionImage = potion.image;
        keyImage =  key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        if(gameFinished){
            g2.setFont(arial_80B);
            g2.setColor(Color.black);
            String text;
            int textLength;
            int centerX = gp.screenWidth/2;
            int centerY = gp.screenHeight/2;
            text = "Game over!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            centerX = gp.screenWidth/2-textLength;
            centerY = gp.screenHeight/2 - gp.tileSize;
            g2.drawString(text,centerX,centerY);
            gp.gameThread = null;
        } else {
            int x = 180;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            if (gp.player.potionFound) {
                g2.drawImage(potionImage, x, 0, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize + 10;
            }
            if (gp.player.keyFound) {
                g2.drawImage(keyImage, x, 0, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize + 10;
            }
            g2.drawString("Utilities Obtained: ", gp.tileSize / 2, gp.tileSize / 2);
            //Message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, 300, 500);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
