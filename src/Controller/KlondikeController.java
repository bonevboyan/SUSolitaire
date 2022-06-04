package Controller;

import Services.UserService;
import View.Layouts.KlondikeScreen;

public class KlondikeController {
    private UserService userService;
    private KlondikeScreen klondikeScreen;
    private int score;

    public KlondikeController(KlondikeScreen klondikeScreen) {
        this.userService = new UserService();
        this.klondikeScreen = klondikeScreen;
        score = 0;


    }
}
