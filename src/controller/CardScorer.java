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
 * Description: determines a score for a 7-card poker hand
 *
 * **************************************** */
package controller;

import java.util.Arrays;
import model.Card;
import model.Player;
import model.River;

/**
 *
 * @author Devon Wasson
 */
public class CardScorer {

    /**
     * core method for scoring a hand
     *
     * @param player who is being scored
     * @param river to see the cards a player has
     * @return an int that represents how good a players hand is
     */
    public static int scoreHand(Player player, River river) {

        Card[] cardList = new Card[7]; //card list with all cards
        cardList[0] = (player.getCardOne());
        cardList[1] = (player.getCardTwo());
        cardList[2] = (river.getRiver().get(0));
        cardList[3] = (river.getRiver().get(1));
        cardList[4] = (river.getRiver().get(2));
        cardList[5] = (river.getRiver().get(3));
        cardList[6] = (river.getRiver().get(4));

        Arrays.sort(cardList); //sort the list of cards from value high to low
        // witout regard to suit

        int hasRoyalFlush = checkRoyalFlush(cardList); //check to see if has royal flush
        int hasStraightFlush = checkStraightFlush(cardList); //ect...
        int hasFourOfAKind = checkFourOfAKind(cardList);
        int hasFullHouse = checkFullHouse(cardList);
        int hasFlush = checkFlush(cardList);
        int hasStraight = checkStraight(cardList);
        int hasThreeOfAKind = checkThreeOfAKind(cardList);
        int hasTwoPair = checkTwoPair(cardList);
        int hasOnePair = checkOnePair(cardList);

        if (hasRoyalFlush != 0) {  //if the player has a royal flush...
            return hasRoyalFlush; //return that return value
        }
        //this continues for all hands, in order of how good the hand is
        if (hasStraightFlush != 0) {
            return hasStraightFlush;
        }
        if (hasFourOfAKind != 0) {
            return hasFourOfAKind;
        }
        if (hasFullHouse != 0) {
            return hasFullHouse;
        }
        if (hasFlush != 0) {
            return hasFlush;
        }
        if (hasStraight != 0) {
            return hasStraight;
        }
        if (hasThreeOfAKind != 0) {
            return hasThreeOfAKind;
        }
        if (hasTwoPair != 0) {
            return hasTwoPair;
        }
        if (hasOnePair != 0) {
            return hasOnePair;
        }

        return cardList[0].getFace().getValue(); //if the player has nothing in their hand, return their best card
    }

    /**
     * checks to see if there is a royal flush in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkRoyalFlush(Card[] cardList) {
        //if the highest 5 return a royal straight and are all the same suit
        if (cardList[0].getFace().getValue() == 14 && cardList[1].getFace().getValue() == 13 && cardList[2].getFace().getValue() == 12 && cardList[3].getFace().getValue() == 11 && cardList[4].getFace().getValue() == 10
            && cardList[0].getSuitName().equals(cardList[1].getSuitName()) && cardList[1].getSuitName().equals(cardList[2].getSuitName()) && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName())) {
            return 180 + cardList[0].getFace().getValue();
        }
        //if the middle 5 return a royal straight and are all the same suit
        if (cardList[1].getFace().getValue() == 14 && cardList[2].getFace().getValue() == 13 && cardList[3].getFace().getValue() == 12 && cardList[4].getFace().getValue() == 11 && cardList[5].getFace().getValue() == 10
            && cardList[1].getSuitName().equals(cardList[2].getSuitName()) && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName()) && cardList[4].getSuitName().equals(cardList[5].getSuitName())) {
            return 180 + cardList[1].getFace().getValue();
        }
        //if the lowest 5 return a royal straight and are all the same suit
        if (cardList[2].getFace().getValue() == 14 && cardList[3].getFace().getValue() == 13 && cardList[4].getFace().getValue() == 12 && cardList[5].getFace().getValue() == 11 && cardList[6].getFace().getValue() == 10
            && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName()) && cardList[4].getSuitName().equals(cardList[5].getSuitName()) && cardList[5].getSuitName().equals(cardList[6].getSuitName())) {
            return 180 + cardList[2].getFace().getValue();
        }
        return 0;
    }

    /**
     * checks to see if the hand has a straight flush
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkStraightFlush(Card[] cardList) {
        //if the highest 5 return a straight and are all the same suit
        if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue() + 1 && cardList[1].getFace().getValue() == cardList[2].getFace().getValue() + 1 && cardList[2].getFace().getValue() == cardList[3].getFace().getValue() + 1 && cardList[3].getFace().getValue() == cardList[4].getFace().getValue() + 1
            && cardList[0].getSuitName().equals(cardList[1].getSuitName()) && cardList[1].getSuitName().equals(cardList[2].getSuitName()) && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName())) {
            return 160 + cardList[0].getFace().getValue();
        }
        //if the middle 5 return a straight and are all the same suit
        if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue() + 1 && cardList[2].getFace().getValue() == cardList[3].getFace().getValue() + 1 && cardList[3].getFace().getValue() == cardList[4].getFace().getValue() + 1 && cardList[4].getFace().getValue() == cardList[5].getFace().getValue() + 1
            && cardList[1].getSuitName().equals(cardList[2].getSuitName()) && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName()) && cardList[4].getSuitName().equals(cardList[5].getSuitName())) {
            return 160 + cardList[1].getFace().getValue();
        }
        //if the lowest 5 return a straight and are all the same suit
        if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue() + 1 && cardList[3].getFace().getValue() == cardList[4].getFace().getValue() + 1 && cardList[4].getFace().getValue() == cardList[5].getFace().getValue() + 1 && cardList[5].getFace().getValue() == cardList[6].getFace().getValue() + 1
            && cardList[2].getSuitName().equals(cardList[3].getSuitName()) && cardList[3].getSuitName().equals(cardList[4].getSuitName()) && cardList[4].getSuitName().equals(cardList[5].getSuitName()) && cardList[5].getSuitName().equals(cardList[6].getSuitName())) {
            return 160 + cardList[2].getFace().getValue();
        }
        return 0;
    }

    /**
     * checks to see if the hand has four of a kind
     *
     * @param cardList
     * @return a constant to score the hand
     *
     */
    private static int checkFourOfAKind(Card[] cardList) {
        //if the higest four are the same value
        if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue() && cardList[1].getFace().getValue() == cardList[2].getFace().getValue() && cardList[2].getFace().getValue() == cardList[3].getFace().getValue()) {
            return 140 + cardList[0].getFace().getValue();
        }
        //if next four are same value... ect...
        if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue() && cardList[2].getFace().getValue() == cardList[3].getFace().getValue() && cardList[3].getFace().getValue() == cardList[4].getFace().getValue()) {
            return 140 + cardList[1].getFace().getValue();
        }
        if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue() && cardList[3].getFace().getValue() == cardList[4].getFace().getValue() && cardList[4].getFace().getValue() == cardList[5].getFace().getValue()) {
            return 140 + cardList[2].getFace().getValue();
        }
        if (cardList[3].getFace().getValue() == cardList[4].getFace().getValue() && cardList[4].getFace().getValue() == cardList[5].getFace().getValue() && cardList[5].getFace().getValue() == cardList[6].getFace().getValue()) {
            return 140 + cardList[3].getFace().getValue();
        }
        return 0;
    }

    /**
     * checks to see if there is a full house in a hand
     *
     * @param cardList
     * @return a number to score the hand
     */
    private static int checkFullHouse(Card[] cardList) {

        int third = checkThreeOfAKind(cardList); //return the value from a three of a kind check
        if (third == 0) { //if there is no three of a kind, there is no full house
            return 0; //so end
        } else { //if there is three of a kind, check for two of a kind of different value from the three of a kind
            third = third - 60; //change this vale to be the value of the card used in the three of a kind
            if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue() && cardList[0].getFace().getValue() != third) {
                return 120 + third; //if we have a pair that is not the three of a kind, return the full house constant plus the value of the three of a kind card
            }
            if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue() && cardList[1].getFace().getValue() != third) {
                return 120 + third;
            }
            if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue() && cardList[2].getFace().getValue() != third) {
                return 120 + third;
            }
            if (cardList[3].getFace().getValue() == cardList[4].getFace().getValue() && cardList[3].getFace().getValue() != third) {
                return 120 + third;
            }
            if (cardList[4].getFace().getValue() == cardList[5].getFace().getValue() && cardList[4].getFace().getValue() != third) {
                return 120 + third;
            }
            if (cardList[5].getFace().getValue() == cardList[6].getFace().getValue() && cardList[5].getFace().getValue() != third) {
                return 120 + third;
            }
        }

        return 0;
    }

    /**
     * checks to see if there is a flush in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkFlush(Card[] cardList) {

        int numHeart = 0;
        int numSpade = 0;
        int numDiamond = 0;
        int numClub = 0;
        //varaibles for holding num of each suit
        //add values to the varaibles
        for (int i = 0; i < cardList.length; i++) {
            if (cardList[i].getSuitName().equals("Spades")) {
                numSpade++;
            }
            if (cardList[i].getSuitName().equals("Hearts")) {
                numHeart++;
            }
            if (cardList[i].getSuitName().equals("Clubs")) {
                numClub++;
            }
            if (cardList[i].getSuitName().equals("Diamonds")) {
                numDiamond++;
            }
        }

        if (numDiamond > 4) { //if the flush is caused by diamonds
            if (cardList[0].getSuitName().equals("Diamonds")) { //if the first card is highest in the flush
                return 100 + cardList[0].getFace().getValue();
            }
            if (cardList[1].getSuitName().equals("Diamonds")) { //if second card is highest in the flush
                return 100 + cardList[1].getFace().getValue();
            }
            if (cardList[2].getSuitName().equals("Diamonds")) { //if the third card is highest in the flush
                return 100 + cardList[2].getFace().getValue();
            }
        }
        if (numClub > 4) { //if the flush is caused by clubs
            if (cardList[0].getSuitName().equals("Clubs")) { //if the first card is highest in the flush
                return 100 + cardList[0].getFace().getValue();
            }
            if (cardList[1].getSuitName().equals("Clubs")) { //if the second card is highest in the flush
                return 100 + cardList[1].getFace().getValue();
            }
            if (cardList[2].getSuitName().equals("Clubs")) { //if the third card is highest in the flush
                return 100 + cardList[2].getFace().getValue();
            }
        }
        if (numHeart > 4) { //if the flush is caused by hearts
            if (cardList[0].getSuitName().equals("Hearts")) { //if the first card is highest in the flush
                return 100 + cardList[0].getFace().getValue();
            }
            if (cardList[1].getSuitName().equals("Hearts")) { //if the second card is highest in the flush
                return 100 + cardList[1].getFace().getValue();
            }
            if (cardList[2].getSuitName().equals("Hearts")) { //if the third card is highest in the flush
                return 100 + cardList[2].getFace().getValue();
            }
        }
        if (numSpade > 4) { //if the flush is caused by spades
            if (cardList[0].getSuitName().equals("Spades")) { //if the first card is highest in the flush
                return 100 + cardList[0].getFace().getValue();
            }
            if (cardList[1].getSuitName().equals("Spades")) { //if the second card is highest in the flush
                return 100 + cardList[1].getFace().getValue();
            }
            if (cardList[2].getSuitName().equals("Spades")) { //if the third card is highest in the flush
                return 100 + cardList[2].getFace().getValue();
            }
        }

        return 0;
    }

    /**
     * checks to see if there is a straight in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkStraight(Card[] cardList) {
        //if the highest 5 return a straight
        if ((cardList[0].getFace().getValue() == (cardList[1].getFace().getValue() + 1)) && (cardList[1].getFace().getValue() == (cardList[2].getFace().getValue() + 1)) && (cardList[2].getFace().getValue() == (cardList[3].getFace().getValue() + 1)) && (cardList[3].getFace().getValue() == (cardList[4].getFace().getValue() + 1))) {
            return 80 + cardList[0].getFace().getValue();
        }
        //if the middle 5 return a straight
        if ((cardList[1].getFace().getValue() == (cardList[2].getFace().getValue() + 1)) && (cardList[2].getFace().getValue() == (cardList[3].getFace().getValue() + 1)) && (cardList[3].getFace().getValue() == (cardList[4].getFace().getValue() + 1)) && (cardList[4].getFace().getValue() == (cardList[5].getFace().getValue() + 1))) {
            return 80 + cardList[1].getFace().getValue();
        }
        //if the lowest 5 return a straight
        if ((cardList[2].getFace().getValue() == (cardList[3].getFace().getValue() + 1)) && (cardList[3].getFace().getValue() == (cardList[4].getFace().getValue() + 1)) && (cardList[4].getFace().getValue() == (cardList[5].getFace().getValue() + 1)) && (cardList[5].getFace().getValue() == (cardList[6].getFace().getValue() + 1))) {
            return 80 + cardList[2].getFace().getValue();
        }
        return 0;
    }

    /**
     * checks to see if there is three of a kind in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkThreeOfAKind(Card[] cardList) {
        //if the first three are the same value...
        if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue() && cardList[1].getFace().getValue() == cardList[2].getFace().getValue()) {
            return 60 + cardList[0].getFace().getValue();
        }
        //ect...
        if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue() && cardList[2].getFace().getValue() == cardList[3].getFace().getValue()) {
            return 60 + cardList[1].getFace().getValue();
        }
        if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue() && cardList[3].getFace().getValue() == cardList[4].getFace().getValue()) {
            return 60 + cardList[2].getFace().getValue();
        }
        if (cardList[3].getFace().getValue() == cardList[4].getFace().getValue() && cardList[4].getFace().getValue() == cardList[5].getFace().getValue()) {
            return 60 + cardList[3].getFace().getValue();
        }
        if (cardList[4].getFace().getValue() == cardList[5].getFace().getValue() && cardList[5].getFace().getValue() == cardList[6].getFace().getValue()) {
            return 60 + cardList[4].getFace().getValue();
        }
        return 0;

    }

    /**
     * a method to see if there is two pair in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkTwoPair(Card[] cardList) {
        int second = checkOnePair(cardList); //check to see if there is a single pair
        if (second == 0) { //if no pair exists
            return 0; //return 0
        } else { //if there is a pair
            second = second - 20; //second is now the value of the pair
            if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue() && cardList[0].getFace().getValue() != second) {
                return 40 + second; //if we have a pair that is not the first pair, return the two pair constant plus the value of the highest pair card
            }
            if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue() && cardList[1].getFace().getValue() != second) {
                return 40 + second;
            }
            if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue() && cardList[2].getFace().getValue() != second) {
                return 40 + second;
            }
            if (cardList[3].getFace().getValue() == cardList[4].getFace().getValue() && cardList[3].getFace().getValue() != second) {
                return 40 + second;
            }
            if (cardList[4].getFace().getValue() == cardList[5].getFace().getValue() && cardList[4].getFace().getValue() != second) {
                return 40 + second;
            }
            if (cardList[5].getFace().getValue() == cardList[6].getFace().getValue() && cardList[5].getFace().getValue() != second) {
                return 40 + second;
            }
        }
        return 0;
    }

    /**
     * a method to check if there is a single pair in the hand
     *
     * @param cardList
     * @return an int to score the hand
     */
    private static int checkOnePair(Card[] cardList) {
        //checks to see if the first two cards are a pair...
        if (cardList[0].getFace().getValue() == cardList[1].getFace().getValue()) {
            return 20 + cardList[0].getFace().getValue();
        }
        //ect...
        if (cardList[1].getFace().getValue() == cardList[2].getFace().getValue()) {
            return 20 + cardList[1].getFace().getValue();
        }
        if (cardList[2].getFace().getValue() == cardList[3].getFace().getValue()) {
            return 20 + cardList[2].getFace().getValue();
        }
        if (cardList[3].getFace().getValue() == cardList[4].getFace().getValue()) {
            return 20 + cardList[3].getFace().getValue();
        }
        if (cardList[4].getFace().getValue() == cardList[5].getFace().getValue()) {
            return 20 + cardList[4].getFace().getValue();
        }
        if (cardList[5].getFace().getValue() == cardList[6].getFace().getValue()) {
            return 20 + cardList[5].getFace().getValue();
        }
        return 0;

    }

}
