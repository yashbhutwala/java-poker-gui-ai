/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 12, 2015
 * Time: 4:19:13 PM
 *
 * Project: csci205FinalProject
 * Package: org.bigjava.poker.view
 * File: Game
 * Description:  Base class for the game.
 *
 * **************************************** */
package view;

import controller.TurnController;
import java.awt.Dimension;
import java.awt.Toolkit;
import model.Table;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Morgan Eckenroth
 */
public class Game extends StateBasedGame {

    protected static final int MENU_STATE = 0;
    protected static final int PLAY_STATE = 1;
    protected static final float SCREEN_SCALE = 0.7f;

    private final Table table;
    private final ScreenPlay screenPlay;
    private final ScreenMenu screenMenu;
    private final TurnController turnController;

    /**
     * Main class for game itself.
     *
     * @param name
     */
    public Game(String name) {
        super(name);
        table = new Table();
        turnController = new TurnController(table);
        screenPlay = new ScreenPlay(PLAY_STATE, table, turnController);
        turnController.setScreenPlay(screenPlay);
        screenMenu = new ScreenMenu(MENU_STATE, table);
        this.addState(screenMenu);
        this.addState(screenPlay);

    }

    /**
     * Creates the states for the game.
     *
     * @param container
     * @throws SlickException
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.getState(MENU_STATE).init(container, this);
        this.getState(PLAY_STATE).init(container, this);
        this.enterState(MENU_STATE);

    }

    /**
     * Creates game and sets up the GL_CONTEXT
     *
     * @param args
     */
    public static void main(String[] args) {
        String title = "Big Java Poker";
        String version = " v1.1.0";
        AppGameContainer appgc;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * SCREEN_SCALE);
        int height = (int) (screenSize.getHeight() * SCREEN_SCALE);
        try {
            appgc = new AppGameContainer(new Game(title + version));
            appgc.setDisplayMode(width, height, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException se) {
            System.out.println("Reached critical mass!");
        }
    }
}
