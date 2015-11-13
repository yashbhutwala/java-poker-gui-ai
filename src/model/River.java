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
 * Description:  Generic river model.
 *
 * **************************************** */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * River class to model a river.
 *
 * @author Yash Bhutwala
 */
public class River {

    // River Card List
    private final List<Card> river;

    public River() {
        // River is implemeneted as an arraylist
        this.river = new ArrayList<>();
    }

    /**
     * Adds a card to the river.
     *
     * @param card
     */
    public void addCard(Card card) {
        this.river.add(card);
    }

    /**
     * Returns the current size of the river.
     *
     * @return size of the river.
     */
    public int getSize() {
        return this.river.size();
    }

    public List<Card> getRiver() {
        return this.river;
    }
}
