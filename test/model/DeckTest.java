/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 15, 2015
 * Time: 8:56:19 AM
 *
 * Project: csci205FinalProject
 * Package: model
 * File: RiverTest
 * Description: Tests the deck class of the model.
 *
 * **************************************** */
package model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yash Bhutwala
 */
public class DeckTest {

    public DeckTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of draw method, of class Deck.
     */
    @Test
    public void testDraw() {
        System.out.println("Testing Draw...");
        Deck newDeck = new Deck();

        int expResultBeforeDraw = 52;
        int resultBeforeDraw = newDeck.size();
        assertEquals(expResultBeforeDraw, resultBeforeDraw);

        Card cardToRemove = newDeck.draw();
        int expResultAfterDraw = 51;
        int resultAfterDraw = newDeck.size();
        assertEquals(expResultAfterDraw, resultAfterDraw, 0);
        System.out.println("Success!!");
    }

}
