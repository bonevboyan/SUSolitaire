package Model.Common;

import java.util.EventListener;

public interface TimerEventListener extends EventListener {
    void OnEvent(long seconds);
}
