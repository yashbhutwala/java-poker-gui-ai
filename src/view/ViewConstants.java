/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 22, 2015
 * Time: 5:04:56 PM
 *
 * Project: csci205FinalProject
 * Package: view
 * File: ViewConstants
 * Description: Utility class for storing constants used by the view
 *
 * **************************************** */
package view;

import org.newdawn.slick.GameContainer;

/**
 *
 * @author Morgan Eckenroth
 */
public final class ViewConstants {

    protected static int playButtonX, playButtonY, playButtonWidth, playButtonHeight;
    protected static int nameFieldX, nameFieldY, nameFieldWidth, nameFieldHeight;
    protected static int backgroundScale;
    protected static int cardWidth, cardHeight;
    protected static int winnersBoxX, winnersBoxY, winnersBoxWidth, winnersBoxHeight;
    protected static int currentBetX, currentBetY;
    protected static int playerInfoX, player1InfoY, player2InfoY, player3InfoY;
    protected static int buttonWidth, buttonHeight;
    protected static int betButtonX, betButtonY, foldButtonX, foldButtonY, callButtonX, callButtonY, checkButtonX, checkButtonY, raiseButtonX, raiseButtonY, replayButtonX, replayButtonY;
    protected static int fontSizeTitle, fontSizeData, fontSizeButton;
    protected static int betFieldX, betFieldY, betFieldWidth, betFieldHeight;
    protected static int moneyStringX, moneyStringY;
    protected static int currPotStringX, currPotStringY;
    protected static int backgroundImageX, backgroundImageY;
    protected static int AIcard1X, AIcard2X, AI1cardY, AI2cardY, AI3cardY;
    protected static int playerCard1X, playerCard2X, playerCardY;
    protected static int riverCardX, riverCardY;
    protected static int turnStringX, turnStringY;
    protected static int gameInfoStringX, gameInfoStringY;
    protected static int moneyX, moneyY;
    protected static int bettingStringX, bettingStringY;
    protected static int proceedButtonX, proceedButtonY, proceedButtonWidth, proceedButtonHeight;

    private ViewConstants() {
    }

    protected static void initConstants(GameContainer container) {
        playButtonX = (container.getWidth() / 2) - (100 / 2);
        playButtonY = (int) (container.getHeight() * 0.90);
        playButtonWidth = 100;
        playButtonHeight = 35;

        nameFieldX = (int) (container.getWidth() / 2) - (145 / 2);
        nameFieldY = (int) (container.getHeight() * 0.80);
        nameFieldWidth = 145;
        nameFieldHeight = 30;

        backgroundScale = (int) (container.getWidth() * 0.80);
        cardWidth = (int) (backgroundScale * 0.054);
        cardHeight = (int) (container.getHeight() * 0.131);

        winnersBoxWidth = 250;
        winnersBoxHeight = 100;
        winnersBoxX = backgroundScale / 2 - winnersBoxWidth / 2;
        winnersBoxY = container.getHeight() / 2 - winnersBoxHeight / 2;

        buttonWidth = 70;
        buttonHeight = 30;

        betButtonX = (int) (container.getWidth() * 0.87);
        betButtonY = (int) (container.getHeight() * 0.62);

        foldButtonX = (int) (container.getWidth() * 0.83);
        foldButtonY = (int) (container.getHeight() * 0.70);

        callButtonX = (int) (container.getWidth() * 0.91);
        callButtonY = (int) (container.getHeight() * 0.70);

        checkButtonX = (int) (container.getWidth() * 0.83);
        checkButtonY = (int) (container.getHeight() * 0.78);

        raiseButtonX = (int) (container.getWidth() * 0.91);
        raiseButtonY = (int) (container.getHeight() * 0.78);

        replayButtonX = backgroundScale / 2 - buttonWidth / 2;
        replayButtonY = (int) (container.getHeight() * 0.60);

        fontSizeTitle = (int) (container.getWidth() * 0.012);
        fontSizeData = (int) (container.getWidth() * 0.010);
        fontSizeButton = (int) (container.getWidth() * 0.009);
        //fontSizeTitle = 20;
        //fontSizeData = 14;
        //fontSizeButton = 12;

        betFieldX = (int) (container.getWidth() * 0.835);
        betFieldY = (int) (container.getHeight() * 0.55);
        betFieldWidth = 145;
        betFieldHeight = 30;

        moneyX = (int) (container.getWidth() * 0.87);
        moneyY = (int) (container.getHeight() * 0.15);
        moneyStringX = (int) (container.getWidth() * 0.83);
        moneyStringY = (int) (container.getHeight() * 0.19);
        currPotStringX = (int) (container.getWidth() * 0.83);
        currPotStringY = (int) (container.getHeight() * 0.06);
        turnStringX = (int) (container.getWidth() * 0.83);
        turnStringY = (int) (container.getHeight() * 0.09);
        currentBetX = (int) (container.getWidth() * 0.85);
        currentBetY = (int) (container.getHeight() * 0.51);
        gameInfoStringX = (int) (container.getWidth() * 0.85);
        gameInfoStringY = (int) (container.getHeight() * 0.01);
        bettingStringX = (int) (container.getWidth() * 0.85);
        bettingStringY = (int) (container.getHeight() * 0.45);

        playerInfoX = (int) (container.getWidth() * 0.83);
        player1InfoY = (int) (container.getHeight() * 0.24);
        player2InfoY = (int) (container.getHeight() * 0.29);
        player3InfoY = (int) (container.getHeight() * 0.34);

        backgroundImageX = backgroundScale;
        backgroundImageY = container.getHeight();

        AIcard1X = (int) (backgroundScale * 0.026);
        AIcard2X = (int) (backgroundScale * 0.082);
        AI1cardY = (int) (container.getHeight() * 0.054);
        AI2cardY = (int) (container.getHeight() * 0.249);
        AI3cardY = (int) (container.getHeight() * 0.445);

        playerCard1X = (int) (ViewConstants.backgroundScale * 0.866);
        playerCard2X = (int) (ViewConstants.backgroundScale * 0.921);
        playerCardY = (int) (container.getHeight() * 0.249);

        riverCardX = (int) (ViewConstants.backgroundScale * 0.693);
        riverCardY = (int) (container.getHeight() * 0.050);

        proceedButtonWidth = 250;
        proceedButtonHeight = 100;
        proceedButtonX = (int) ((backgroundScale / 2) - (proceedButtonWidth / 2.2));
        proceedButtonY = (int) (container.getHeight() * 0.80);

    }
}
