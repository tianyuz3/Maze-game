package main;

import utilities.Object;
import utilities.ObjectKey;
import utilities.SpeedPotion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;

    Graphics2D g2;

    BufferedImage potionImage;

    public boolean messageOn = false;
    public String message = "";
    BufferedImage keyImage;

    public int messageCounter = 0;

    public boolean gameFinished = false;

    public int commandNum = 0;

    public UI(GamePanel panel) {
        gp = panel;
        arial_40 = new Font("Arial", Font.PLAIN, 20);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        SpeedPotion potion = new SpeedPotion();
        ObjectKey key = new ObjectKey();
        potionImage = potion.image;
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) throws IOException {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        if (gp.gameState == gp.playState) {
            if (gameFinished) {
                g2.setFont(arial_80B);
                g2.setColor(Color.black);
                String text;
                int textLength;
                int centerX = gp.screenWidth / 2;
                int centerY = gp.screenHeight / 2;
                text = "Game over!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                centerX = gp.screenWidth / 2 - textLength;
                centerY = gp.screenHeight / 2 - gp.tileSize;
                g2.drawString(text, centerX+100, centerY-200);
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
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
    }

    public void drawTitleScreen() throws IOException {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        ///title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D maze game";
        int x = getCenterXText(text);
        int y = gp.tileSize * 3;
        //shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        x = 80;
        y = 200;
        //images that are displayed on the main menu
        BufferedImage map = ImageIO.read(getClass().getResourceAsStream("/maps/braid.png"));
        g2.drawImage(map, x, y, 250, 250, null);
        BufferedImage map2 = ImageIO.read(getClass().getResourceAsStream("/maps/bias.png"));
        g2.drawImage(map2, x + 500, y, 250, 250, null);
        g2.drawImage(gp.player.right, 400, y + 100, 100, 100, null);
        BufferedImage questionMark = ImageIO.read((getClass().getResourceAsStream("/maps/questionMark.png")));
        g2.drawImage(questionMark, 450, y, 120, 140, null);
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        g2.setColor(new Color(153, 101, 23));
        g2.setColor(Color.WHITE);
        text = "new game";
        x = getCenterXText(text);
        y = gp.screenHeight / 2 + 100;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        text = "load game";
        g2.drawString(text, x, y + 100);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y + 100);
        }
        text = "Help";
        x = getCenterXText(text);
        g2.drawString(text, x, y + 200);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y + 200);
        }
        text = "Exit";
        x = getCenterXText(text);
        g2.drawString(text, x, y + 300);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y + 300);
        }
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();

        }
    }






    public void drawOptionScreen(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize *10;
        drawSubwindow(frameX,frameY,frameWidth,frameHeight);


    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "GAME PAUSED";
        int x = getCenterXText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public int getCenterXText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x= gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawSubwindow(int x, int y, int width ,int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }



}
