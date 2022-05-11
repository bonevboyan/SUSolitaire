package Model;

public class Score implements Comparable<Score> {
    private String username;
    private int points;

    public Score(int points, String username) {
        this.points = points;
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(Score o) {
        return o.points - this.points;
    }
}
