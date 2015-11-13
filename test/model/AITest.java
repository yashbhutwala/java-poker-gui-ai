/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 19, 2015
 * Time: 6:06:30 PM
 *
 * Project: csci205finalproject
 * Package: controller
 * File: CardScorer
 * Description: Test for AI Class
 *
 * **************************************** */
package model;

import model.Card.Face;
import model.Card.Suit;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mph009
 */
public class AITest {

    /**
     * A river object to test the AI with
     */
    private River river;

    /**
     * A dealer object to test the AI with
     */
    private Dealer dealer;

    /**
     * An AI object to be tested
     */
    private AI ai;

    public AITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        river = new River();
        dealer = new Dealer();
        ai = new AI("Xavier Darlington-Whit", 1000);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shouldBet method, of class AI.
     */
    @Test
    public void testShouldBet1() {
        // Set up the river
        Card rCard1 = new Card(Suit.HEARTS, Face.NINE);
        Card rCard2 = new Card(Suit.HEARTS, Face.TEN);
        Card rCard3 = new Card(Suit.SPADES, Face.EIGHT);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.HEARTS, Face.EIGHT);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        int expResult = 200;
        int result = ai.shouldBet(river, dealer);
        assertEquals(expResult, result);
    }

    /**
     * Test of shouldBet method, of class AI.
     */
    @Test
    public void testShouldBet2() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.EIGHT);
        Card rCard2 = new Card(Suit.DIAMONDS, Face.JACK);
        Card rCard3 = new Card(Suit.HEARTS, Face.ACE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.SPADES, Face.FIVE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        int expResult = -1;
        int result = ai.shouldBet(river, dealer);
        assertEquals(expResult, result);
    }

    /**
     * Test of shouldBet method, of class AI.
     */
    @Test
    public void testShouldBet3() {
        // Set up the river
        Card rCard1 = new Card(Suit.HEARTS, Face.FOUR);
        Card rCard2 = new Card(Suit.HEARTS, Face.FIVE);
        Card rCard3 = new Card(Suit.HEARTS, Face.SIX);
        Card rCard4 = new Card(Suit.HEARTS, Face.SEVEN);
        Card rCard5 = new Card(Suit.HEARTS, Face.SEVEN);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.HEARTS, Face.THREE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        int expResult = 1000;
        int result = ai.shouldBet(river, dealer);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMultiple method, of class AI. Tests if both matches are in the
     * river.
     */
    @Test
    public void testHasMultiple1() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.TWO);
        Card rCard2 = new Card(Suit.CLUBS, Face.TWO);
        Card rCard3 = new Card(Suit.CLUBS, Face.THREE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.CLUBS, Face.FOUR);
        Card aCard2 = new Card(Suit.CLUBS, Face.FIVE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // test
        int expResult = 10;
        int result = ai.hasMultiple(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMultiple method, of class AI. Tests if the player has a card
     * and if the river has a match
     */
    @Test
    public void testHasMultiple2() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.TWO);
        Card rCard2 = new Card(Suit.CLUBS, Face.THREE);
        Card rCard3 = new Card(Suit.CLUBS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.CLUBS, Face.FIVE);
        Card aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // test
        int expResult = 10;
        int result = ai.hasMultiple(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMultiple method, of class AI. Tests if the matches are in both
     * in the hand.
     */
    @Test
    public void testHasMultiple3() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.THREE);
        Card rCard2 = new Card(Suit.CLUBS, Face.FOUR);
        Card rCard3 = new Card(Suit.CLUBS, Face.FIVE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.CLUBS, Face.TWO);
        Card aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // test
        int expResult = 10;
        int result = ai.hasMultiple(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMultiple method, of class AI. Tests if there are multiple
     * matches.
     */
    @Test
    public void testHasMultiple4() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.THREE);
        Card rCard2 = new Card(Suit.CLUBS, Face.TWO);
        Card rCard3 = new Card(Suit.CLUBS, Face.FIVE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.CLUBS, Face.TWO);
        Card aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // test
        int expResult = 30;
        int result = ai.hasMultiple(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasMultiple method, of class AI. Tests if there are multiple
     * matches of different cards.
     */
    @Test
    public void testHasMultiple5() {
        // Set up the river
        Card rCard1 = new Card(Suit.CLUBS, Face.THREE);
        Card rCard2 = new Card(Suit.CLUBS, Face.THREE);
        Card rCard3 = new Card(Suit.CLUBS, Face.FIVE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.CLUBS, Face.TWO);
        Card aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // test
        int expResult = 20;
        int result = ai.hasMultiple(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFlush method, of class AI. For a river of size 3.
     */
    @Test
    public void testHasFlushRiver3() {
        // Set up the river: 5 hearts
        Card rCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard2 = new Card(Suit.HEARTS, Face.THREE);
        Card rCard3 = new Card(Suit.HEARTS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.NINE);
        Card aCard2 = new Card(Suit.HEARTS, Face.TEN);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 10;
        int result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 4 hearts, 1 spades
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.HEARTS, Face.TWO);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 5;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 2 hearts, 2 spades, 1 diamond
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.SPADES, Face.TWO);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        aCard1 = new Card(Suit.DIAMONDS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFlush method, of class AI. For a river of size 4.
     */
    @Test
    public void testHasFlushRiver4() {
        // Set up the river: 5 hearts, 1 spades
        Card rCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard2 = new Card(Suit.HEARTS, Face.THREE);
        Card rCard3 = new Card(Suit.HEARTS, Face.FOUR);
        Card rCard4 = new Card(Suit.SPADES, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.NINE);
        Card aCard2 = new Card(Suit.HEARTS, Face.TEN);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 20;
        int result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 3 hearts, 3 spades
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.THREE);
        rCard3 = new Card(Suit.SPADES, Face.FOUR);
        rCard4 = new Card(Suit.SPADES, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 4 hearts, 2 spades
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.SPADES, Face.TWO);
        rCard4 = new Card(Suit.HEARTS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 10;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 3 hearts, 1 spade, 1 diamond, 1 clubs
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.SPADES, Face.TWO);
        rCard4 = new Card(Suit.DIAMONDS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFlush method, of class AI. For a river of size 5.
     */
    @Test
    public void testHasFlushRiver5() {
        // Set up the river: 7 hearts
        Card rCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard2 = new Card(Suit.HEARTS, Face.THREE);
        Card rCard3 = new Card(Suit.HEARTS, Face.FOUR);
        Card rCard4 = new Card(Suit.HEARTS, Face.FOUR);
        Card rCard5 = new Card(Suit.HEARTS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.NINE);
        Card aCard2 = new Card(Suit.HEARTS, Face.TEN);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 30;
        int result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 6 hearts, 1 diamond
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.THREE);
        rCard3 = new Card(Suit.HEARTS, Face.FOUR);
        rCard4 = new Card(Suit.HEARTS, Face.FOUR);
        rCard5 = new Card(Suit.HEARTS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.DIAMONDS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 30;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 5 hearts, 1 diamond, 1 spade
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.THREE);
        rCard3 = new Card(Suit.DIAMONDS, Face.FOUR);
        rCard4 = new Card(Suit.HEARTS, Face.FOUR);
        rCard5 = new Card(Suit.SPADES, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.HEARTS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 30;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 4 hearts, 2 diamonds, 1 spade
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.THREE);
        rCard3 = new Card(Suit.HEARTS, Face.FOUR);
        rCard4 = new Card(Suit.DIAMONDS, Face.FOUR);
        rCard5 = new Card(Suit.DIAMONDS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
        // Set up the river: 3 hearts, 4 diamonds
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.THREE);
        rCard3 = new Card(Suit.DIAMONDS, Face.FOUR);
        rCard4 = new Card(Suit.DIAMONDS, Face.FOUR);
        rCard5 = new Card(Suit.DIAMONDS, Face.FOUR);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.DIAMONDS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasFlush(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStraight method, of class AI. For a river of size 3.
     */
    @Test
    public void testHasStraightRiver3() {
        // Set up the river:
        Card rCard1 = new Card(Suit.HEARTS, Face.NINE);
        Card rCard2 = new Card(Suit.HEARTS, Face.TEN);
        Card rCard3 = new Card(Suit.HEARTS, Face.JACK);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.HEARTS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 2;
        int result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.FOUR);
        rCard2 = new Card(Suit.HEARTS, Face.FIVE);
        rCard3 = new Card(Suit.HEARTS, Face.SIX);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.THREE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 3;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.SIX);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.SPADES, Face.THREE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        aCard1 = new Card(Suit.DIAMONDS, Face.FOUR);
        aCard2 = new Card(Suit.SPADES, Face.FIVE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 5;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.EIGHT);
        rCard2 = new Card(Suit.HEARTS, Face.JACK);
        rCard3 = new Card(Suit.SPADES, Face.ACE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        // Set up the AI
        aCard1 = new Card(Suit.DIAMONDS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.FIVE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStraight method, of class AI. For a river of size 4.
     */
    @Test
    public void testHasStraightRiver4() {
        // Set up the river:
        Card rCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard2 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard3 = new Card(Suit.HEARTS, Face.NINE);
        Card rCard4 = new Card(Suit.SPADES, Face.TEN);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.HEARTS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 7;
        int result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.TWO);
        rCard2 = new Card(Suit.HEARTS, Face.NINE);
        rCard3 = new Card(Suit.SPADES, Face.TEN);
        rCard4 = new Card(Suit.SPADES, Face.JACK);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.SPADES, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 3;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.SIX);
        rCard2 = new Card(Suit.HEARTS, Face.TWO);
        rCard3 = new Card(Suit.SPADES, Face.THREE);
        rCard4 = new Card(Suit.HEARTS, Face.TEN);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.FOUR);
        aCard2 = new Card(Suit.SPADES, Face.FIVE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 8;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.FIVE);
        rCard2 = new Card(Suit.HEARTS, Face.EIGHT);
        rCard3 = new Card(Suit.SPADES, Face.JACK);
        rCard4 = new Card(Suit.DIAMONDS, Face.ACE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.CLUBS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStraight method, of class AI. For a river of size 5.
     */
    @Test
    public void testHasStraightRiver5() {
        // Set up the river:
        Card rCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard2 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard3 = new Card(Suit.HEARTS, Face.TWO);
        Card rCard4 = new Card(Suit.HEARTS, Face.SEVEN);
        Card rCard5 = new Card(Suit.HEARTS, Face.EIGHT);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Suit.HEARTS, Face.TWO);
        Card aCard2 = new Card(Suit.HEARTS, Face.TWO);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        int expResult = 25;
        int result = ai.hasStraight(river);
        assertEquals(expResult, result);
        // Set up the river:
        river = new River();
        rCard1 = new Card(Suit.HEARTS, Face.FOUR);
        rCard2 = new Card(Suit.HEARTS, Face.FIVE);
        rCard3 = new Card(Suit.HEARTS, Face.EIGHT);
        rCard4 = new Card(Suit.HEARTS, Face.SEVEN);
        rCard5 = new Card(Suit.HEARTS, Face.ACE);
        river.addCard(rCard1);
        river.addCard(rCard2);
        river.addCard(rCard3);
        river.addCard(rCard4);
        river.addCard(rCard5);
        // Set up the AI
        aCard1 = new Card(Suit.HEARTS, Face.TWO);
        aCard2 = new Card(Suit.DIAMONDS, Face.THREE);
        ai.setCardOne(aCard1);
        ai.setCardTwo(aCard2);
        // Test
        expResult = 0;
        result = ai.hasStraight(river);
        assertEquals(expResult, result);
    }

}
