/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 12, 2015
 * Time: 4:29:55 PM
 *
 * Project: csci205FinalProject
 * Package: org.bigjava.poker.view
 * File: ScreenPlay
 * Description:
 *   Main Display screen of the play state
 * **************************************** */
package view;

import controller.TurnController;
import java.awt.Font;
import model.Player;
import model.Table;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Morgan Eckenroth
 */
public class ScreenPlay extends BasicGameState {

    private final int state;
    private Table table;
    private int mouseX, mouseY;
    private Image background, tableOverlay, drKing, woman, jamesBond;
    private Image drKingGlow, womanGlow, jamesBondGlow;
    private Image player0card1, player0card2, player1card1, player1card2, player2card1, player2card2, player3card1, player3card2;
    private Image riverCard1, riverCard2, riverCard3, riverCard4, riverCard5;
    private Button foldButton, callButton, checkButton, raiseButton, betButton, replayButton;
    private TextField betField;
    private Font fontTitle, fontButton, fontData;
    private TrueTypeFont ttfTitle, ttfButton, ttfData;
    private boolean textFocus;
    private TurnController turnController;
    private int previousRiverLength;
    private Player roundWinner;
    private boolean hasUpdatedPlayerCards;
    private Button proceedButton;

    /**
     * Main view for the game
     *
     * @param state
     * @param table
     * @param turnController
     */
    public ScreenPlay(int state, Table table, TurnController turnController) {
        this.state = state;
        this.table = table;
        this.turnController = turnController;
        roundWinner = null;
    }

    @Override
    public int getID() {
        return state;
    }

    /**
     * Creates all required objects to be passed to the GL_RENDER_SPACE
     *
     * @param container
     * @param game
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fontTitle = new Font("Verdana", Font.BOLD, ViewConstants.fontSizeTitle);
        ttfTitle = new TrueTypeFont(fontTitle, true);
        fontData = new Font("Verdana", Font.BOLD, ViewConstants.fontSizeData);
        ttfData = new TrueTypeFont(fontData, true);
        fontButton = new Font("Verdana", Font.BOLD, ViewConstants.fontSizeButton);
        ttfButton = new TrueTypeFont(fontButton, true);
        riverCard4 = null;
        riverCard5 = null;
        this.previousRiverLength = 3;
        hasUpdatedPlayerCards = false;
        initButtons(container);
        initPlayers();
        initTextField(container);
        try {
            initImages();
        } catch (SlickException ex) {
            System.out.println("No background image!");
        }
    }

    /**
     * Uses modified Java Graphics to render onto the GL_RENDER_SPACE
     *
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        drawBackground(g, container);
        drawPlayersCards(g, container);
        drawRiverCards(g, container);
        drawButtons(g);
        drawTextFields(g, container);
        if (table.getRenderWinState() && roundWinner != null) {
            displayWinner(g);
            displayReplay(g);
        }
        drawGameInfo(g);
    }

    /**
     * Updates any objects through user input and events before rendering.
     *
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouseX = Mouse.getX();
        mouseY = container.getHeight() - Mouse.getY();

        if (!table.getRenderWinState()) {
            if (turnController != null) {
                turnController.getUserInput();

            }

            setTextFieldFocus();
            if (textFocus) {
                if (betField.getText().equals("Call")) {
                    betField.setText("");
                }
            }
            initDeltaRiverCards();

        } else {
            if (!hasUpdatedPlayerCards) {
                initPlayerCardsForWin();
                hasUpdatedPlayerCards = true;
            }
            roundWinner = turnController.getWinner();
            drawPlayerCardsForWin();
            if (replayButton.isClicked(mouseX, mouseY)) {
                table.getDealer().payOut(turnController.getWinner());
                resetGameWithPersistence(container, game);
            }
        }
    }

    /**
     * Starts the game over after a player wins, and persists the players.
     *
     * @param container
     * @param game
     * @throws SlickException
     */
    private void resetGameWithPersistence(GameContainer container, StateBasedGame game) throws SlickException {
        turnController.resetPreviousMovesForPersistence();
        Table newTable = new Table(table.getPlayers());
        TurnController tC = new TurnController(newTable);
        this.table = newTable;
        this.turnController = tC;
        this.init(container, game);
        this.turnController.setScreenPlay(this);
        roundWinner = null;
        game.enterState(state);
    }

    /**
     * Draws replay button.
     *
     * @param g
     */
    private void displayReplay(Graphics g) {
        replayButton.fillRounded(g, ttfButton, 100);
    }

    /**
     * Displays win state information.
     *
     * @param g
     * @param container
     */
    private void displayWinner(Graphics g) {
        Rectangle winnersBox = new Rectangle(ViewConstants.winnersBoxX, ViewConstants.winnersBoxY, ViewConstants.winnersBoxWidth, ViewConstants.winnersBoxHeight);
        String winnersString1 = roundWinner.getName() + " wins!\n";
        String winnersString2 = table.getDealer().getPot() + " is the amount won!";

        float stringPosX1 = (winnersBox.getX() + winnersBox.getWidth() / 2) - (g.getFont().getWidth(winnersString1) / 2);
        float stringPosY1 = (winnersBox.getY() + winnersBox.getHeight() / 2) - 2 * (g.getFont().getHeight(winnersString1) / 2);
        float stringPosX2 = (winnersBox.getX() + winnersBox.getWidth() / 2) - (g.getFont().getWidth(winnersString2) / 2);
        float stringPosY2 = (winnersBox.getY() + winnersBox.getHeight() / 2) + (g.getFont().getHeight(winnersString2) / 2);

        g.setColor(Color.black);
        g.fill(winnersBox);
        g.setColor(Color.white);
        g.drawString(winnersString1, stringPosX1, stringPosY1);
        g.drawString(winnersString2, stringPosX2, stringPosY2);
    }

    /**
     * Draws info from the current game.
     *
     * @param g
     * @param container
     */
    private void drawGameInfo(Graphics g) {
        g.setFont(ttfTitle);
        g.drawString("GAME INFO", ViewConstants.gameInfoStringX, ViewConstants.gameInfoStringY);
        g.drawString("BETTING", ViewConstants.bettingStringX, ViewConstants.bettingStringY);

        g.setFont(ttfData);
        g.drawString("Money", ViewConstants.moneyX, ViewConstants.moneyY);
        g.drawString("Turn: " + table.getPlayers()[turnController.getWhoseTurn()].getName(), ViewConstants.turnStringX, ViewConstants.turnStringY);
        if (turnController != null) {
            g.drawString("Current Bet: " + table.getDealer().getCurrentBet(), ViewConstants.currentBetX, ViewConstants.currentBetY);
        }
        g.drawString(table.getPlayers()[1].getName() + ":     $" + table.getPlayers()[1].getCash(), ViewConstants.playerInfoX, ViewConstants.player1InfoY);
        g.drawString(table.getPlayers()[2].getName() + ":         $" + table.getPlayers()[2].getCash(), ViewConstants.playerInfoX, ViewConstants.player2InfoY);
        g.drawString(table.getPlayers()[3].getName() + ":               $" + table.getPlayers()[3].getCash(), ViewConstants.playerInfoX, ViewConstants.player3InfoY);
        g.drawString("You:               $" + table.getPlayers()[0].getCash(), ViewConstants.moneyStringX, ViewConstants.moneyStringY);
        g.drawString("Current Pot: " + table.getDealer().getPot(), ViewConstants.currPotStringX, ViewConstants.currPotStringY);

    }

    /**
     * Determines if the player has clicked in the text field, and sets the
     * focus as needed.
     */
    private void setTextFieldFocus() {
        if (Mouse.isButtonDown(0)) {
            textFocus = mouseX > betField.getX() && mouseX < betField.getX() + betField.getWidth() && mouseY > betField.getY() && mouseY < betField.getY() + betField.getHeight();
        }
    }

    public String getTextFieldText() {
        return betField.getText();
    }

    /**
     * Creates the button objects that are to be rendered.
     *
     * @param container
     */
    private void initButtons(GameContainer container) {
        betButton = new Button("BET", ViewConstants.betButtonX, ViewConstants.betButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        foldButton = new Button("FOLD", ViewConstants.foldButtonX, ViewConstants.foldButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        callButton = new Button("CALL", ViewConstants.callButtonX, ViewConstants.callButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        checkButton = new Button("CHECK", ViewConstants.checkButtonX, ViewConstants.checkButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        raiseButton = new Button("RAISE", ViewConstants.raiseButtonX, ViewConstants.raiseButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        replayButton = new Button("REPLAY?", ViewConstants.replayButtonX, ViewConstants.replayButtonY, ViewConstants.buttonWidth, ViewConstants.buttonHeight);
        proceedButton = new Button(table.getPlayers()[turnController.getWhoseTurn()].getLastMove(), ViewConstants.proceedButtonX, ViewConstants.proceedButtonY, ViewConstants.proceedButtonWidth, ViewConstants.proceedButtonHeight);
    }

    /**
     * Creates the text fields that are to be rendered.
     *
     * @param container
     */
    private void initTextField(GameContainer container) {
        textFocus = false;
        Font textFieldFont = new Font("Verdana", Font.BOLD, ViewConstants.fontSizeData);
        TrueTypeFont trueTypeFont = new TrueTypeFont(textFieldFont, true);
        betField = new TextField(container, trueTypeFont, ViewConstants.betFieldX, ViewConstants.betFieldY, ViewConstants.betFieldWidth, ViewConstants.betFieldHeight);
        betField.setBackgroundColor(Color.white);
        betField.setBorderColor(Color.black);
        betField.setTextColor(Color.black);
        betField.setText("Call");
    }

    /**
     * Creates the players that are to be rendered.
     */
    private void initPlayers() {
        table.getPlayers()[0].setCardsInHand(table.getDeck());
        table.getPlayers()[1].setCardsInHand(table.getDeck());
        table.getPlayers()[2].setCardsInHand(table.getDeck());
        table.getPlayers()[3].setCardsInHand(table.getDeck());
    }

    /**
     * Creates the images that are to be rendered.
     *
     * @throws SlickException
     */
    private void initImages() throws SlickException {
        background = new Image("res/Background.png");
        tableOverlay = new Image("res/Table_Hands.png");
        drKing = new Image("res/King_R.png");
        drKingGlow = new Image("res/King_R_Glow.png");
        woman = new Image("res/Woman_M.png");
        womanGlow = new Image("res/Woman_M_Glow.png");
        jamesBond = new Image("res/Bond_L.png");
        jamesBondGlow = new Image("res/Bond_L_Glow.png");
        player0card1 = new Image("res/" + table.getPlayers()[0].getCardOne().getImage());
        player0card2 = new Image("res/" + table.getPlayers()[0].getCardTwo().getImage());
        player1card1 = new Image("res/Card_Back.png");
        player1card2 = new Image("res/Card_Back.png");
        player2card1 = new Image("res/Card_Back.png");
        player2card2 = new Image("res/Card_Back.png");
        player3card1 = new Image("res/Card_Back.png");
        player3card2 = new Image("res/Card_Back.png");

        initRiverImages();
    }

    /**
     * Creates the river images that are to be rendered.
     *
     * @throws SlickException
     */
    private void initRiverImages() throws SlickException {
        riverCard1 = new Image("res/" + table.getRiver().getRiver().get(0).getImage());
        riverCard2 = new Image("res/" + table.getRiver().getRiver().get(1).getImage());
        riverCard3 = new Image("res/" + table.getRiver().getRiver().get(2).getImage());
    }

    /**
     * Creates the river images that are drawn that are to be rendered.
     *
     * @throws SlickException
     */
    private void initDeltaRiverCards() throws SlickException {
        if (previousRiverLength == 3 && table.getRiver().getSize() == 4) {
            riverCard4 = new Image("res/" + table.getRiver().getRiver().get(3).getImage());
            previousRiverLength = 4;
        }
        if (previousRiverLength == 4 && table.getRiver().getSize() == 5) {
            riverCard5 = new Image("res/" + table.getRiver().getRiver().get(4).getImage());
            previousRiverLength = 5;
        }
    }

    /**
     * Flips the players' cards to be displayed on win.
     *
     * @throws SlickException
     */
    private void initPlayerCardsForWin() throws SlickException {
        Player[] players = table.getPlayers();
        player0card1 = new Image("res/" + players[0].getCardOne().getImage());
        player0card2 = new Image("res/" + players[0].getCardTwo().getImage());
        player1card1 = new Image("res/" + players[1].getCardOne().getImage());
        player1card2 = new Image("res/" + players[1].getCardTwo().getImage());
        player2card1 = new Image("res/" + players[2].getCardOne().getImage());
        player2card2 = new Image("res/" + players[2].getCardTwo().getImage());
        player3card1 = new Image("res/" + players[3].getCardOne().getImage());
        player3card2 = new Image("res/" + players[3].getCardTwo().getImage());
    }

    private void drawPlayerCardsForWin() {
        player0card1.draw(ViewConstants.playerCard1X, ViewConstants.playerCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player0card2.draw(ViewConstants.playerCard2X, ViewConstants.playerCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player1card1.draw(ViewConstants.AIcard1X, ViewConstants.AI1cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player1card2.draw(ViewConstants.AIcard2X, ViewConstants.AI1cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player2card1.draw(ViewConstants.AIcard1X, ViewConstants.AI2cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player2card2.draw(ViewConstants.AIcard2X, ViewConstants.AI2cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player3card1.draw(ViewConstants.AIcard1X, ViewConstants.AI3cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player3card2.draw(ViewConstants.AIcard2X, ViewConstants.AI3cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);

    }

    /**
     * Draws all text fields.
     *
     * @param container
     * @param g
     */
    private void drawTextFields(Graphics g, GameContainer container) {
        betField.render(container, g);
        betField.setFocus(textFocus);
    }

    /**
     * Draws all buttons.
     *
     * @param g
     */
    private void drawButtons(Graphics g) {
        if (turnController != null) {
            if (turnController.getWhoseTurn() == 0) {
                if (turnController.getHasBetOnRound()) {
                    raiseButton.drawRounded(g, ttfButton, 100);
                    callButton.drawRounded(g, ttfButton, 100);
                    g.setColor(Color.gray);
                    checkButton.drawRounded(g, ttfButton, 100);
                    betButton.drawRounded(g, ttfButton, 100);
                    g.setColor(Color.white);
                } else {
                    checkButton.drawRounded(g, ttfButton, 100);
                    betButton.drawRounded(g, ttfButton, 100);
                    g.setColor(Color.gray);
                    raiseButton.drawRounded(g, ttfButton, 100);
                    callButton.drawRounded(g, ttfButton, 100);
                    g.setColor(Color.white);
                }
                foldButton.drawRounded(g, ttfButton, 100);
                proceedButton.fill(g, ttfData);
            } else {
                g.setColor(Color.gray);
                checkButton.drawRounded(g, ttfButton, 100);
                betButton.drawRounded(g, ttfButton, 100);
                raiseButton.drawRounded(g, ttfButton, 100);
                foldButton.drawRounded(g, ttfButton, 100);
                callButton.drawRounded(g, ttfButton, 100);
                g.setColor(Color.white);
                proceedButton.fill(g, ttfData);
            }
        }

    }

    /**
     * Draws the background.
     *
     * @param g
     * @param container
     */
    private void drawBackground(Graphics g, GameContainer container) {
        g.setBackground(new Color(7, 99, 36));
        background.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
        if (roundWinner == null) {
            if (turnController.getWhoseTurn() == TurnController.WOMANS_TURN) {
                womanGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                woman.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }
            if (turnController.getWhoseTurn() == TurnController.BONDS_TURN) {
                jamesBondGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                jamesBond.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }
            tableOverlay.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            if (turnController.getWhoseTurn() == TurnController.KINGS_TURN) {
                drKingGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                drKing.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }
        } else {
            if (roundWinner.getName().equals("Woman")) {
                womanGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                woman.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }
            if (roundWinner.getName().equals("007")) {
                jamesBondGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                jamesBond.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }
            tableOverlay.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            if (roundWinner.getName().equals("Prof. King")) {
                drKingGlow.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            } else {
                drKing.draw(0, 0, ViewConstants.backgroundImageX, ViewConstants.backgroundImageY);
            }

        }
    }

    /**
     * Draws the players' cards.
     *
     * @param g
     * @param container
     */
    private void drawPlayersCards(Graphics g, GameContainer container) {
        //Player 0 Cards
        player0card1.draw(ViewConstants.playerCard1X, ViewConstants.playerCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player0card2.draw(ViewConstants.playerCard2X, ViewConstants.playerCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);

        //Player 1 Cards
        player1card1.draw(ViewConstants.AIcard1X, ViewConstants.AI1cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player1card2.draw(ViewConstants.AIcard2X, ViewConstants.AI1cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);

        //Player 2 Cards
        player2card1.draw(ViewConstants.AIcard1X, ViewConstants.AI2cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player2card2.draw(ViewConstants.AIcard2X, ViewConstants.AI2cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);

        //Player 3 Cards
        player3card1.draw(ViewConstants.AIcard1X, ViewConstants.AI3cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        player3card2.draw(ViewConstants.AIcard2X, ViewConstants.AI3cardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
    }

    /**
     * Draws the river cards.
     *
     * @param g
     * @param container
     */
    private void drawRiverCards(Graphics g, GameContainer container) {
        int xOffs = 0;
        riverCard1.draw((int) (ViewConstants.riverCardX + xOffs), ViewConstants.riverCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        xOffs += ViewConstants.cardWidth + 3;
        riverCard2.draw((int) (ViewConstants.riverCardX + xOffs), ViewConstants.riverCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        xOffs += ViewConstants.cardWidth + 3;
        riverCard3.draw((int) (ViewConstants.riverCardX + xOffs), ViewConstants.riverCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
        xOffs += ViewConstants.cardWidth + 3;
        if (riverCard4 != null) {
            riverCard4.draw((int) (ViewConstants.riverCardX + xOffs), ViewConstants.riverCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
            xOffs += ViewConstants.cardWidth + 3;
        }
        if (riverCard5 != null) {
            riverCard5.draw((int) (ViewConstants.riverCardX + xOffs), ViewConstants.riverCardY, ViewConstants.cardWidth, ViewConstants.cardHeight);
            xOffs += ViewConstants.cardWidth + 3;
        }
    }

    public Button getFoldButton() {
        return foldButton;
    }

    public Button getCallButton() {
        return callButton;
    }

    public Button getCheckButton() {
        return checkButton;
    }

    public Button getRaiseButton() {
        return raiseButton;
    }

    public Button getBetButton() {
        return betButton;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Button getProceedButton() {
        return proceedButton;
    }
}
