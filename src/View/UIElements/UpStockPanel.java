package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class UpStockPanel extends JPanel {
    private final int WIDTH = 130;
    private final int HEIGHT = 175;

    public UpStockPanel(Point coordinates) {
        setBackground(new Color(0, 37, 0));
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }
}
