package Services;

import Model.Database;
import Model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class UserService {
    private final Database db;
    private String username;

    public UserService() {
        this.db = new Database();
    }

    public void sendResult(int score){
        this.db.addUser(new User(username, score));
        this.db.saveUser();
    }

    public void saveName(String name){
        this.username = name;
    }

    public Object[] getAllScores() {
        return this.db.loadUsers();
    }

    public Object[] topScores() {
        Object[] scores = this.db.loadUsers();

        scores = Arrays.stream(scores).sorted().limit(5).toArray();

        Collections.reverse(Arrays.asList(scores));

        return scores;
    }
}
