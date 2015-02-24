
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

// Game is subclass of Canvas
// Runnable interface has only one method, run()
// run() contains the code executed within the thread
public class Game extends Canvas implements Runnable {
    
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    
    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    // Main image object (final rendered view)
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // Convert image object into array of integers
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    // Game instance constructor
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size); // Canvas method
        
        screen = new Screen(width, height);
        frame = new JFrame();
    }
    
    // Start thread from run()
    public synchronized void start() {
        // Create new thread using 'this' instance of 'Game'
        // with name "Display"
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    
    // Stop thread from run()
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Implement run() method
    public void run() {
        while (running) {
            update(); // Handles logic (speed limited)
            render(); // Handles graphics (speed unlimited)
        }
    }

    public void update() {
        // TODO
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        // If buffer not present, create
        if (bs == null) {
            createBufferStrategy(3); // Triple-buffer
            return;
        }

        // Call sceen object's render method
        screen.render();

        // Copy screen pixel array to buffer pixel array
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(44, 62, 80)); // Set next graphic method color
        g.fillRect(0, 0, getWidth(), getHeight()); // rectangle at (0, 0) with w/h of frame

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose(); // Toss current graphics, free system resources
        bs.show(); // Show buffer strategy (blitting)
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Java Game Development");
        game.frame.add(game); // Add game component to frame
        game.frame.pack(); // Size frame and game component to match
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); // Center frame on screen
        game.frame.setVisible(true);
        
        game.start();
    }

}
