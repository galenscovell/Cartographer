
/**
 * SCREEN CLASS
 * Displays construction options to user.
 */

package ui;

import logic.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings ("serial")


public class Screen extends JPanel implements ActionListener {
    private int width;
    private int height;
    private Game game;

    private JCheckBox mazeCheck;
    private JCheckBox caveCheck;
    private JSlider frameSlide;
    private JSlider sizeSlide;
    private JSlider marginSlide;
    private JSlider smoothSlide;
    private JButton constructButton;
    private JButton pauseButton;
    private JLabel mainLabel;
    private boolean paused = false;

    private JFrame topFrame;


    public Screen(int width, int height, Game game, JFrame frame) {
        this.width = width;
        this.height = height;
        this.game = game;
        this.topFrame = frame;


        // Initialize components
        JPanel mainPanel     = new JPanel();
        JPanel subPanel      = new JPanel();
        this.mainLabel       = new JLabel("Settings", JLabel.CENTER);
        JPanel settingsPanel = new JPanel();
        this.mazeCheck       = new JCheckBox("Maze");
        this.caveCheck       = new JCheckBox("Cave");
        JLabel frameLabel    = new JLabel("FPS", JLabel.CENTER);
        this.frameSlide      = new JSlider(JSlider.HORIZONTAL, 2, 62, 32);
        JLabel sizeLabel     = new JLabel("Tile size", JLabel.CENTER);
        this.sizeSlide       = new JSlider(JSlider.HORIZONTAL, 2, 22, 8);
        JLabel marginLabel   = new JLabel("Tile margin", JLabel.CENTER);
        this.marginSlide     = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        JLabel smoothLabel   = new JLabel("Smoothing passes", JLabel.CENTER);
        this.smoothSlide     = new JSlider(JSlider.HORIZONTAL, 2, 22, 6);
        this.constructButton = new JButton("Construct");
        this.pauseButton     = new JButton("Pause");
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
        mazeCheck.setEnabled(false);

        caveCheck.setOpaque(false);
        caveCheck.setFocusPainted(false);
        caveCheck.setEnabled(false);

        frameSlide.setOpaque(false);
        frameSlide.setMajorTickSpacing(10);
        frameSlide.setPaintLabels(true);
        frameSlide.setPaintTicks(true);
        frameSlide.setEnabled(false);

        sizeSlide.setOpaque(false);
        sizeSlide.setMajorTickSpacing(4);
        sizeSlide.setPaintLabels(true);
        sizeSlide.setPaintTicks(true);
        sizeSlide.setEnabled(false);

        marginSlide.setOpaque(false);
        marginSlide.setMajorTickSpacing(2);
        marginSlide.setPaintLabels(true);
        marginSlide.setPaintTicks(true);
        marginSlide.setEnabled(false);

        smoothSlide.setOpaque(false);
        smoothSlide.setMajorTickSpacing(4);
        smoothSlide.setPaintLabels(true);
        smoothSlide.setPaintTicks(true);
        smoothSlide.setEnabled(false);

        constructButton.setFocusPainted(false);
        constructButton.setEnabled(false);
        constructButton.setActionCommand("construct");
        constructButton.addActionListener(this);

        pauseButton.setFocusPainted(false);
        pauseButton.setActionCommand("pause");
        pauseButton.addActionListener(this);

        quitButton.setFocusPainted(false);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        


        // Set component dimensions
        Dimension labelSize = new Dimension(120, 14);
        Dimension buttonSize = new Dimension(100, 25);
        Dimension sliderSize = new Dimension(140, 45);
        mainPanel.setPreferredSize(new Dimension(this.width / 4, this.height));
        subPanel.setPreferredSize(new Dimension(160, 450));
        mainLabel.setPreferredSize(labelSize);
        settingsPanel.setPreferredSize(new Dimension(140, 320));
        frameLabel.setPreferredSize(labelSize);
        sizeLabel.setPreferredSize(labelSize);
        marginLabel.setPreferredSize(labelSize);
        smoothLabel.setPreferredSize(labelSize);

        frameSlide.setPreferredSize(sliderSize);
        sizeSlide.setPreferredSize(sliderSize);
        marginSlide.setPreferredSize(sliderSize);
        smoothSlide.setPreferredSize(sliderSize);

        constructButton.setPreferredSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);
        

        // Set component fonts
        Font smallFont = new Font("Source Code Pro", Font.PLAIN, 12);
        Font largeFont = new Font("Source Code Pro", Font.PLAIN, 16);

        mainLabel.setFont(largeFont);
        frameLabel.setFont(smallFont);
        sizeLabel.setFont(smallFont);
        marginLabel.setFont(smallFont);
        smoothLabel.setFont(smallFont);

        mazeCheck.setFont(smallFont);
        caveCheck.setFont(smallFont);
        frameSlide.setFont(smallFont);
        sizeSlide.setFont(smallFont);
        marginSlide.setFont(smallFont);
        smoothSlide.setFont(smallFont);

        constructButton.setFont(smallFont);
        pauseButton.setFont(smallFont);
        quitButton.setFont(smallFont);

        
        // Compose components
        settingsPanel.add(mazeCheck);
        settingsPanel.add(caveCheck);
        settingsPanel.add(frameLabel);
        settingsPanel.add(frameSlide);
        settingsPanel.add(sizeLabel);
        settingsPanel.add(sizeSlide);
        settingsPanel.add(marginLabel);
        settingsPanel.add(marginSlide);
        settingsPanel.add(smoothLabel);
        settingsPanel.add(smoothSlide);

        subPanel.add(this.mainLabel);
        subPanel.add(settingsPanel);
        subPanel.add(this.constructButton);
        subPanel.add(pauseButton);
        subPanel.add(quitButton);

        mainPanel.add(subPanel);


        this.add(mainPanel);
        this.setOpaque(false);
    }

    public void constructNew(String worldType) {
        int tileSize = this.sizeSlide.getValue();
        int margin = this.marginSlide.getValue();
        int framerate = this.frameSlide.getValue();
        int smoothing = this.smoothSlide.getValue();

        this.game.stop();
        this.topFrame.getContentPane().remove(this.game);
        this.topFrame.repaint();

        this.game = new Game(this.width, this.height, tileSize, margin, worldType, smoothing, framerate);
        this.topFrame.getContentPane().add(this.game);
        this.topFrame.pack();
        this.game.buildWorld();
        this.pauseButton.doClick();
    }

    public void getConstructType() {
        boolean maze = this.mazeCheck.isSelected();
        boolean cave = this.caveCheck.isSelected();

        if (maze && cave || !maze && !cave) {
            return;
        } else if (maze) {
            constructNew("maze");
        } else if (cave) {
            constructNew("cave");
        }
    }

    public void pausePress() {
        if (this.paused) {
            this.paused = false;
            this.mainLabel.setText("Settings");
            this.pauseButton.setText("Pause");
            this.constructButton.setEnabled(false);
            this.mazeCheck.setEnabled(false);
            this.caveCheck.setEnabled(false);
            this.frameSlide.setEnabled(false);
            this.sizeSlide.setEnabled(false);
            this.marginSlide.setEnabled(false);
            this.smoothSlide.setEnabled(false);
        } else {
            this.paused = true;
            this.mainLabel.setText("Paused");
            this.pauseButton.setText("Run");
            this.constructButton.setEnabled(true);
            this.mazeCheck.setEnabled(true);
            this.caveCheck.setEnabled(true);
            this.frameSlide.setEnabled(true);
            this.sizeSlide.setEnabled(true);
            this.marginSlide.setEnabled(true);
            this.smoothSlide.setEnabled(true);
        }
        this.game.pause();
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("construct")) {
            getConstructType();
        } else if (command.equals("pause")) {
            pausePress();
        } else if (command.equals("quit")) {
            this.game.stop();
            System.exit(0);
        }
    }
}