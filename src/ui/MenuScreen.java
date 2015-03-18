
/**
 * MENUSCREEN CLASS
 * Displays various editable fields/menus to user.
 */

package ui;

import java.awt.event.KeyEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuScreen extends JPanel implements Screen {
    private int width;
    private int height;
    private String text;

    public MenuScreen(int width, int height) {
        this.width = width / 4;
        this.height = height;

        JPanel mainPanel = new JPanel();

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(150, 100));
        subPanel.setBackground(new Color(0xE7E7E7));
        subPanel.setLayout(new GridLayout(2, 0));

        JLabel label = new JLabel("Testing!", JLabel.CENTER);
        label.setForeground(new Color(0x000000));
        subPanel.add(label);

        JButton startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.black));
        startButton.setBackground(new Color(0x4183C4));
        subPanel.add(startButton);

        mainPanel.setPreferredSize(new Dimension(this.width, this.height));
        mainPanel.setBackground(new Color(0x31312F));
        mainPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x7f8c8d)));

        mainPanel.add(subPanel);
        this.add(mainPanel);
        this.setOpaque(false);
    }

    public void displayOutput() {
        
    }

    public void respondToUserInput(KeyEvent key) {
        
    }
}