/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 13, 2015
 * Time: 10:25:40 AM
 *
 * Project: csci205finalproject
 * Package: CardProject.game
 * File: Dealer
 * Description: Basic Class for a dealer made by Devon Wasson
 *
 * **************************************** */
package model;

/**
 *
 * @author Devon Wasson
 */
public class Dealer {

    private int pot;

    private int minBet;

    private int bettingRound;
    private int currentBet;

    /**
     * basic constructor for a dealer class
     */
    public Dealer() {

        minBet = 25;
        pot = 0;
        bettingRound = 0;
        currentBet = 0;

    }

    /**
     * A player gives money to the dealer to add to the pot
     *
     * @param amount of money bet
     */
    public void addToPot(int amount) {
        pot += amount;
    }

    /**
     * When the game is over, give the pot to the winning player
     *
     * @param player , whoever won
     */
    public void payOut(Player player) {
        player.win(this.pot);
        this.pot = 0;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public int getBettingRound() {
        return bettingRound;
    }

    public void setBettingRound(int bettingRound) {
        this.bettingRound = bettingRound;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int bet) {
        currentBet = bet;
    }
}
