package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }
    public void save() throws FileNotFoundException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();
            ds.x = gp.player.x;
            ds.y = gp.player.y;
            ds.speed = gp.player.speed;
            ds.keyFound = gp.player.keyFound;
            ds.potionFound = gp.player.potionFound;



            oos.writeObject(ds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.x =ds.x;
            gp.player.y = ds.y;
            gp.player.speed = ds.speed;
            gp.player.keyFound = ds.keyFound;
            gp.player.potionFound = ds.potionFound;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
