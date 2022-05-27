package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class FoundationPanel extends JPanel {
    private final int WIDTH = 130;
    private final int HEIGHT = 180;

    public FoundationPanel(Point coordinates) {
        setBackground(new Color(0, 37, 0));
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }
}
