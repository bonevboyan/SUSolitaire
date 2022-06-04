package Model.GameObjects;

import Model.Common.GameTimer;
import Model.Common.ScoreEventListener;
import Model.Common.TimerEventListener;
import Model.Contracts.CardStackableCollection;
import Model.Enums.CardCollectionType;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Field {
    private List<CardStackableCollection> cardStacks;

    private Stack<Card> upStock;
    private Stack<Card> downStock;

    private Collection<ScoreEventListener> scoreEventListeners;
    private Collection<TimerEventListener> timerEventListeners;

    private Timer timer;

    private GameTimer gameTimer;

    private int score;

    public Field() {
        scoreEventListeners = new ArrayList<>();
        timerEventListeners = new ArrayList<>();

        cardStacks = new ArrayList<>();
        upStock = new Stack<>();
        downStock = new Stack<>();

        gameTimer = new GameTimer();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTimerEvent();
                increaseScore(-1);
            }
        });
        //timer.setInitialDelay(3000);
        timer.start();

        score = 0;

        Pack pack = new Pack();

        for (int i = 1; i <= 7; i++) {
            var cards = pack.getPack().subList(0, i);

            Pile pile = new Pile(cards);
            cardStacks.add(pile);

            cards.clear();
        }

        downStock.addAll(pack.getPack());

        for (int i = 0; i < 4; i++) {
            cardStacks.add(new Foundation());
        }
    }

    public void restock() {
        while (!upStock.isEmpty()) {
            downStock.push(upStock.pop());
            downStock.peek().setOpen(false);
        }

        increaseScore(-100);
    }

    public boolean openCardFromStock() {
        try {
            var card = downStock.pop();
            card.setOpen(true);
            upStock.push(card);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean moveCardFromStock(CardCollectionInfo stackInfo) {
        try {
            var stack = this.cardStacks.get(stackInfo.getIndex() + stackInfo.getStackType().index);

            var card = upStock.pop();

            var cards = new ArrayList<Card>();
            cards.add(card);

            if (stack.addCards(cards)) {
                increaseScore(5);
                return true;
            } else {
                upStock.add(card);
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    public boolean moveCards(int count, CardCollectionInfo oldStackInfo, CardCollectionInfo newStackInfo) {
        try {
            var oldStack = this.cardStacks.get(oldStackInfo.getIndex() + oldStackInfo.getStackType().index);
            var newStack = this.cardStacks.get(newStackInfo.getIndex() + newStackInfo.getStackType().index);

            var cards = oldStack.removeLastCards(count);
            if (cards == null) return false;

            if (newStack.addCards(cards)) {
                if (oldStackInfo.getStackType() == CardCollectionType.FOUNDATION && newStackInfo.getStackType() == CardCollectionType.PILE) {
                    increaseScore(-15);
                } else {
                    if (newStackInfo.getStackType() == CardCollectionType.FOUNDATION && oldStackInfo.getStackType() == CardCollectionType.PILE) {
                        increaseScore(10);
                    }
                    if (!oldStack.getCards().isEmpty()) {
                        if (!oldStack.getCards().peek().isOpen()) {
                            oldStack.getCards().peek().setOpen(true);
                            increaseScore(5);
                        }
                    }
                }

                return true;
            } else {
                oldStack.getCards().addAll(cards);
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public List<Pile> getPiles() {
        return cardStacks.subList(0, 7).stream().map(x -> (Pile) x).toList();
    }

    public Stack<Card> getDownStock() {
        return downStock;
    }

    public void subscribeToScoreEvent(ScoreEventListener eventListener) {
        scoreEventListeners.add(eventListener);
    }

    public void onScoreEvent() {
        for (ScoreEventListener eventListener : scoreEventListeners) {
            eventListener.OnEvent(score);
        }
    }

    public void subscribeToTimerEvent(TimerEventListener eventListener) {
        timerEventListeners.add(eventListener);
    }

    public void onTimerEvent() {
        for (TimerEventListener eventListener : timerEventListeners) {
            eventListener.OnEvent(gameTimer.getElapsedSeconds());
        }
    }

    public void increaseScore(int points) {
        this.score = Math.max(points + score, 0);
        onScoreEvent();
    }

    public void destroyTimers() {
        timer = new Timer(0, null);
        gameTimer = new GameTimer();
        timerEventListeners = new ArrayList<>();
        scoreEventListeners = new ArrayList<>();
    }
}
