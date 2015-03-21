
/**
 * GAME CLASS
 * Handles game loop.
 *
 * Begins World, Thread and sets up BufferStrategy
 * Loops specified times through world-building process and exploration
 * Limits FPS
 */

package logic;

import automata.Explorer;
import automata.World;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;

@SuppressWarnings ("serial")


public class Game extends Canvas implements Runnable {
    private int smoothingTicks;
    private int framerate;
    private boolean running = false;

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
        render();
        this.thread = new Thread(this, "Simulation");
        this.thread.start(); // call run()
    }

    public synchronized void stop() {
        render();
        this.running = false;
        this.thread.interrupt();
    }

    public void pause() {
        render();
        if (this.running) {
            this.running = false;
        } else {
            this.running = true;
        }
    }

    public void run() {
        long start, end, sleepTime;

        while (true) {
            start = System.currentTimeMillis();
                
            if (this.smoothingTicks > 0) {
                this.world.update();
                render();
                this.smoothingTicks--;
            } 

            if (this.running) {
                if (this.explorer == null) {
                    Point floorPoint = this.world.findFloorSpace();
                    if (floorPoint != null) {
                        this.explorer = this.world.placeExplorer(floorPoint);
                    } else {
                        pause();
                    }
                } else if (this.explorer.movement(this.world) == false) {
                    this.explorer = null;
                }
            }

            render();
            end = System.currentTimeMillis();
            // Sleep to match FPS limit
            sleepTime = (1000 / this.framerate) - (end - start);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); 
                } catch (InterruptedException e) {
                    this.thread.interrupt();
                }
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D gfx = (Graphics2D) bs.getDrawGraphics();
        // Clear screen
        gfx.setColor(new Color(0x2c3e50));
        gfx.fillRect(0, 0, getWidth(), getHeight());
        // Render next frame
        this.world.render(gfx);

        gfx.dispose();
        bs.show();
    }
}