
/**
 * MAIN CLASS
 * Main entry for Cave Creator application.
 */

import ui.GameFrame;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame(960, 520);
        SwingUtilities.invokeLater(gameFrame);
    }
}