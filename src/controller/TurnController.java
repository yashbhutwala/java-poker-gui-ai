/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 15, 2015
 * Time: 10:48:30 AM
 *
 * Project: csci205FinalProject
 * Package: controller
 * File: TurnController
 * Description:
 *  Main source of game logic, will ping each player for move and perform them.
 * **************************************** */
package controller;

import java.util.IllegalFormatConversionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CashException;
import model.Player;
import model.Table;
import view.ScreenPlay;

/**
 *
 * @author Morgan Eckenroth
 */
public class TurnController {

    public static int KINGS_TURN = 1;
    public static int WOMANS_TURN = 2;
    public static int BONDS_TURN = 3;

    private final Table table;
    private ScreenPlay sP;
    private int whoseTurn;
    private int roundNumber;
    private boolean roundHasBet;
    private int checkCount;
    private int foldCount;
    private int betCount;
    private Player winner;

    /**
     * Acts as the main source of game logic for the program. Controllers the
     * flow of the game and can determine a winner.
     *
     * @param table
     */
    public TurnController(Table table) {
        this.table = table;
        this.sP = null;
        whoseTurn = 0;
        roundNumber = 0;
        checkCount = 0;
        betCount = 0;
        foldCount = 0;
        roundHasBet = false;
    }

    public void setScreenPlay(ScreenPlay sP) {
        this.sP = sP;
    }

    /**
     * Ends the current player's turn and advances the round, going to the next
     * round if needed.
     */
    private void endTurn() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
        }

        whoseTurn++;
        sP.getProceedButton().setText(table.getPlayers()[whoseTurn - 1].getName() + ": " + table.getPlayers()[whoseTurn - 1].getLastMove());
        if (foldCount >= table.getPlayers().length - 1) {
            for (Player p : table.getPlayers()) {
                if (!p.getLastMove().equals("FOLD")) {
                    winner = p;
                }
            }
            table.setRenderWinState();
            roundNumber = 0;
        } else if (checkCount == table.getPlayers().length) {
            winner = table.getPlayers()[0];
            table.setRenderWinState();
            roundNumber = 0;
        } else if (roundNumber >= 3) {
            table.setRenderWinState();
            roundNumber = 0;
        }
        if (betCount + foldCount + checkCount >= table.getPlayers().length) {
            if (roundNumber == 1 || roundNumber == 2) {
                table.getRiver().addCard(table.getDeck().draw());
            }
            resetRoundData();

        }
        if (whoseTurn == table.getPlayers().length) {
            whoseTurn = 0;
        }

    }

    private void resetRoundData() {
        checkCount = 0;
        betCount = 0;
        whoseTurn = 0;
        foldCount = 0;
        roundHasBet = false;
        roundNumber++;
        resetPreviousMoves();
    }

    /**
     * Determines the winner of the game.
     *
     * @return player with best hand
     */
    public Player getWinner() {
        if (winner == null) {
            int highScore = 0;
            for (Player player : table.getPlayers()) {
                int currentScore = CardScorer.scoreHand(player, table.getRiver());
                if (player.getLastMove().equals("FOLD")) {
                    currentScore = 0;
                }
                if (!player.getLastMove().equals("FOLD") && winner == null) {
                    winner = player;
                    highScore = currentScore;
                } else if (currentScore > highScore && !player.getLastMove().equals("FOLD")) {
                    highScore = currentScore;
                    winner = player;
                }
            }
        }
        return winner;
    }

    /**
     * Clears the log of previous moves per round.
     */
    private void resetPreviousMoves() {
        for (Player player : table.getPlayers()) {
            if (!player.getLastMove().equals("FOLD")) {
                player.setLastMove("");
            }
        }
    }

    /**
     * Clears the log of previous moves per round.
     */
    public void resetPreviousMovesForPersistence() {
        for (Player player : table.getPlayers()) {
            player.setLastMove("");
        }
    }

    /**
     * Collects input from the user and AI and acts accordingly.
     */
    public void getUserInput() {
        if (!table.getPlayers()[whoseTurn].getLastMove().equals("FOLD")) {
            if (roundNumber == 0) {
                setInitialPot();
                roundNumber++;
            } else {
                if (whoseTurn == 0) {
                    getHumanInput();
                } else {
                    getAIInput();
                }
            }
        } else {
            foldCount++;
            endTurn();
        }
    }

    /**
     * Collects input from the AI and acts accordingly.
     */
    private void getAIInput() {
        int toBet = table.getPlayers()[whoseTurn].shouldBet(table.getRiver(), table.getDealer());
        performAction(toBet);

    }

    /**
     * Collects input from the user and acts accordingly.
     */
    private void getHumanInput() {
        if (sP != null) {
            if (sP.getBetButton().isClicked(sP.getMouseX(), sP.getMouseY())) {
                performHumanBet();
            }
            if (sP.getCallButton().isClicked(sP.getMouseX(), sP.getMouseY())) {
                if (roundHasBet) {
                    performCall();
                }
            }
            if (sP.getRaiseButton().isClicked(sP.getMouseX(), sP.getMouseY())) {
                performHumanRaise();
            }
            if (sP.getCheckButton().isClicked(sP.getMouseX(), sP.getMouseY())) {
                if (!roundHasBet) {
                    performCheck();
                }
            }
            if (sP.getFoldButton().isClicked(sP.getMouseX(), sP.getMouseY())) {
                performFold();
            }
        }
    }

    /**
     * Performs all steps to raise the bet by the player.
     */
    private void performHumanRaise() {
        if (roundHasBet) {
            int bet;
            try {
                table.getPlayers()[whoseTurn].setBet(Integer.parseInt(sP.getTextFieldText()));
                bet = table.getPlayers()[whoseTurn].getBet();
                if (bet < table.getDealer().getCurrentBet()) {
                    bet = table.getDealer().getCurrentBet();
                    if (table.getPlayers()[whoseTurn].getCash() < bet) {
                        bet = table.getPlayers()[whoseTurn].getCash();
                    }
                }
            } catch (NumberFormatException nfex) {
                bet = 0;
            }
            performRaise(bet);
        }
    }

    /**
     * Performs all steps to add a bet to the table.
     */
    private void performHumanBet() {
        if (!roundHasBet) {
            int bet;
            try {
                table.getPlayers()[whoseTurn].setBet(Integer.parseInt(sP.getTextFieldText()));
                bet = table.getPlayers()[whoseTurn].getBet();
                if (bet < table.getDealer().getCurrentBet()) {
                    bet = table.getDealer().getCurrentBet();
                    if (table.getPlayers()[whoseTurn].getCash() < bet) {
                        bet = table.getPlayers()[whoseTurn].getCash();
                    }
                }
            } catch (NumberFormatException nfex) {
                bet = table.getDealer().getCurrentBet();
                if (bet < table.getDealer().getCurrentBet()) {
                    bet = table.getDealer().getCurrentBet();
                    if (table.getPlayers()[whoseTurn].getCash() < bet) {
                        bet = table.getPlayers()[whoseTurn].getCash();
                    }
                }
            }
            performBet(bet);
        }
    }

    /**
     * Collects the initial bets from all players before the game starts.
     */
    private void setInitialPot() {
        for (Player player : table.getPlayers()) {
            player.setBet(50);
            try {
                int bet = player.payCash(player.getBet());
                table.getDealer().addToPot(bet);
            } catch (CashException ex) {
                try {
                    int bet = player.payCash(player.getCash());
                    table.getDealer().addToPot(bet);
                } catch (CashException ex1) {
                }

            }
            //endTurn();
        }
    }

    /**
     * Performs the steps to bet assuming all checks met.
     *
     * @param betAmount
     */
    private void performBet(int betAmount) {
        try {
            if (betAmount > table.getPlayers()[whoseTurn].getCash()) {
                betAmount = table.getPlayers()[whoseTurn].getCash();
            }
            table.getDealer().addToPot(table.getPlayers()[whoseTurn].payCash(betAmount));
            roundHasBet = true;
            table.getDealer().setCurrentBet(betAmount);

        } catch (IllegalFormatConversionException | CashException | NumberFormatException nfex) {
            betAmount = 0;
            try {
                table.getDealer().addToPot(table.getPlayers()[whoseTurn].payCash(betAmount));
            } catch (CashException ex) {
            }
            roundHasBet = true;
            table.getDealer().setCurrentBet(betAmount);
        }
        table.getPlayers()[whoseTurn].setLastMove("BET");
        checkCount = 0;
        betCount++;
        endTurn();
    }

    /**
     * Performs a call on the table.
     */
    private void performCall() {
        try {
            int currBet = table.getDealer().getCurrentBet();
            if (currBet > table.getPlayers()[whoseTurn].getCash()) {
                currBet = table.getPlayers()[whoseTurn].getCash();
            }
            table.getDealer().setCurrentBet(currBet);
            table.getDealer().addToPot(table.getPlayers()[whoseTurn].payCash(currBet));
        } catch (IllegalFormatConversionException | CashException | NumberFormatException nfex) {
            System.out.println("Not a proper dollar amount!");
        }
        betCount++;

        table.getPlayers()[whoseTurn].setLastMove("CALL");
        endTurn();
    }

    /**
     * Performs a raise on the table.
     */
    private void performRaise(int bet) {
        try {
            table.getPlayers()[whoseTurn].setBet(bet);
            if (bet > table.getPlayers()[whoseTurn].getCash()) {
                bet = table.getPlayers()[whoseTurn].getCash();
            }
            int playerPay = table.getPlayers()[whoseTurn].payCash(bet);
            table.getDealer().addToPot(playerPay);
            table.getPlayers()[whoseTurn].setLastMove("RAISE");
            table.getDealer().setCurrentBet(bet);
        } catch (IllegalFormatConversionException | CashException | NumberFormatException nfex) {
            System.out.println("Not a proper dollar amount!");
        }

        betCount = 1;
        endTurn();
    }

    /**
     * Performs a check on the table.
     */
    private void performCheck() {
        table.getPlayers()[whoseTurn].setLastMove("CHECK");
        checkCount++;
        endTurn();
    }

    /**
     * Performs a fold on the table.
     */
    private void performFold() {
        table.getPlayers()[whoseTurn].setLastMove("FOLD");
        foldCount++;
        endTurn();
    }

    /**
     * Determines what moves can be played at the time and does what it can to
     * resolve input issues.
     */
    private void performAction(int bet) {
        if (bet <= 0) {
            performFold();
        } else if (bet <= 10) {
            if (roundHasBet) {
                performCall();
            } else {
                performCheck();
            }
        } else {
            if (roundHasBet) {
                if (bet > table.getDealer().getCurrentBet()) {
                    performRaise(bet);
                } else {
                    performCall();
                }
            } else {
                performBet(bet);
            }
        }
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public boolean getHasBetOnRound() {
        return roundHasBet;
    }
}
