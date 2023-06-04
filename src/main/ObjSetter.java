package main;


import utilities.ObjectDoor;
import utilities.ObjectKey;

public class ObjSetter {

    GamePanel panel;

    public ObjSetter(GamePanel p){
        panel = p;
    }
    public void setObject(){
        panel.obj[0] = new ObjectKey();
        panel.obj[0].x = 14 *panel.tileSize;
        panel.obj[0].y =  panel.tileSize;
        panel.obj[1] = new ObjectDoor();
        panel.obj[1].x = 13 * panel.tileSize;
        panel.obj[1].y = 0;

    }
}
