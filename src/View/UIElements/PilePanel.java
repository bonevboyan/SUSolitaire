package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class PilePanel extends JPanel {
    private final int WIDTH = 130;
    private final int HEIGHT = 400;

    private int cardAmount = 0;

    public PilePanel(Point coordinates) {
        setBackground(new Color(0, 37, 0));
        setBounds(coordinates.x, coordinates.y, WIDTH, HEIGHT);
    }

    public PilePanel(Point coordinates, int cardAmount) {
        this(coordinates);
        this.cardAmount = cardAmount;
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
