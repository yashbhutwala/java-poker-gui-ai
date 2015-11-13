/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 12, 2015
 * Time: 5:22:44 PM
 *
 * Project: csci205FinalProject
 * Package: org.bigjava.poker.view
 * File: Button
 * Description:
 *  Generic button class for use in any screens
 * **************************************** */
package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Morgan Eckenroth
 */
public class Button {

    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private String text;

    private int stringWidth;
    private int stringHeight;
    private final int buttonCenterX;
    private final int buttonCenterY;
    private int stringX;
    private int stringY;

    /**
     * Custom button that allows for implicit listening.
     *
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Button(String text, int x, int y, int width, int height) {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.text = text;

        buttonCenterX = this.xPos + this.width / 2;
        buttonCenterY = this.yPos + this.height / 2;
    }

    /**
     * Determines if the button was clicked.
     *
     * @param mouseX
     * @param mouseY
     * @return true if clicked, else false
     */
    public boolean isClicked(int mouseX, int mouseY) {
        if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseX > this.xPos && mouseX < this.xPos + this.width && mouseY > this.yPos && mouseY < this.yPos + this.height) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setText(String newText) {
        text = newText;
    }

    /**
     * Draws the button to the openGL context
     *
     * @param g
     * @param ttf
     */
    public void draw(Graphics g, TrueTypeFont ttf) {
        g.setFont(ttf);
        stringWidth = g.getFont().getWidth(text);
        stringHeight = g.getFont().getHeight(text);
        stringX = buttonCenterX - stringWidth / 2;
        stringY = buttonCenterY - stringHeight / 2;

        g.drawRect(xPos, yPos, width, height);
        g.drawString(text, stringX, stringY);
    }

    /**
     * Fills the button to the openGL context
     *
     * @param g
     * @param ttf
     */
    public void fill(Graphics g, TrueTypeFont ttf) {
        g.setFont(ttf);
        stringWidth = g.getFont().getWidth(text);
        stringHeight = g.getFont().getHeight(text);
        stringX = buttonCenterX - stringWidth / 2;
        stringY = buttonCenterY - stringHeight / 2;

        g.setColor(Color.black);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.white);
        g.drawString(text, stringX, stringY);
    }

    /**
     * Draws the button to the openGL context
     *
     * @param g
     * @param ttf
     * @param roundedness
     */
    public void drawRounded(Graphics g, TrueTypeFont ttf, int roundedness) {
        g.setFont(ttf);
        stringWidth = g.getFont().getWidth(text);
        stringHeight = g.getFont().getHeight(text);
        stringX = buttonCenterX - stringWidth / 2;
        stringY = buttonCenterY - stringHeight / 2;

        g.drawOval(xPos, yPos, width, height);
        g.drawString(text, stringX, stringY);
    }

    /**
     * Fill the button to the openGL context
     *
     * @param g
     * @param ttf
     * @param roundedness
     */
    public void fillRounded(Graphics g, TrueTypeFont ttf, int roundedness) {
        g.setFont(ttf);
        stringWidth = g.getFont().getWidth(text);
        stringHeight = g.getFont().getHeight(text);
        stringX = buttonCenterX - stringWidth / 2;
        stringY = buttonCenterY - stringHeight / 2;
        g.setColor(Color.black);
        g.fillOval(xPos, yPos, width, height);
        g.setColor(Color.white);
        g.drawString(text, stringX, stringY);
    }
}
