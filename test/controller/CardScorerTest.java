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
 * Description: Test for CardScorer utility
 *
 * **************************************** */
package controller;

import model.AI;
import model.Card;
import model.Player;
import model.River;
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
public class CardScorerTest {

    /**
     * A river object to test the AI with
     */
    private River testRiver;

    /**
     * An AI object to be tested
     */
    private Player testPlayer;

    public CardScorerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testRiver = new River();
        testPlayer = new AI("Xavier Darlington-Whit", 1000);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of scoreHand method for one pair, of class CardScorer.
     */
    @Test
    public void testOnePair() {
        System.out.println("OnePair");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.DIAMONDS, Card.Face.TEN);
        Card rCard2 = new Card(Card.Suit.HEARTS, Card.Face.TEN);
        Card rCard3 = new Card(Card.Suit.SPADES, Card.Face.EIGHT);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
        Card rCard5 = new Card(Card.Suit.SPADES, Card.Face.FIVE);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.CLUBS, Card.Face.FOUR);
        Card aCard2 = new Card(Card.Suit.HEARTS, Card.Face.TWO);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 30;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for two pair, of class CardScorer.
     */
    @Test
    public void testTwoPair() {
        System.out.println("TwoPair");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.TEN);
        Card rCard2 = new Card(Card.Suit.SPADES, Card.Face.TEN);
        Card rCard3 = new Card(Card.Suit.HEARTS, Card.Face.EIGHT);
        Card rCard4 = new Card(Card.Suit.DIAMONDS, Card.Face.EIGHT);
        Card rCard5 = new Card(Card.Suit.CLUBS, Card.Face.TWO);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.SPADES, Card.Face.THREE);
        Card aCard2 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 50;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for three of a kind, of class CardScorer.
     */
    @Test
    public void testThreeOfAKind() {
        System.out.println("ThreeKind");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.NINE);
        Card rCard2 = new Card(Card.Suit.SPADES, Card.Face.NINE);
        Card rCard3 = new Card(Card.Suit.CLUBS, Card.Face.NINE);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.SEVEN);
        Card rCard5 = new Card(Card.Suit.SPADES, Card.Face.QUEEN);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
        Card aCard2 = new Card(Card.Suit.HEARTS, Card.Face.TWO);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 69;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for a straight, of class CardScorer.
     */
    @Test
    public void testStraight() {
        System.out.println("straight");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.TWO);
        Card rCard2 = new Card(Card.Suit.CLUBS, Card.Face.THREE);
        Card rCard3 = new Card(Card.Suit.SPADES, Card.Face.FOUR);
        Card rCard4 = new Card(Card.Suit.DIAMONDS, Card.Face.FIVE);
        Card rCard5 = new Card(Card.Suit.SPADES, Card.Face.SIX);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.HEARTS, Card.Face.EIGHT);
        Card aCard2 = new Card(Card.Suit.DIAMONDS, Card.Face.ACE);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 86;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for a flush, of class CardScorer.
     */
    @Test
    public void testFlush() {
        System.out.println("Flush");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.TWO);
        Card rCard2 = new Card(Card.Suit.HEARTS, Card.Face.THREE);
        Card rCard3 = new Card(Card.Suit.HEARTS, Card.Face.FIVE);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.SEVEN);
        Card rCard5 = new Card(Card.Suit.HEARTS, Card.Face.NINE);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.DIAMONDS, Card.Face.QUEEN);
        Card aCard2 = new Card(Card.Suit.CLUBS, Card.Face.ACE);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 109;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for a full house, of class CardScorer.
     */
    @Test
    public void testFullHouse() {
        System.out.println("FullHouse");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.FIVE);
        Card rCard2 = new Card(Card.Suit.SPADES, Card.Face.FIVE);
        Card rCard3 = new Card(Card.Suit.CLUBS, Card.Face.FIVE);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.EIGHT);
        Card rCard5 = new Card(Card.Suit.SPADES, Card.Face.EIGHT);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.DIAMONDS, Card.Face.TWO);
        Card aCard2 = new Card(Card.Suit.HEARTS, Card.Face.THREE);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 125;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for four of a kind, of class CardScorer.
     */
    @Test
    public void testFourOfAKind() {
        System.out.println("FourKind");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.TEN);
        Card rCard2 = new Card(Card.Suit.CLUBS, Card.Face.TEN);
        Card rCard3 = new Card(Card.Suit.SPADES, Card.Face.TEN);
        Card rCard4 = new Card(Card.Suit.DIAMONDS, Card.Face.TEN);
        Card rCard5 = new Card(Card.Suit.SPADES, Card.Face.TWO);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.HEARTS, Card.Face.THREE);
        Card aCard2 = new Card(Card.Suit.HEARTS, Card.Face.FIVE);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 150;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for straightFlush, of class CardScorer.
     */
    @Test
    public void testStraightFlush() {
        System.out.println("StrightFlush");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.FOUR);
        Card rCard2 = new Card(Card.Suit.HEARTS, Card.Face.FIVE);
        Card rCard3 = new Card(Card.Suit.HEARTS, Card.Face.SIX);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.SEVEN);
        Card rCard5 = new Card(Card.Suit.HEARTS, Card.Face.EIGHT);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.DIAMONDS, Card.Face.JACK);
        Card aCard2 = new Card(Card.Suit.CLUBS, Card.Face.KING);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 168;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of scoreHand method for Royal Flush, of class CardScorer.
     */
    @Test
    public void testRoyalFlush() {
        System.out.println("RoyalFlush");
        // Set up the river
        Card rCard1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
        Card rCard2 = new Card(Card.Suit.HEARTS, Card.Face.KING);
        Card rCard3 = new Card(Card.Suit.HEARTS, Card.Face.QUEEN);
        Card rCard4 = new Card(Card.Suit.HEARTS, Card.Face.JACK);
        Card rCard5 = new Card(Card.Suit.CLUBS, Card.Face.TWO);
        testRiver.addCard(rCard1);
        testRiver.addCard(rCard2);
        testRiver.addCard(rCard3);
        testRiver.addCard(rCard4);
        testRiver.addCard(rCard5);
        // Set up the AI
        Card aCard1 = new Card(Card.Suit.HEARTS, Card.Face.TEN);
        Card aCard2 = new Card(Card.Suit.DIAMONDS, Card.Face.FOUR);
        testPlayer.setCardOne(aCard1);
        testPlayer.setCardTwo(aCard2);
        // test
        int expResult = 194;
        int result = CardScorer.scoreHand(testPlayer, testRiver);
        assertEquals(expResult, result);
    }

}
