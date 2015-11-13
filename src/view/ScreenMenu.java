/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 12, 2015
 * Time: 4:24:12 PM
 *
 * Project: csci205FinalProject
 * Package: org.bigjava.poker.view
 * File: ScreenMenu
 * Description: Front screen of the game.
 *
 * **************************************** */
package view;

import java.awt.Font;
import model.Table;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 *
 * @author Morgan Eckenroth
 */
public class ScreenMenu extends BasicGameState {

    private final int state;
    private Button playButton;
    private Image menuBackground;
    private TextField nameField;
    private final Table table;
    private final String defaultName;
    private int mouseX, mouseY;
    private boolean textFocus;
    private Font font;
    private TrueTypeFont ttf;

    /**
     * Acts as the front screen for the game.
     *
     * @param state
     * @param table
     */
    public ScreenMenu(int state, Table table) {
        this.state = state;
        this.table = table;
        defaultName = "Name...";
        textFocus = false;
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
        ViewConstants.initConstants(container);
        font = new Font("Verdana", Font.BOLD, ViewConstants.fontSizeButton);
        ttf = new TrueTypeFont(font, true);
        playButton = new Button("PLAY", ViewConstants.playButtonX, ViewConstants.playButtonY, ViewConstants.playButtonWidth, ViewConstants.playButtonHeight);
        try {
            menuBackground = new Image("res/Big_Java_Poker_Project.png");
        } catch (SlickException ex) {
            System.out.println("Background image not found!");
        }
        initTextField(container);

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
        menuBackground.draw(0, 0, container.getWidth(), container.getHeight());
        playButton.drawRounded(g, ttf, 100);
        nameField.render(container, g);
        nameField.setFocus(textFocus);
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

        setTextFieldFocus();
        if (textFocus) {
            if (nameField.getText().equals(defaultName)) {
                nameField.setText("");
            }
        }

        if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (playButton.isClicked(mouseX, mouseY)) {
                table.setPlayer1Name(getNameInput());
                game.enterState(Game.PLAY_STATE, null, new VerticalSplitTransition());
            }
        }
    }

    /**
     * Determines if the player has clicked in the text field, and sets the
     * focus as needed.
     */
    private void setTextFieldFocus() {
        if (Mouse.isButtonDown(0)) {
            textFocus = mouseX > nameField.getX() && mouseX < nameField.getX() + nameField.getWidth() && mouseY > nameField.getY() && mouseY < nameField.getY() + nameField.getHeight();
        }
    }

    /**
     * Creates the text fields that are to be rendered.
     *
     * @param container
     */
    private void initTextField(GameContainer container) {
        Font font = new Font("Verdana", Font.BOLD, 20);
        TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
        nameField = new TextField(container, trueTypeFont, ViewConstants.nameFieldX, ViewConstants.nameFieldY, ViewConstants.nameFieldWidth, ViewConstants.nameFieldHeight);
        nameField.setText(defaultName);
        nameField.setBackgroundColor(Color.white);
        nameField.setBorderColor(Color.black);
        nameField.setTextColor(Color.black);
    }

    /**
     * Collects user input to the text field and sets the player's name.
     *
     * @return the players name
     */
    private String getNameInput() {
        if (nameField.getText().equals(defaultName) || nameField.getText().equals("")) {
            return "HUMAN";
        }
        return nameField.getText();

    }
}
