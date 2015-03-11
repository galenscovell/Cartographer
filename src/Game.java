
/**
 * Main Game class for my explorations in Java game development.
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends Canvas {
    private BufferStrategy buffstrat;
    private boolean running = true;

    public Game() {
        JFrame container = new JFrame("Java Game Development");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(800, 600));

        panel.setLayout(null);
        setBounds(0, 0, 800, 600);
        panel.add(this);
        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        createBufferStrategy(2);
        buffstrat = getBufferStrategy();

        gameLoop();
    }

    public void gameLoop() {
        long lastLoopTime = System.currentTimeMillis();

        // Main loop
        while (running) {
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            Graphics2D g = (Graphics2D) buffstrat.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 600);

            g.dispose();
            buffstrat.show();

            try {
                Thread.sleep(10);
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}