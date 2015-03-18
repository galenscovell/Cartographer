
/**
 * MENUSCREEN CLASS
 * Displays various editable fields/menus to user.
 */

package ui;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;


public class MenuScreen extends JPanel implements Screen {
    private int width;
    private int height;
    private String text;

    public MenuScreen(int width, int height) {
        this.width = width;
        this.height = height / 4;
    }

    public void displayOutput() {
        
    }

    public void respondToUserInput(KeyEvent key) {
        
    }
}