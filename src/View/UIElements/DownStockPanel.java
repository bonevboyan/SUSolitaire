package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class DownStockPanel extends JPanel {
    private final int WIDTH = 130;
    private final int HEIGHT = 175;

    private int cardAmount = 0;

    public DownStockPanel(Point coordinates) {
        setBackground(new Color(0, 37, 0));
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public void incrementCardAmount(int increment) {
        cardAmount += increment;
    }
}
