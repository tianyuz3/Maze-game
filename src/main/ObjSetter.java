package main;


import utilities.ObjectDoor;
import utilities.ObjectKey;
import utilities.SpeedPotion;

public class ObjSetter {

    GamePanel panel;

    public ObjSetter(GamePanel p){
        panel = p;
    }
    public void setObject(){
        if(!panel.player.keyFound) {
            panel.obj[0] = new ObjectKey();
            panel.obj[0].x = 5 * panel.tileSize;
            panel.obj[0].y = 5 * panel.tileSize;
        }
        panel.obj[1] = new ObjectDoor();
        panel.obj[1].x = 9 * panel.tileSize;
        panel.obj[1].y = 0;
        if(!panel.player.potionFound) {
            panel.obj[2] = new SpeedPotion();
            panel.obj[2].x = 7 * panel.tileSize;
            panel.obj[2].y = 13 * panel.tileSize;
        }


    }
}
