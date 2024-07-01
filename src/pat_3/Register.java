package pat_3;
/**
 *
 * 
 */
public class Register {

 // (W3schools.com, 2023)
        private String username;
        private String password;

        public Register(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
    public boolean checkUserName() { // (W3schools.com, 2023)
    boolean containsUnderscore = false;
    for (int i = 0; i < username.length(); i++) {
        if (username.charAt(i) == '_') {
            containsUnderscore = true;
           }
    }
    return containsUnderscore && username.length() <= 5;
}
    
    public boolean checkPasswordComplexity() {
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if ("!@#$%^&*()".indexOf(c) != -1) { //!= means not equal 
                hasSpecialCharacter = true;
            }
        }
        return password.length() >= 8 && hasUpperCase && hasNumber && hasSpecialCharacter;
    }     
        
        public String registerUser() { // (W3schools.com, 2023)
            if (!checkUserName()) {
                return "Invalid username format. Must contain an underscore (_) and be no more than 5 characters.";
            } else if (!checkPasswordComplexity()) {
                return "Invalid password format. Must be at least 8 characters long and contain a capital letter, a number, and a special character.";
            } else {
                return "Registration successful!";
            }
        }
       
        public boolean loginUser() { return this.username.equals(username) && this.password.equals(password);}
        
        public String returnLoginStatus(boolean successful) { // (W3schools.com, 2023)
            if (successful) {
                return "Login successful!";
            } else {
                return "Login failed. Please check your username and password and try again.";
                }
        }
}


