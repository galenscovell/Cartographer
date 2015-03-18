
/**
 * MAIN CLASS
 * Responsible for entry into application.
 */

import logic.Game;
import ui.Screen;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
        int windowX  = 720;
        int windowY  = 480;
        int cellSize = 8;
        int margin   = 2;

        Game game     = new Game(windowX, windowY, cellSize, margin);
        Screen screen = new Screen(windowX, windowY);
        JFrame frame  = new JFrame();

        frame.getContentPane().setBackground(new Color(0x2c3e50));
        frame.setResizable(false);
        frame.setTitle("Dungeon Creator");;
        frame.setLayout(new FlowLayout());
        frame.add(game);
        frame.add(screen);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}