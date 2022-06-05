package View.Common;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardFaceFactory {

    private static final String IMAGE_PATH = "src/assets/cardfaces.png";
    private static final int CARD_WIDTH_PIXELS = 72;
    private static final int CARD_HEIGHT_PIXELS = 96;

    // paint a card face using graphics
    public static void paintCardFace(CardSuit suit, CardNumber number, Graphics g) {
        BufferedImage image = null;
        // read image from source path
        try {
            image = ImageIO.read(new File(IMAGE_PATH));
        } catch (Exception e) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.fillRect(0, 0, 40, 40);
            return;
        }

        assert image != null;

        Graphics2D g2d = (Graphics2D) g;

        // draw the image using graphics
        int pixelXPosition = getPixelXPositionByNumber(number);
        int pixelYPosition = getPixelYPositionBySuit(suit);
        g2d.drawImage(image, 0, 0, ViewConstants.CARD_WIDTH-1, ViewConstants.CARD_HEIGHT-1,
                pixelXPosition, pixelYPosition,
                pixelXPosition + CARD_WIDTH_PIXELS,
                pixelYPosition + CARD_HEIGHT_PIXELS, null);

    }

    // map Y positions
    private static int getPixelYPositionBySuit(CardSuit suit) {
        return switch (suit) {
            case CLUBS ->  1;
            case SPADES -> 99;
            case HEARTS -> 197;
            case DIAMONDS -> 295;
        };
    }

    // map X positions
    private static int getPixelXPositionByNumber(CardNumber number) {
        return switch (number) {
            case ACE -> 1;
            case TWO -> 74;
            case THREE -> 147;
            case FOUR -> 220;
            case FIVE -> 293;
            case SIX -> 366;
            case SEVEN -> 439;
            case EIGHT -> 512;
            case NINE -> 585;
            case TEN -> 658;
            case JACK -> 731;
            case QUEEN -> 804;
            case KING -> 877;
        };
    }

}