
/**
 * MAINFRAME CLASS
 * Main frame for application, holds OptionPanel and GamePanel.
 */

package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;


public class MainFrame implements Runnable {
    private int windowX;
    private int windowY;
    private JFrame frame;

    public MainFrame(int x, int y) {
        this.windowX = x;
        this.windowY = y;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Mapper");
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
        GamePanel gamePanel = new GamePanel(6, 2, "cave", 32, 4);
        OptionPanel optionPanel = new OptionPanel(gamePanel, frame);
        container.add(optionPanel.getPanel());
        container.add(gamePanel);
    }
}