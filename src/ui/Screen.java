
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
    private int screenX;
    private int screenY;
    private int width;
    private int height;
    private Game game;

    private JCheckBox mazeCheck;
    private JCheckBox caveCheck;
    private JSlider frameSlide;
    private JSlider sizeSlide;
    private JSlider marginSlide;
    private JButton constructButton;
    private JButton pauseButton;
    private JLabel mainLabel;
    private boolean paused = false;

    private JFrame topFrame;


    public Screen(int width, int height, Game game, JFrame frame) {
        this.screenX = width;
        this.screenY = height;
        this.width = width / 4;
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
        JLabel sizeLabel     = new JLabel("Cell size", JLabel.CENTER);
        this.sizeSlide       = new JSlider(JSlider.HORIZONTAL, 2, 16, 8);
        JLabel marginLabel   = new JLabel("Cell margin", JLabel.CENTER);
        this.marginSlide     = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
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
        sizeSlide.setMajorTickSpacing(2);
        sizeSlide.setPaintLabels(true);
        sizeSlide.setPaintTicks(true);
        sizeSlide.setEnabled(false);

        marginSlide.setOpaque(false);
        marginSlide.setMajorTickSpacing(2);
        marginSlide.setPaintLabels(true);
        marginSlide.setPaintTicks(true);
        marginSlide.setEnabled(false);

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
        Dimension labelSize = new Dimension(120, 20);
        Dimension buttonSize = new Dimension(110, 35);
        Dimension sliderSize = new Dimension(140, 45);
        mainPanel.setPreferredSize(new Dimension(this.width, this.height));
        subPanel.setPreferredSize(new Dimension(160, 460));
        mainLabel.setPreferredSize(labelSize);
        settingsPanel.setPreferredSize(new Dimension(140, 280));
        frameLabel.setPreferredSize(labelSize);
        sizeLabel.setPreferredSize(labelSize);
        marginLabel.setPreferredSize(labelSize);

        frameSlide.setPreferredSize(sliderSize);
        sizeSlide.setPreferredSize(sliderSize);
        marginSlide.setPreferredSize(sliderSize);

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

        mazeCheck.setFont(smallFont);
        caveCheck.setFont(smallFont);
        frameSlide.setFont(smallFont);
        sizeSlide.setFont(smallFont);
        marginSlide.setFont(smallFont);

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

        subPanel.add(this.mainLabel);
        subPanel.add(settingsPanel);
        subPanel.add(this.constructButton);
        subPanel.add(pauseButton);
        subPanel.add(quitButton);

        mainPanel.add(subPanel);


        this.add(mainPanel);
        this.setOpaque(false);
    }

    public void constructNew(String worldType, int smoothing) {
        int cellSize = this.sizeSlide.getValue();
        int margin = this.marginSlide.getValue();
        int framerate = this.frameSlide.getValue();

        this.game.stop();
        this.topFrame.getContentPane().remove(this.game);
        this.topFrame.repaint();

        this.game = new Game(this.screenX, this.screenY, cellSize, margin, worldType, smoothing, framerate);
        this.topFrame.getContentPane().add(this.game);
        this.topFrame.pack();
        this.game.buildWorld();
        this.pauseButton.doClick();
    }

    public void gatherConstructSettings() {
        boolean maze = this.mazeCheck.isSelected();
        boolean cave = this.caveCheck.isSelected();

        if (maze && cave) {
            return;
        } else if (maze) {
            constructNew("maze", 20);
        } else if (cave) {
            constructNew("cave", 4);
        } else {
            return;
        }
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("construct")) {
            gatherConstructSettings();
        } else if (command.equals("pause")) {
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
            }
            this.game.pause();
        } else if (command.equals("quit")) {
            this.game.stop();
            System.exit(0);
        }
    }
}