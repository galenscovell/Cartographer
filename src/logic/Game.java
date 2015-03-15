
/**
 * GAME CLASS
 * Responsible for handling of game loop.
 */

package logic;

import graphics.World;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {
    private int windowX  = 722;
    private int windowY  = 482;
    private int cellSize = 13;
    private int margin   = 2;

    private int ticks = 100;

    private boolean running = false;
    private World world;
    private JFrame frame;

    final int FPS = 20;
    final int SKIP_TICKS = 1000 / FPS;
    final int MAX_FRAMESKIP = 5;


    public Game() {
        Dimension size = new Dimension(windowX, windowY);
        setPreferredSize(size);
        this.world = new World(windowX, windowY, cellSize, margin);
        this.frame = new JFrame();

        this.frame.setResizable(false);
        this.frame.setTitle("Cave Creator");
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public synchronized void start() {
        this.world.build();

        this.running = true;
        Thread thread = new Thread(this);
        thread.start(); // call run()
    }

    public synchronized void stop() throws Exception {
        this.running = false;
    }

    public void run() {
        double nextTick = System.currentTimeMillis();
        int loops;

        // Main loop
        while (this.running && this.ticks > 0) {
            loops = 0;
            while (System.currentTimeMillis() > nextTick && loops < MAX_FRAMESKIP) {
                update();
                render();

                nextTick += SKIP_TICKS;
                loops++;
                this.ticks--;
            }
        }
    }

    public void update() {
        this.world.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = (Graphics2D) bs.getDrawGraphics();
        
        g.setColor(new Color(0x2c3e50));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        this.world.render(g);

        g.dispose();
        bs.show();
    }
}