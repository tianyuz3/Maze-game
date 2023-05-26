package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;
    public BufferedImage up,down,left,right,down2,up2;
    public String direction;

    public int spriteRight = 0;
    public int spriteLeft = 1;
    public String getDirection(){
        return direction;
    }
}
