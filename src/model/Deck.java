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
 * Description:  Generic collection of cards.
 *
 * **************************************** */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents a Deck of Cards
 *
 * @author brk009; Changes made by ymb002
 *
 */
public class Deck extends ArrayList<Card> {

    //random number generator
    private final Random randomNumberGenerator;

    /**
     * Creates a Deck of Cards using an Arraylist
     */
    public Deck() {
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Face f : Card.Face.values()) {
                this.add(new Card(s, f));
            }
        }
        randomNumberGenerator = new Random();
    }

    /**
     * Draws a card from the Deck, removes it from the arraylist, and returns it
     *
     * @return the drawn card
     */
    public Card draw() {
        if (this.size() > 1) {
            Card drawnCard = this.get(randomNumberGenerator.nextInt(this.size()));
            this.remove(drawnCard);
            return drawnCard;
        }
        return null;
    }

}
