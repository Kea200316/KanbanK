package pat_3;

import javax.swing.JOptionPane;

public class PAT_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //register and login the user,returning their name and surname
        String[]nameAndsurname=registerAndLogin();
        String name=nameAndsurname[0];
        String surname=nameAndsurname[1];
        
        Task taskManager=new Task();
        taskManager.runTaskManagement(name,surname);
    }

    public static String[] registerAndLogin() { // (W3schools.com, 2023)

        boolean isLoggedIn = false;
        String name;
        String surname;
        int userRegister = 0;

        name = JOptionPane.showInputDialog(null, "\tEnter Name:", "First Name", JOptionPane.PLAIN_MESSAGE);
        surname = JOptionPane.showInputDialog(null, "\tEnter Surname:", "Last name", JOptionPane.PLAIN_MESSAGE);

        while (!isLoggedIn) {
            String decisionString = JOptionPane.showInputDialog(null,
                    "Enter 1 to register a new account, or 2 to login:");
            int Choice = Integer.parseInt(decisionString);

            if (Choice == 1) {
                userRegister++;

                String Username = JOptionPane.showInputDialog(null,
                        "Enter a username (5 or fewer characters, containing an underscore):", "Username", JOptionPane.PLAIN_MESSAGE);

                String Password = JOptionPane.showInputDialog(null,
                        "Enter a password (8 or more characters, containing a digit, capital letter, and special character):");

                Register register = new Register(Username, Password);
                String registrationStatus = register.registerUser();

                if (registrationStatus.contains("successful")) {
                    JOptionPane.showMessageDialog(null, registrationStatus, "Register", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. " + registrationStatus, "Register", JOptionPane.ERROR_MESSAGE);
                }
            } else if (Choice == 2 || Choice == 11) {
                if (Choice == 11) {
                    isLoggedIn = true; // Skip login process 
                } else if (userRegister >= 1) {
                    String username = JOptionPane.showInputDialog(null, "Enter your username:", "Login", JOptionPane.PLAIN_MESSAGE);
                    String password = JOptionPane.showInputDialog(null, "Enter your password:", "Login", JOptionPane.PLAIN_MESSAGE);

                    Register Login = new Register(username, password);
                    boolean loginStatus = Login.loginUser();

                    if (loginStatus) {
                        isLoggedIn = true;
                        JOptionPane.showMessageDialog(null, Login.returnLoginStatus(true), "Login", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, Login.returnLoginStatus(false), "Login", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have not registered yet. Please register first.", "Login", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "You have entered an invalid input. Please try again.");
            }
        }

        JOptionPane.showMessageDialog(null, "Welcome " + name + " " + surname + ", it is great to see you again!");
        return new String[]{name, surname};
    }
}
