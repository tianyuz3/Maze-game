package main;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setResizable(false);
        w.setTitle("Maze");
        GamePanel game = new GamePanel();
        w.add(game);
        w.pack();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        game.startGameThread();

    }
}