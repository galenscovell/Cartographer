
/**
 * MAIN CLASS
 * Responsible for entry into application.
 */

import logic.Game;
import ui.Screen;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings ("serial")


public class Main {

    public static void main(String[] args) {
        int windowX  = 720;
        int windowY  = 480;
        int tileSize = 8;
        int margin   = 2;

        JFrame frame = new JFrame();
        Game game = new Game(windowX, windowY, tileSize, margin, "maze", 20, 32);
        Screen screen = new Screen(windowX, windowY, game, frame);
        
        frame.getContentPane().setBackground(new Color(0x2c3e50));
        frame.setResizable(false);
        frame.setTitle("Dungeon Creator");;
        frame.setLayout(new FlowLayout());
        frame.add(screen);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}