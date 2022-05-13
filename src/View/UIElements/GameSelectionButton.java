package View.UIElements;

import View.GameSelectionScreen;

import javax.swing.*;
import java.awt.*;

public class GameSelectionButton extends JButton {

    public static final int HEIGHT = 400, WIDTH = 300;

    public GameSelectionButton(String label, String source) {

    }

    public GameSelectionButton(String label, Color color) {
        this.setText(label);
        this.setBackground(color);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

}
