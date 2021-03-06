package View.UIElements;

import javax.swing.*;
import java.awt.*;

public class PilePanel extends JPanel {
    //panel for the piles of cards
    private final int WIDTH = 130;
    private int height = 180;

    private int cardAmount = 0;

    public PilePanel(Point coordinates) {
        setBackground(new Color(0, 81, 0)); // set green to 37 to visualises the piles
        setBounds(coordinates.x, coordinates.y, WIDTH, height);
    }

    public PilePanel(Point coordinates, int cardAmount) {
        this(coordinates);
        this.cardAmount = cardAmount;
    }

    //getters and setters

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
        setHeight(180 + cardAmount * 20);
    }

    public void incrementCardAmount(int increment) {
        cardAmount += increment;
        setHeight(height + increment * 20);
    }

    public void setHeight(int height) {
        this.height = height;
        setSize(WIDTH, height);
    }
}
