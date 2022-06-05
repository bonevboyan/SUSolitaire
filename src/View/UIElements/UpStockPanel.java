package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class UpStockPanel extends JPanel {
    //panel for the open stock cards
    private final int WIDTH = 130;
    private final int HEIGHT = 175;

    public UpStockPanel(Point coordinates) {
        setBackground(new Color(0, 81, 0)); // set green to 37 to visualises the up stock
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }
}
