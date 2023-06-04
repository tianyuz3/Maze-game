package main;

import entity.Entity;

public class CollisionTester {
    GamePanel panel;
    public CollisionTester(GamePanel p){
        panel = p;
    }
    public void checkTile(Entity entity){
        int rightWorldX = entity.x + entity.solidArea.x;
        int leftWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int topWorldY = entity.y + entity.solidArea.y;
        int bottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int leftCol =  leftWorldX/panel.tileSize;
        int rightCol = rightWorldX/panel.tileSize;
        int topRow = topWorldY/ panel.tileSize;
        int bottomRow = bottomWorldY/ panel.tileSize;

        int tileNum1, tileNum2;
        switch(entity.direction){
            case "up":
                topRow = (topWorldY - entity.speed)/panel.tileSize;
                tileNum1 = panel.tileM.map[leftCol][topRow];
                tileNum2 = panel.tileM.map[rightCol][topRow];
                if(panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
               bottomRow  = (bottomWorldY + entity.speed)/panel.tileSize;
                tileNum1 = panel.tileM.map[leftCol][bottomRow];
                tileNum2 = panel.tileM.map[rightCol][bottomRow];
                if(panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                leftCol = (leftWorldX - entity.speed)/panel.tileSize;
                tileNum1 = panel.tileM.map[leftCol][topRow];
                tileNum2 = panel.tileM.map[leftCol][bottomRow];
                if(panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                rightCol = (rightWorldX + entity.speed)/panel.tileSize;
                tileNum1 = panel.tileM.map[rightCol][topRow];
                tileNum2 = panel.tileM.map[rightCol][bottomRow];
                if(panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }


    }
    public int checkObject(Entity e, boolean player){
        int index = 999;
        for(int i = 0 ; i<panel.obj.length; i++){
            if(panel.obj[i]!=null){
                //Get entity's solid area position
                e.solidArea.x = e.x + e.solidArea.x;
                e.solidArea.y = e.y + e.solidArea.y;
                //Get object's solid area position
                panel.obj[i].solidArea.x = panel.obj[i].x + panel.obj[i].solidArea.x;
                panel.obj[i].solidArea.y = panel.obj[i].y + panel.obj[i].solidArea.y;
                switch (e.direction){
                    case "up":
                    e.solidArea.y -= e.speed;
                    if(e.solidArea.intersects(panel.obj[i].solidArea)){
                        //check if the object is solid
                        if(panel.obj[i].collision){
                            e.collisionOn = true;
                        }
                        //check if it's the player
                        if(player){
                            index = i;
                        }
                    }
                    break;
                    case "down":
                    e.solidArea.y += e.speed;
                    if(e.solidArea.intersects(panel.obj[i].solidArea)){
                        //check if the object is solid
                        if(panel.obj[i].collision){
                            e.collisionOn = true;
                        }
                        //check if it's the player
                        if(player){
                            index = i;
                        }
                        }
                    break;
                    case "left":
                    e.solidArea.x -= e.speed;
                    if(e.solidArea.intersects(panel.obj[i].solidArea)){
                        //check if the object is solid
                        if(panel.obj[i].collision){
                            e.collisionOn = true;
                        }
                        //check if it's the player
                        if(player){
                            index = i;
                        }
                        }
                    break;
                    case"right":
                    e.solidArea.x += e.speed;
                    if(e.solidArea.intersects(panel.obj[i].solidArea)){
                        //check if the object is solid
                        if(panel.obj[i].collision){
                            e.collisionOn = true;
                        }
                        //check if it's the player
                        if(player){
                            index = i;
                        }
                        }
                    break;
                }
                //reset collision area
                e.solidArea.x = e.solidAdreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                panel.obj[i].solidArea.x = panel.obj[i].solidAreaDefaultX;
                panel.obj[i].solidArea.y = panel.obj[i].solidAreaDefaultY;
            }

        }
        return index;
    }
}

