
/**
 * OPTIONPANEL CLASS
 * Displays construction options to user within panel.
 */

package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;


public class OptionPanel implements ActionListener {
    private GamePanel gamePanel;
    private JFrame rootFrame;
    private JPanel panel;

    private JRadioButton mazeCheck;
    private JRadioButton caveCheck;
    private JRadioButton dungeonCheck;
    private JSlider frameSlide;
    private JSlider sizeSlide;
    private JSlider marginSlide;
    private JSlider smoothSlide;
    private JButton constructButton;
    private JButton pauseButton;
    private JLabel mainLabel;

    private boolean paused = true;


    public OptionPanel(GamePanel gamePanel, JFrame rootFrame) {
        this.gamePanel = gamePanel;
        this.rootFrame = rootFrame;
        this.panel = new JPanel();

        panel.setPreferredSize(new Dimension(200, 480));
        panel.setBackground(new Color(0x31312F));
        panel.setOpaque(true);
        createComponents(panel);
        gamePanel.start();
    }
        
    private void createComponents(Container container) {
        // Initialize components
        JPanel subPanel      = new JPanel();
        this.mainLabel       = new JLabel("Settings", JLabel.CENTER);
        JPanel settingsPanel = new JPanel();
        this.mazeCheck       = new JRadioButton("Maze");
        this.caveCheck       = new JRadioButton("Cave");
        this.dungeonCheck    = new JRadioButton("Dungeon");
        JLabel frameLabel    = new JLabel("FPS", JLabel.CENTER);
        this.frameSlide      = new JSlider(JSlider.HORIZONTAL, 2, 62, 32);
        JLabel sizeLabel     = new JLabel("Tile size", JLabel.CENTER);
        this.sizeSlide       = new JSlider(JSlider.HORIZONTAL, 2, 32, 6);
        JLabel marginLabel   = new JLabel("Tile margin", JLabel.CENTER);
        this.marginSlide     = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        JLabel smoothLabel   = new JLabel("Smoothing passes", JLabel.CENTER);
        this.smoothSlide     = new JSlider(JSlider.HORIZONTAL, 2, 22, 4);
        this.constructButton = new JButton("Construct");
        this.pauseButton     = new JButton("Pause");
        JButton quitButton   = new JButton("Quit");


        // Set specific component settings
        subPanel.setBackground(new Color(0xE7E7E7));

        settingsPanel.setBackground(new Color(0xE7E7E7));

        mazeCheck.setOpaque(false);
        mazeCheck.setFocusPainted(false);
        mazeCheck.setEnabled(true);

        caveCheck.setOpaque(false);
        caveCheck.setFocusPainted(false);
        caveCheck.setEnabled(true);

        dungeonCheck.setOpaque(false);
        dungeonCheck.setFocusPainted(false);
        dungeonCheck.setEnabled(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mazeCheck);
        buttonGroup.add(caveCheck);
        buttonGroup.add(dungeonCheck);

        frameSlide.setOpaque(false);
        frameSlide.setMajorTickSpacing(10);
        frameSlide.setPaintLabels(true);
        frameSlide.setPaintTicks(true);
        frameSlide.setEnabled(true);

        sizeSlide.setOpaque(false);
        sizeSlide.setMajorTickSpacing(6);
        sizeSlide.setPaintLabels(true);
        sizeSlide.setPaintTicks(true);
        sizeSlide.setEnabled(true);

        marginSlide.setOpaque(false);
        marginSlide.setMajorTickSpacing(2);
        marginSlide.setPaintLabels(true);
        marginSlide.setPaintTicks(true);
        marginSlide.setEnabled(true);

        smoothSlide.setOpaque(false);
        smoothSlide.setMajorTickSpacing(4);
        smoothSlide.setPaintLabels(true);
        smoothSlide.setPaintTicks(true);
        smoothSlide.setEnabled(true);

        constructButton.setFocusPainted(false);
        constructButton.setEnabled(true);
        constructButton.setActionCommand("construct");
        constructButton.addActionListener(this);

        pauseButton.setFocusPainted(false);
        pauseButton.setActionCommand("pause");
        pauseButton.addActionListener(this);
        pauseButton.setText("Run");

        quitButton.setFocusPainted(false);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        

        // Set component dimensions
        Dimension labelSize = new Dimension(120, 14);
        Dimension buttonSize = new Dimension(110, 25);
        Dimension sliderSize = new Dimension(140, 45);

        subPanel.setPreferredSize(new Dimension(180, 470));
        mainLabel.setPreferredSize(new Dimension(140, 20));
        settingsPanel.setPreferredSize(new Dimension(160, 340));

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
        dungeonCheck.setFont(smallFont);
        frameSlide.setFont(smallFont);
        sizeSlide.setFont(smallFont);
        marginSlide.setFont(smallFont);
        smoothSlide.setFont(smallFont);

        constructButton.setFont(smallFont);
        pauseButton.setFont(smallFont);
        quitButton.setFont(smallFont);

        
        // Add components
        settingsPanel.add(mazeCheck);
        settingsPanel.add(caveCheck);
        settingsPanel.add(dungeonCheck);
        settingsPanel.add(frameLabel);
        settingsPanel.add(frameSlide);
        settingsPanel.add(sizeLabel);
        settingsPanel.add(sizeSlide);
        settingsPanel.add(marginLabel);
        settingsPanel.add(marginSlide);
        settingsPanel.add(smoothLabel);
        settingsPanel.add(smoothSlide);

        subPanel.add(mainLabel);
        subPanel.add(settingsPanel);
        subPanel.add(constructButton);
        subPanel.add(pauseButton);
        subPanel.add(quitButton);

        container.add(subPanel);
    }

    private void constructNewGame(String worldType) {
        int tileSize = sizeSlide.getValue();
        int margin = marginSlide.getValue();
        int framerate = frameSlide.getValue();
        int smoothing = smoothSlide.getValue();

        gamePanel.stop();
        rootFrame.remove(gamePanel);

        this.gamePanel = new GamePanel(tileSize, margin, worldType, framerate, smoothing);
        rootFrame.add(gamePanel);
        rootFrame.pack();
        gamePanel.start();
    }

    private void getWorldType() {
        if (mazeCheck.isSelected()) {
            constructNewGame("maze");
        } else if (caveCheck.isSelected()) {
            constructNewGame("cave");
        } else if (dungeonCheck.isSelected()) {
            constructNewGame("dungeon");
        }
    }

    private void pausePress() {
        if (paused) {
            paused = false;
            mainLabel.setText("Settings");
            pauseButton.setText("Pause");
            constructButton.setEnabled(false);
            mazeCheck.setEnabled(false);
            caveCheck.setEnabled(false);
            dungeonCheck.setEnabled(false);
            frameSlide.setEnabled(false);
            sizeSlide.setEnabled(false);
            marginSlide.setEnabled(false);
            smoothSlide.setEnabled(false);
        } else {
            paused = true;
            mainLabel.setText("Paused");
            pauseButton.setText("Run");
            constructButton.setEnabled(true);
            mazeCheck.setEnabled(true);
            caveCheck.setEnabled(true);
            dungeonCheck.setEnabled(true);
            frameSlide.setEnabled(true);
            sizeSlide.setEnabled(true);
            marginSlide.setEnabled(true);
            smoothSlide.setEnabled(true);
        }
        gamePanel.pause();
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("construct")) {
            getWorldType();
        } else if (command.equals("pause")) {
            pausePress();
        } else if (command.equals("quit")) {
            gamePanel.stop();
            System.exit(0);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}