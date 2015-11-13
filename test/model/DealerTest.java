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
 * Description:  Tests the dealer model.
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
public class DealerTest {

    public DealerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addToPot method, of class Dealer.
     */
    @Test
    public void testAddToPot() {
        Dealer testDealer = new Dealer();

        System.out.println("addToPot -- Pot should be 0");
        assertEquals(0, testDealer.getPot());

        System.out.println("addToPot -- Pot should now be the amount");
        int amount = 50;
        testDealer.addToPot(amount);
        assertEquals(amount, testDealer.getPot());
    }

    /**
     * Test of payOut method, of class Dealer.
     */
    @Test
    public void testPayOut() {
        Dealer testDealer = new Dealer();
        Player testPlayer = new Player("James Bond", 1000);
        int initialPlayerCash = testPlayer.getCash();

        int amountToAddToPot = 100;
        testDealer.addToPot(amountToAddToPot);
        testDealer.payOut(testPlayer);

        System.out.println("payOut -- testPlayer should have the same amount of money as the sum of its initial cash and what is added to the pot.");
        assertEquals(testPlayer.getCash(), initialPlayerCash + amountToAddToPot);

        System.out.println("payOut -- pot must be 0 after a payout");
        assertEquals(0, testDealer.getPot());
    }

}
