package Controller;

import Services.UserService;
import View.Layouts.KlondikeScreen;

public class KlondikeController {
    private UserService userService;
    private KlondikeScreen klondikeScreen;
    private int score;

    public KlondikeController(KlondikeScreen klondikeScreen){
        this.userService = new UserService();
        this.klondikeScreen = klondikeScreen;
        score = 0;

        this.klondikeScreen.minusButton(e -> {
            score -= 5;
            this.klondikeScreen.setScore(score);
        });

        this.klondikeScreen.plusButton(e -> {
            score += 5;
            this.klondikeScreen.setScore(score);
            if(score >= 100){
                userService.sendResult(score);
            }
        });
    }
}
