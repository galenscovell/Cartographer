
/**
 * GAME CLASS
 * Handles game loop.
 *
 * Creates JFrame and World
 * Begins Thread and sets up BufferStrategy
 * Loops specified times through world-building process
 * Loops specified times through exploration process
 * Limits FPS
 */

package logic;

import automata.Explorer;
import automata.World;
import ui.MenuScreen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends Canvas implements Runnable {
    private int windowX  = 720;
    private int windowY  = 480;
    private int cellSize = 8;
    private int margin   = 2;

    private int smoothingTicks = 20;
    private int exploringTicks = 2000;

    private boolean running = false;
    private World world;
    private Explorer explorer;
    private JFrame mainFrame;
    private JPanel menuFrame;

    final int FPS = 30;


    public Game() {
        this.world = new World(windowX, windowY, cellSize, margin);

        Dimension size = new Dimension(windowX, windowY);
        setPreferredSize(size);
        this.mainFrame = new JFrame();
        this.menuFrame = new MenuScreen(windowX, windowY);

        this.mainFrame.setResizable(false);
        this.mainFrame.setTitle("Maze Creator");
        this.mainFrame.add(this);
        this.mainFrame.pack();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    public synchronized void start() {
        this.running = true;
        Thread thread = new Thread(this, "Display");
        thread.start(); // call run()
    }

    public synchronized void stop() throws Exception {
        this.running = false;
    }

    public void run() {
        long start, end, sleepTime;

        // World building loop
        while (this.running && this.smoothingTicks > 0) {
            start = System.currentTimeMillis();
            this.world.update();
            render();
            this.smoothingTicks--;
            end = System.currentTimeMillis();
            // Sleep to match FPS limit
            sleepTime = (1000 / FPS) - (end - start);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        this.explorer = this.world.placeExplorer();

        // Explorer loop
        while (this.running && this.exploringTicks > 0) {
            if (this.explorer.movement(this.world)) {
                // Current explorer moves if able
                start = System.currentTimeMillis();
                render();
                this.exploringTicks--;
                end = System.currentTimeMillis();
                // Sleep to match FPS limit
                sleepTime = (1000 / FPS) - (end - start);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (this.world.isFloorSpace()) {
                // Place new explorer if empty space remains
                this.explorer = this.world.placeExplorer();
            } else {
                // Stop exploration runtime
                this.exploringTicks = 0;
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics gfx = bs.getDrawGraphics();
        // Clear screen
        gfx.setColor(new Color(0x2c3e50));
        gfx.fillRect(0, 0, getWidth(), getHeight());
        // Render next frame
        this.world.render(gfx);

        gfx.dispose();
        bs.show();
    }
}