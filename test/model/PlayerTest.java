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
 * Description: Tests the player class of the model.
 *
 * **************************************** */
package model;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yash Bhutwala
 */
public class PlayerTest {

    public static final double EPSILON = 1.0E-12;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resetHand method, of class Player.
     */
    @Test
    public void testResetHand() {
        System.out.println("resetHand");
        Player testPlayer = new Player("James Bond", 1000);
        Deck deck = new Deck();
        testPlayer.setCardsInHand(deck);
        testPlayer.resetHand();
        Assert.assertArrayEquals(testPlayer.getCardsInHand(), null);
    }

    /**
     * Test of getCardsForHand method, of class Player.
     */
    @Test
    public void testSetCardsInHand() {
        System.out.println("getCardsForHand");
        Deck deck = new Deck();
        Player testPlayer = new Player("James Bond", 1000);
        testPlayer.setCardsInHand(deck);
        assertEquals(testPlayer.getCardsInHand().length, 2);
    }

    /**
     * Test of payCash method, of class Player.
     *
     * @throws model.CashException
     */
    @Test
    public void testPayCash() throws CashException {
        System.out.println("payCash");
        int payAmount = 500;
        Player testPlayer = new Player("James Bond", 1000);
        int expAmountLeft = testPlayer.getCash() - payAmount;
        testPlayer.payCash(payAmount);
        assertEquals(testPlayer.getCash(), expAmountLeft, EPSILON);
    }

    /**
     * Test of payCash method for Cash Exception.
     *
     * @throws CashException
     */
    @Test(expected = CashException.class)
    public void testPayCashException() throws CashException {
        System.out.println("payCash -- Cash Exception");
        int payAmount = 500;
        Player testPlayer = new Player("James Bond", 200);
        testPlayer.payCash(payAmount);
    }

    /**
     * Test of win method, of class Player.
     */
    @Test
    public void testWin() {
        System.out.println("win");
        int amount = 500;
        Player testPlayer = new Player("James Bond", 1000);
        double expAmount = amount + testPlayer.getCash();
        testPlayer.win(amount);
        assertEquals(expAmount, testPlayer.getCash(), EPSILON);
    }
}
