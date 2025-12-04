package dao;
import java.util.ArrayList;
import database.Database;
import entities.users.User;
// validation
public class UserDAO {
    public void addUser(User user) {
        Database.users.add(user);
    }
    // method searches by usernames
    public User findByUsername(String username) {
        // Loop through all users in the database.
        for (User u : Database.users) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        // if not found return null
        return null;
    }
    // method searches by id
    public User findById(String id) {
        for (User u : Database.users) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return null;
    }
    // returns all users in database
    public ArrayList<User> getAllUsers() {
        return Database.users;
    }
}
