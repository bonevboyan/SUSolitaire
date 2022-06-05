import Controller.UserController;
import Services.UserService;
import View.Layouts.*;
import View.Sounds.Sound;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // frame width & height
    public int FRAME_WIDTH = 1300;
    public int FRAME_HEIGHT = 1000;

    // Card layout for switching view
    private CardLayout cardLayout;

    public MainFrame() {
        super("SUSolitaire");
        cardLayout = new CardLayout();
        StartMenu startMenu = new StartMenu();
        UserDetails userDetails = new UserDetails();
        GameSelectionScreen gameSelectionScreen = new GameSelectionScreen();
        CreditsScreen creditsScreen = new CreditsScreen();
        KlondikeScreen klondikeScreen = new KlondikeScreen();
        // sets our layout as a card layout
        setLayout(cardLayout);

        // initialize user controller
        new UserController(startMenu, userDetails, klondikeScreen, new UserService());

        // adds view to card layout with unique constraints
        add(startMenu, "start menu");
        add(userDetails, "user details");
        add(gameSelectionScreen, "game selection");
        add(creditsScreen, "credits");
        add(klondikeScreen, "klondike");

        //create sound player
        Sound player = new Sound();

        // switch view according to its constraints on click
        startMenu.viewUsers(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "user details");});
        startMenu.submitUsers(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "game selection");});
        startMenu.viewCredits(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "credits");});
        userDetails.backButton(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "start menu");});
        gameSelectionScreen.backButton(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "start menu");});
        creditsScreen.backButton(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "start menu");});
        klondikeScreen.exitButton(e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "game selection");});
        gameSelectionScreen.gameButton(0, e ->
            {player.playButtonClick(); cardLayout.show(MainFrame.this.getContentPane(), "klondike");});

        // icon for our application
        ImageIcon imageIcon = new ImageIcon("src/assets/appicon.png");
        setIconImage(imageIcon.getImage());

        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
