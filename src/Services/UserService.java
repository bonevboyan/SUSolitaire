package Services;

import Model.Common.Sorter;
import Model.Database.Database;
import Model.Database.Models.Score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Database db;
    private String username;

    public UserService() {
        this.db = new Database();
    }

    public void sendResult(int score) {
        try {
            this.db.saveScore(new Score(score, username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveName(String name){
        this.username = name;
    }

    //returns the scores sorted
    public List<Score> getAllScores() {
        try {
            ArrayList<Score> list = new ArrayList<>(this.db.loadScores());
            Sorter.sortList(list);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    //sends the top 5 scores to the start menu
    public List<Score> topScores() {
        return getAllScores().stream().limit(5).toList();
    }
}
