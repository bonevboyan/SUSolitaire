package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class DownStockPanel extends JPanel {
    //visualisation of the down stock cards space
    private final int WIDTH = 130;
    private final int HEIGHT = 175;

    public DownStockPanel(Point coordinates) {
        setBackground(new Color(0, 37, 0));
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }
}
