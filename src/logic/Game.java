
/**
 * GAME CLASS
 * Handles game loop.
 *
 * Begins World, Thread and sets up BufferStrategy
 * Loops specified times through world-building process
 * Loops specified times through exploration process
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


public class Game extends Canvas implements Runnable {
    private int smoothingTicks = 2;
    private int exploringTicks = 2000;
    private World world;
    private Explorer explorer;
    private Thread thread;
    private int framerate = 30;


    public Game(int x, int y, int size, int margin) {
        this.world = new World(x, y, size, margin);
        this.setPreferredSize(new Dimension(x, y));
    }

    public synchronized void start() {
        this.thread = new Thread(this, "Display");
        this.thread.start(); // call run()
    }

    public synchronized void stop() {
        this.thread.interrupt();
    }

    public void run() {
        long start, end, sleepTime;

        // World building loop
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

        this.explorer = this.world.placeExplorer();

        // Explorer loop
        while (this.exploringTicks > 0) {
            if (this.explorer.movement(this.world)) {
                // Current explorer moves if able
                start = System.currentTimeMillis();
                render();
                this.exploringTicks--;
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