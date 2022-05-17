package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private int result;

    public User(String username, int result) {
        this.username = username;
        this.result = result;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
