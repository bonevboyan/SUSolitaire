package Controller;

import Services.UserService;
import View.Layouts.KlondikeScreen;
import View.Layouts.StartMenu;
import View.Layouts.UserDetails;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;
    private StartMenu startMenu;
    private KlondikeScreen klondikeScreen;
    private UserDetails userDetails;

    public UserController(StartMenu startMenu, UserDetails userDetails, KlondikeScreen klondikeScreen, UserService userService) {
        this.startMenu = startMenu;
        this.userDetails = userDetails;
        this.klondikeScreen = klondikeScreen;
        this.userService = userService;

        // submit user
        this.startMenu.submitUsers(e -> {
            String username = this.startMenu.getUsername().trim();

            if (username.isEmpty()) {
                username = "guest" + (int) Math.ceil(Math.random() * 100);
            } else {
                try (PrintWriter printWriter = new PrintWriter("src/assets/username.txt")) {
                    printWriter.print(username);
                } catch (FileNotFoundException ignored) {
                }
            }

            this.userService.saveName(username);


            this.startMenu.reset(true);
        });

        this.startMenu.viewUsers(e -> {
            this.userDetails.getUsers(this.userService.getAllScores());
        });

        this.startMenu.topScores(this.userService.topScores());

        this.klondikeScreen.saveResult(e -> {
            int score = klondikeScreen.getScore();

            this.userService.sendResult(score);
        });

        this.startMenu.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                startMenu.topScores(userService.topScores());
            }
        });
    }

}
