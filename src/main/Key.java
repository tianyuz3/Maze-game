package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Key implements KeyListener {
    public boolean up, down,left,right,enter,esc;
    GamePanel gp;

    public Key(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //Main menu state
        if(gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3)
                    gp.ui.commandNum = 0;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }//load game
                if (gp.ui.commandNum == 1) {
                gp.saveLoad.load();
                gp.gameState = gp.playState;
                }//help menu
                if (gp.ui.commandNum == 2) {

                }//exit
                if (gp.ui.commandNum == 3) {
                System.exit(0);
                }
            }//option state
            } else if(gp.gameState == gp.optionState){
            optionState(code);
            //play state
        } else if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                up = true;
            }
            if (code == KeyEvent.VK_A) {
                left = true;
            }
            if (code == KeyEvent.VK_S) {
                down = true;
            }
            if (code == KeyEvent.VK_D) {
                right = true;
            }
            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                    gp.stopMusic();
                } else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
            }
            if (code == KeyEvent.VK_ESCAPE) {
                System.out.println("Game saved");
                try {
                    gp.saveLoad.save();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);

            }
        }
    }

    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code== KeyEvent.VK_W){
            up = false;
        }
        if(code== KeyEvent.VK_A){
            left = false;
        }
        if(code== KeyEvent.VK_S){
            down = false;
        }
        if(code== KeyEvent.VK_D){
            right = false;
        }
    }
}
