package fxml_helloworld;

import java.util.ArrayList;
import java.util.List;

public class Authentication {
    private List<User> users;

    public Authentication() {
        users = new ArrayList<>();
        users.add(new User("user1", "pass1")); // Menggunakan konstruktor dengan username dan password
        users.add(new User("user2", "pass2")); // Menggunakan konstruktor dengan username dan password
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
