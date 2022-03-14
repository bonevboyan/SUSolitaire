package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Result> result;

    public User() {
        // empty constructor
    }

    public User(String username) {
        this.username = username;
        this.result = new ArrayList<>();
    }

    public User(String username, Result result) {
        this(username);
        this.result.add(result);
    }

    // getters
    public String getUsername() {
        return username;
    }

    public List<Result> getResult() {
        return result;
    }

    public void addResult(Result result) {
        this.result.add(result);
    }
}
