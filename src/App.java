import View.MainFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // starts app
        SwingUtilities.invokeLater(MainFrame::new);
    }

}
