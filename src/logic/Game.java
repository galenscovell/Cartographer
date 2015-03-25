
/**
 * GAME CLASS
 * Handles game loop.
 */

package logic;

import automata.Explorer;
import automata.World;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Game extends JPanel implements Runnable {
    private int smoothingTicks;
    private int framerate;
    private boolean running = false;
    private boolean threadActive;

    private World world;
    private Explorer explorer;
    private Thread thread;


    public Game(int x, int y, int tileSize, int margin, String worldType, int smoothing, int framerate) {
        this.smoothingTicks = smoothing;
        this.framerate = framerate;
        this.world = new World(x, y, tileSize, margin, worldType);
        this.setPreferredSize(new Dimension(x, y));
    }

    public synchronized void start() {
        this.thread = new Thread(this, "Simulation");
        this.threadActive = true;
        thread.start(); // call run()
    }

    public synchronized void stop() {
        running = false;
        threadActive = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            thread.interrupt();
        }
    }

    public void pause() {
        if (running) {
            running = false;
        } else {
            running = true;
        }
    }

    @Override
    public void run() {
        long start, end, sleepTime;

        while (threadActive) {
            start = System.currentTimeMillis();
                
            if (smoothingTicks > 0) {
                world.update();
                smoothingTicks--;
            } 

            if (running) {
                if (explorer == null) {
                    Point floorPoint = world.findFloorSpace();
                    if (floorPoint != null) {
                        explorer = world.placeExplorer(floorPoint);
                    } else {
                        pause();
                    }
                } else if (explorer.movement(world) == false) {
                    explorer = null;
                }
            }

            repaint();
            end = System.currentTimeMillis();
            // Sleep to match FPS limit
            sleepTime = (1000 / framerate) - (end - start);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); 
                } catch (InterruptedException e) {
                    thread.interrupt();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gfx = (Graphics2D) g;

        // Clear screen
        gfx.setColor(new Color(0x2c3e50));
        gfx.fillRect(0, 0, getWidth(), getHeight());
        // Render next frame
        this.world.render(gfx);
    }
}