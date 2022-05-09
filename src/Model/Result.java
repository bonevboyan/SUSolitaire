package Model;

public class Result {
    private String username;
    private int score;

    public Result(int score, String username) {
        this.score = score;
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
