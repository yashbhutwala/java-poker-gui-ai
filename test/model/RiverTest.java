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
 * Description: Tests the river class of the model.
 *
 * **************************************** */
package model;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Morgan Eckenroth
 */
public class RiverTest {

    private River river;
    private Deck deck;

    public RiverTest() {
    }

    @Before
    public void setUp() {
        river = new River();
        deck = new Deck();
    }

    @After
    public void tearDown() {
        river = null;
    }

    /**
     * Test of addCard method, of class River.
     */
    @Test
    public void testAddCard() {
        assertTrue(river.getSize() == 0);
        river.addCard(deck.draw());
        assertTrue(river.getSize() == 1);
    }
}
