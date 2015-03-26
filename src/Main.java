
/**
 * MAIN CLASS
 * Main entry for Cave Creator application.
 */

import ui.MainFrame;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(960, 520);
        SwingUtilities.invokeLater(mainFrame);
    }
}