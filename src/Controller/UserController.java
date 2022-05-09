package Controller;

import Model.Database;
import Model.User;
import View.Form;
import View.UserDetails;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserController {
    // database file
    private String databaseFile = "src\\data\\database.txt";
    private Database database;
    private Form form;
    private UserDetails userDetails;

    public UserController(Form form, UserDetails userDetails) {
        this.database = new Database();
        this.form = form;
        this.userDetails = userDetails;

        // submit user
        this.form.submitUsers(e -> {
            String firstname = this.form.getUsername().trim();
            //String lastname = this.form.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "Username Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } /*else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }*/
            
            this.database.addUser(new User(firstname, 100));
            this.database.saveUser();
            this.form.reset(true);
        });

        // load users
        this.form.viewUsers(e -> {
            try {
                this.userDetails.getUsers(this.database.loadUsers(new File(databaseFile)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
