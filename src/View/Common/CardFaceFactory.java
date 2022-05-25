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
            System.out.println("haha");
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
        switch (suit) {
            case CLUBS -> {
                return 1;
            }
            case SPADES -> {
                return 99;
            }
            case HEARTS -> {
                return 197;
            }
            case DIAMONDS -> {
                return 295;
            }
        }
        return 0;
    }

    // map X positions
    private static int getPixelXPositionByNumber(CardNumber number) {
        switch (number) {
            case ACE -> {
                return 1;
            }
            case TWO -> {
                return 74;
            }
            case THREE -> {
                return 147;
            }
            case FOUR -> {
                return 220;
            }
            case FIVE -> {
                return 293;
            }
            case SIX -> {
                return 366;
            }
            case SEVEN -> {
                return 439;
            }
            case EIGHT -> {
                return 512;
            }
            case NINE -> {
                return 585;
            }
            case TEN -> {
                return 658;
            }
            case JACK -> {
                return 731;
            }
            case QUEEN -> {
                return 804;
            }
            case KING -> {
                return 877;
            }
        }
        return 0;
    }

}