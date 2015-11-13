/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 14, 2015
 * Time: 9:04:12 PM
 *
 * Project: csci205FinalProject
 * Package: model
 * File: Table
 * Description:
 *  Main model of the game, contains most things for game
 * **************************************** */
package model;

/**
 *
 * @author Morgan Eckenroth
 */
public class Table {

    private final int startingCash;
    private boolean renderWinState;
    private final Player[] players;
    private final River river;
    private final Deck deck;
    private final Dealer dealer;

    /**
     * Contains information on the current game.
     */
    public Table() {
        startingCash = 10000;
        renderWinState = false;
        players = new Player[4];
        river = new River();
        deck = new Deck();
        dealer = new Dealer();
        initRiver();
        initPlayers();
    }

    /**
     * Used for persistence.
     *
     * @param oldPlayers
     */
    public Table(Player[] oldPlayers) {
        startingCash = 10000;
        renderWinState = false;
        river = new River();
        deck = new Deck();
        dealer = new Dealer();
        this.players = oldPlayers;
        initRiver();
    }

    /**
     * Populates river.
     */
    private void initRiver() {
        river.addCard(deck.draw());
        river.addCard(deck.draw());
        river.addCard(deck.draw());
    }

    /**
     * Creates player objects.
     */
    private void initPlayers() {
        players[0] = new Player("HUMAN", startingCash);
        players[1] = new AI("Prof. King", startingCash);
        players[2] = new AI("Woman", startingCash);
        players[3] = new AI("007", startingCash);
    }

    public Player[] getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public River getRiver() {
        return river;
    }

    public boolean getRenderWinState() {
        return renderWinState;
    }

    public int getCurrentPot() {
        return dealer.getPot();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setPlayer1Name(String name) {
        players[0].setName(name);
    }

    public void setRenderWinState() {
        renderWinState = !renderWinState;
    }
}
