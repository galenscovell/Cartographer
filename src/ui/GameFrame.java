
/**
 * GAMEFRAME CLASS
 * Main JFrame for application, holds Screen(game and options);
 */

package ui;

import logic.Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;


public class GameFrame implements Runnable {
    private int windowX;
    private int windowY;
    private JFrame frame;
    private Screen screen;

    public GameFrame(int x, int y) {
        this.windowX = x;
        this.windowY = y;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Cave Creator");
        frame.setPreferredSize(new Dimension(windowX, windowY));
        frame.getContentPane().setBackground(new Color(0x2c3e50));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents(frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new FlowLayout());
        Game game = new Game(720, 480, 6, 2, "cave", 4, 32);
        Screen screen = new Screen(game, frame);
        container.add(screen.getPanel());
        container.add(game);
    }
}