
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

import java.awt.image.BufferStrategy;

@SuppressWarnings ("serial")


public class Game extends Canvas implements Runnable {
    private int smoothingTicks;
    private int framerate;
    private boolean running = false;
    private boolean firstRun = true;

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
        buildWorld();
        this.running = true;
        this.thread.start(); // call run()
    }

    public synchronized void stop() {
        this.running = false;
        this.thread = null;
    }

    public void pause() {
        if (this.thread == null) {
            this.world.clearActive();
            start();
        } else {
            stop();
        }
    }

    public void buildWorld() {
        long start, end, sleepTime;

        while (this.smoothingTicks > 0) {
            start = System.currentTimeMillis();
            this.world.update();
            render();

            this.smoothingTicks--;
            end = System.currentTimeMillis();
            // Sleep to match FPS limit
            sleepTime = (1000 / this.framerate) - (end - start);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        if (this.firstRun) {
            this.firstRun = false;
            pause();
        }

        long start, end, sleepTime;
        
        // Explorer loop
        while (this.running) {
            if (this.explorer != null && this.explorer.movement(this.world)) {
                // Current explorer moves if able
                start = System.currentTimeMillis();
                render();

                end = System.currentTimeMillis();
                // Sleep to match FPS limit
                sleepTime = (1000 / this.framerate) - (end - start);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Check if world contains empty floor tiles
                Point floorPoint = this.world.findFloorSpace();
                if (floorPoint != null) {
                    // Place new explorer if empty floor tile remains
                    this.explorer = this.world.placeExplorer(floorPoint);
                } else {
                    // Render one last time to finish current frame
                    render();
                    // End exploration
                    stop();
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