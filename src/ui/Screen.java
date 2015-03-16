
/**
 * SCREEN INTERFACE
 * All screens displayOutput() and respondToUserInput()
 */

package ui;

import java.awt.event.KeyEvent;


public interface Screen {
    public void displayOutput();
    public void respondToUserInput(KeyEvent key);
}