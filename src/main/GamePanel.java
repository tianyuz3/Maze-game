package main;

import entity.Player;
import tile.TileManager;
import utilities.Object;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
   private final int originalTileSize = 16;
   private  final int scale = 3;
     public  final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize* maxScreenRow;

    public final int maxMapCol = 50;

    public final int maxMapRow = 50;
    public final int mapWidth = tileSize * maxMapCol;
    public final int mapHeight = tileSize * maxMapRow;

    int FPS = 60;
    Key key = new Key();
    //Entity and object
    public Object obj [] = new Object[10];
    public Player player = new Player(this,key);
    //System
    public CollisionTester cT = new CollisionTester(this);

    Sound music = new Sound();

    Sound soundEffect = new Sound();
    public ObjSetter oSetter = new ObjSetter(this);
    TileManager tileM = new TileManager(this);
    Thread gameThread;

    public UI ui = new UI(this);







    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    public void setUpGame(){
        oSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread!=null){

        update();

        repaint();

            try {
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime/1000000;
                if(remainTime<0){
                    remainTime = 0;
                }
                Thread.sleep((long) remainTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
       player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //tile
        tileM.draw(g2);
        //object
        for(int i = 0 ; i<obj.length; i++){
            if(obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        //player
        player.draw(g2);
        //UI
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
            soundEffect.setFile(i);
            soundEffect.play();
    }
}
