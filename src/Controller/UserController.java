package Controller;

import Services.UserService;
import View.StartMenu;
import View.UserDetails;

public class UserController {
    private UserService userService;
    private StartMenu startMenu;
    private UserDetails userDetails;

    public UserController(StartMenu startMenu, UserDetails userDetails) {
        this.startMenu = startMenu;
        this.userDetails = userDetails;
        this.userService = new UserService();

        // submit user
        this.startMenu.submitUsers(e -> {
            String username = this.startMenu.getUsername().trim();

            if(username.isEmpty()) {
                username = "guest" + Math.ceil(Math.random() * 100);
            }

            this.userService.saveName(username);

            this.startMenu.reset(true);
        });

        this.startMenu.viewUsers(e -> {
            this.userDetails.getUsers(this.userService.getAllScores());
        });

        this.startMenu.topScores(this.userService.topScores());
    }

}
