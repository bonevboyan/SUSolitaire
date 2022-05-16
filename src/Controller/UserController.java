package Controller;

import Model.Database;
import Model.User;
import View.StartMenu;
import View.UserDetails;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserController {
    private Database database;
    private StartMenu startMenu;
    private UserDetails userDetails;

    public UserController(StartMenu startMenu, UserDetails userDetails) {
        this.database = new Database();
        this.startMenu = startMenu;
        this.userDetails = userDetails;

        // submit user
        this.startMenu.submitUsers(e -> {
            String firstname = this.startMenu.getUsername().trim();

            // simple validations
            if(firstname.isEmpty()) {
//                JOptionPane.showMessageDialog(this.startMenu, "Username Required.", "Error",
//                        JOptionPane.ERROR_MESSAGE);
//                return;
                firstname = "guest" + Math.random() * 100;
            }

            this.database.addUser(new User(firstname, 100));
            this.database.saveUser();
            this.startMenu.reset(true);
        });

        // load users
        this.startMenu.viewUsers(e -> {
            try {
                this.userDetails.getUsers(this.database.loadUsers());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        try {
            this.startMenu.topScores(this.database.topScores());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
