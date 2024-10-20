import guis.LoginFormGUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFormGUI().setVisible(true));
    }
}