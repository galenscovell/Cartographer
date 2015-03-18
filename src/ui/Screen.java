
/**
 * SCREEN CLASS
 * Displays various editable fields/menus to user.
 */

package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class Screen extends JPanel {
    private int width;
    private int height;
    private String text;

    public Screen(int width, int height) {
        this.width = width / 4;
        this.height = height;


        // Initialize components
        JPanel mainPanel     = new JPanel();
        JPanel subPanel      = new JPanel();
        JLabel startLabel    = new JLabel("Settings", JLabel.CENTER);
        JPanel settingsPanel = new JPanel();
        JCheckBox mazeCheck  = new JCheckBox("Maze");
        JCheckBox caveCheck  = new JCheckBox("Cave");
        JLabel slideLabel    = new JLabel("FPS", JLabel.CENTER);
        JSlider frameSlide   = new JSlider(JSlider.HORIZONTAL, 0, 60, 30);
        JButton startButton  = new JButton("Construct");
        JButton pauseButton  = new JButton("Pause");
        JButton resetButton  = new JButton("Reset");
        JButton quitButton   = new JButton("Quit");


        // Set specific component settings
        mainPanel.setBackground(new Color(0x31312F));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        subPanel.setBackground(new Color(0xE7E7E7));
        subPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        settingsPanel.setBackground(new Color(0xE7E7E7));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        mazeCheck.setOpaque(false);
        mazeCheck.setFocusPainted(false);

        caveCheck.setOpaque(false);
        caveCheck.setFocusPainted(false);

        frameSlide.setOpaque(false);
        frameSlide.setMajorTickSpacing(15);
        frameSlide.setPaintLabels(true);
        frameSlide.setPaintTicks(true);

        startButton.setFocusPainted(false);

        pauseButton.setFocusPainted(false);

        resetButton.setFocusPainted(false);

        quitButton.setFocusPainted(false);


        // Set component dimensions
        Dimension labelSize = new Dimension(120, 20);
        Dimension buttonSize = new Dimension(110, 35);
        mainPanel.setPreferredSize(new Dimension(this.width, this.height));
        subPanel.setPreferredSize(new Dimension(160, 440));
        startLabel.setPreferredSize(labelSize);
        settingsPanel.setPreferredSize(new Dimension(140, 140));
        slideLabel.setPreferredSize(labelSize);
        frameSlide.setPreferredSize(new Dimension(120, 50));
        startButton.setPreferredSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        resetButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);
        

        // Set component fonts
        Font smallFont = new Font("Source Code Pro", Font.PLAIN, 12);
        Font largeFont = new Font("Source Code Pro", Font.PLAIN, 16);
        startLabel.setFont(largeFont);
        slideLabel.setFont(smallFont);
        mazeCheck.setFont(smallFont);
        caveCheck.setFont(smallFont);
        frameSlide.setFont(smallFont);
        startButton.setFont(smallFont);
        pauseButton.setFont(smallFont);
        resetButton.setFont(smallFont);
        quitButton.setFont(smallFont);

        
        // Compose components
        settingsPanel.add(mazeCheck);
        settingsPanel.add(caveCheck);
        settingsPanel.add(slideLabel);
        settingsPanel.add(frameSlide);

        subPanel.add(startLabel);
        subPanel.add(settingsPanel);
        subPanel.add(startButton);
        subPanel.add(pauseButton);
        subPanel.add(resetButton);
        subPanel.add(quitButton);

        mainPanel.add(subPanel);


        this.add(mainPanel);
        this.setOpaque(false);
    }
}