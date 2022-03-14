package Model;

public class Result {
    private int score;
    private GameMode mode;

    public Result(int score, GameMode mode) {
        this.score = score;
        this.mode = mode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }
}
