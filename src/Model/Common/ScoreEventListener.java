package Model.Common;

import java.util.EventListener;

public interface ScoreEventListener extends EventListener {
    void OnEvent(int score);
}
