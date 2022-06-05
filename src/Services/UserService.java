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

    public void sendResult(int score){
        try{
            this.db.saveScore(new Score(score, username));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveName(String name){
        this.username = name;
    }

    public List<Score> getAllScores() {
        try {
            return this.db.loadScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Score> topScores() {
        ArrayList<Score> list = new ArrayList<>(getAllScores());
        Sorter.sortList(list);
        return list.stream().limit(5).toList();
    }
}
