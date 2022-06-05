package Model.GameObjects;

import Model.Common.EndGameListener;
import Model.Common.GameTimer;
import Model.Common.ScoreEventListener;
import Model.Common.TimerEventListener;
import Model.Contracts.CardStackableCollection;
import Model.Enums.CardCollectionType;
import Model.Enums.CardNumber;

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
    private EndGameListener endGameListener;

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

        timer = new Timer(1000, e -> {
            onTimerEvent();
            increaseScore(-3);
        });
        timer.start();

        score = 0;

        //add cards to piles
        Pack pack = new Pack();

        for (int i = 1; i <= 7; i++) {
            var cards = pack.getPack().subList(0, i);

            Pile pile = new Pile(cards);
            cardStacks.add(pile);

            cards.clear();
        }

        //the remaining cards go to the down stock
        downStock.addAll(pack.getPack());

        //create foundations
        for (int i = 0; i < 4; i++) {
            cardStacks.add(new Foundation());
        }
    }

    //return the cards from the up stock to the down stock
    public void restock() {
        while (!upStock.isEmpty()) {
            downStock.push(upStock.pop());
            downStock.peek().setOpen(false);
        }

        increaseScore(-100);
    }

    //returns if the move was a success
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

    //returns if the move was a success
    public boolean moveCardFromStock(CardCollectionInfo stackInfo) {
        try {
            //gets needed pile or foundation
            var stack = this.cardStacks.get(stackInfo.getIndex() + stackInfo.getStackType().getIndex());

            var card = upStock.pop();

            var cards = new ArrayList<Card>();
            cards.add(card);

            if (stack.addCards(cards)) {
                increaseScore(50);
                checkVictory();
                return true;
            } else {
                //undo changes if move is illegal
                upStock.add(card);
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    //returns if the move was a success
    public boolean moveCards(int count, CardCollectionInfo oldStackInfo, CardCollectionInfo newStackInfo) {
        try {
            //gets needed piles or foundations
            var oldStack = this.cardStacks.get(oldStackInfo.getIndex() + oldStackInfo.getStackType().getIndex());
            var newStack = this.cardStacks.get(newStackInfo.getIndex() + newStackInfo.getStackType().getIndex());

            var cards = oldStack.removeLastCards(count);
            if (cards == null) return false;

            if (newStack.addCards(cards)) {
                if (oldStackInfo.getStackType() == CardCollectionType.FOUNDATION && newStackInfo.getStackType() == CardCollectionType.PILE) {
                    increaseScore(-50);
                } else {
                    if (newStackInfo.getStackType() == CardCollectionType.FOUNDATION && oldStackInfo.getStackType() == CardCollectionType.PILE) {
                        increaseScore(100);
                    }
                    if (!oldStack.getCards().isEmpty()) {
                        if (!oldStack.getCards().peek().isOpen()) {
                            oldStack.getCards().peek().setOpen(true);
                            increaseScore(50);
                        }
                    }
                    checkVictory();
                }

                return true;
            } else {
                //undo changes if move is illegal
                oldStack.getCards().addAll(cards);
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public void checkVictory() {
        //stops if game isn't won
        try {
            for (int i = CardCollectionType.FOUNDATION.getIndex(); i < cardStacks.size(); i++) {
                if (cardStacks.get(i).getCards().peek().getCardNumber() != CardNumber.KING)
                    return;
            }
        } catch (EmptyStackException e) {
            return;
        }

        //if game is won:
        onEndGameEvent();
    }

    //getters
    public List<Pile> getPiles() {
        return cardStacks.subList(0, 7).stream().map(x -> (Pile) x).toList();
    }

    public Stack<Card> getDownStock() {
        return downStock;
    }

    //set events to communicate with view for score, timer and end of game
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

    public void subscribeToEndGameEvent(EndGameListener endGameListener) {
        this.endGameListener = endGameListener;
    }

    public void onEndGameEvent() {
        endGameListener.OnEvent();
    }

    public void increaseScore(int points) {
        this.score = Math.max(points + score, 0);
        onScoreEvent();
    }

    //stops the timers
    public void destroyTimers() {
        timer = new Timer(0, null);
        gameTimer = new GameTimer();
        timerEventListeners = new ArrayList<>();
        scoreEventListeners = new ArrayList<>();
    }
}
