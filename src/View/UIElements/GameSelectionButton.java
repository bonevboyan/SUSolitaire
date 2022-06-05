package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class GameSelectionButton extends JButton {
    //button to select game
    public static final int HEIGHT = 350, WIDTH = 380;

    //makes button just an image
    public GameSelectionButton(String label, String source) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setIcon(new ImageIcon(source));
    }

    //makes button text with colored background
    public GameSelectionButton(String label, Color color) {
        this.setText(label);
        this.setBackground(color);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

}
