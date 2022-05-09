package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Form extends JPanel {

    private JTextField usernameField;
    //private JTextField lastNameField;

    private JButton addButton;
    private JButton viewButton;

    public Form() {
        JLabel title = new JLabel("SUSolitaire");
        title.setForeground(Color.white);

        setBackground(new Color(0, 81, 0));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setForeground(Color.white);
        //JLabel lastnameLabel = new JLabel("Last Name: ");

        usernameField = new JTextField(25);
        //lastNameField = new JTextField(25);

        addButton = new JButton("Add User");
        addButton.setPreferredSize(new Dimension(278, 40));
        viewButton = new JButton("View All Users");
        viewButton.setPreferredSize(new Dimension(278, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        add(title, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(usernameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        add(usernameField, gridBagConstraints);

        //gridBagConstraints.gridx = 0;
        //gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        //add(lastnameLabel, gridBagConstraints);

        //gridBagConstraints.gridx = 0;
        //gridBagConstraints.gridy = 3;

        //add(lastNameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = buttonInset;

        add(addButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = buttonInset;

        add(viewButton, gridBagConstraints);
    }

    // getters
    public String getUsername() {
        return usernameField.getText();
    }

//    public String getLastname() {
//        return lastNameField.getText();
//    }

    public void submitUsers(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void viewUsers(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            usernameField.setText("");
            //lastNameField.setText("");
        }
    }
}
