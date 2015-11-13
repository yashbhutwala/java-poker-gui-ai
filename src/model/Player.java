/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 15, 2015
 * Time: 10:16:18 AM
 *
 * Project: csci205finalproject
 * Package: model
 * File: AI
 * Description:  Generic player model.
 *
 * **************************************** */
package model;

/**
 *
 * @authors Yash Bhutwala, Morgan Eckenroth
 */
public class Player {

    // Name
    private String name;

    // Hand of Card
    private Card[] hand;

    // Current cash amount
    private int cash;

    // Current bet
    private int bet;

    private String lastMove;

    /**
     * Contains data associated to each player.
     *
     * @param name
     * @param cash
     */
    public Player(String name, int cash) {
        this.name = name;
        this.cash = cash;
        lastMove = "";
        this.hand = new Card[2];
    }

    /**
     * Resets hand for a new round.
     */
    public void resetHand() {
        this.hand = null;
        resetBet();
    }

    /**
     * Acts as a placeholder.
     *
     * @param river
     * @param dealer
     * @return
     */
    public int shouldBet(River river, Dealer dealer) {
        return 0;
    }

    /**
     * Players gets a hand.
     *
     * @param deck
     */
    public void setCardsInHand(Deck deck) {
        this.hand = new Card[]{deck.draw(), deck.draw()};
    }

    /**
     * Reset the player's bet.
     */
    public void resetBet() {
        bet = 0;
    }

    /**
     * Player pays an amount of cash.
     *
     * @param amount
     * @return amount paid
     * @throws CashException
     */
    public int payCash(int amount) throws CashException {
        if (cash - amount < 0) {
            throw new CashException(String.format("INSUFFICIENT CASH: Player in debt, cash remaining = $%d", cash));
        }
        cash -= amount;
        return amount;
    }

    /**
     * Player won money.
     *
     * @param amount
     */
    public void win(int amount) {
        cash += amount;
    }

    public String getName() {
        return name;
    }

    public Card[] getCardsInHand() {
        return hand;
    }

    public int getCash() {
        return cash;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Card getCardOne() {
        return hand[0];
    }

    public Card getCardTwo() {
        return hand[1];
    }

    public void setCardOne(Card card1) {
        hand[0] = card1;
    }

    public void setCardTwo(Card card2) {
        hand[1] = card2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public String getLastMove() {
        return lastMove;
    }
}
