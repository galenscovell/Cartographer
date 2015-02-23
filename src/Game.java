
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

// Game is subclass of Canvas
public class Game extends Canvas implements Runnable {
    
    // Game window resolution is 16:9
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    
    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    
    // Constructor for Game
    public Game() {
        // New Dimension object
        Dimension size = new Dimension(width * scale, height * scale);
        // Set window size to Dimension object using Canvas method 'setPreferredSize'
        setPreferredSize(size);
        
        frame = new JFrame();
    }
    
    // Start thread, synchronized: prevent thread interferences
    public synchronized void start() {
        // Create new thread using 'this' instance of 'Game'
        // with name "Display"
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    
    // Attempt to stop thread, if unable wait and try again
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Implement Runnable run() method
    public void run() {
        while (running) {
            // Handles logic (speed limited)
            update();
            // Handles graphics (speed unlimited)
            render();
        }
    }

    public void update() {
        // TODO
    }

    public void render() {
        // Creation of buffer strategy
        // Buffer is region of data for storage
        BufferStrategy bs = getBufferStrategy();
        // If buffer not present, create
        if (bs == null) {
            createBufferStrategy(3); // Triple-buffer
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Set next graphic method color
        g.setColor(new Color(44, 62, 80));
        // Create rectangle at (0, 0) with width/height of frame
        g.fillRect(0, 0, getWidth(), getHeight());

        // Toss current graphics, free system resources
        g.dispose();
        // Show buffer strategy (blitting)
        bs.show();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Java Game Development");
        // Add game component to frame
        game.frame.add(game);
        // Size frame and game component to match
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center frame on screen
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        
        game.start();
    }

}
