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
 * Description:  Determines the move to make for the CPU players.
 *
 * **************************************** */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Devon Wasson
 */
public class AI extends Player {

    /**
     *
     * @param name
     * @param cash
     */
    public AI(String name, int cash) {
        super(name, cash);
    }

    /**
     * determines how much the AI should bet given how good its odds are
     *
     * @param river
     * @param dealer
     * @return amount to bet
     */
    @Override
    public int shouldBet(River river, Dealer dealer) {
        int minBet = dealer.getMinBet();
        int toBet = -1; //default to bet is fold

        int shouldBet = hasMultiple(river) + hasFlush(river) + hasStraight(river);
        if (shouldBet <= 10 && shouldBet > 0) {
            toBet = minBet; //if low chances, call or check
        } else if (shouldBet <= 20 && shouldBet > 10) {
            toBet = (int) (this.getCash() * .02);
        } else if (shouldBet <= 30 && shouldBet > 20) {
            toBet = (int) (this.getCash() * .04);
        } else if (shouldBet > 30) {
            toBet = (int) (0.10 * this.getCash());
        }

        if (toBet <= dealer.getCurrentBet() / 4) {
            toBet = 0;
        }

        return toBet;
    }

    /**
     * checks to see if the AI has any doubles or triples or quads
     *
     * @param river
     * @return num to determine odds
     */
    public int hasMultiple(River river) {
        List<Integer> cardList = new ArrayList<>();
        cardList.add(this.getCardOne().getFace().getValue());
        cardList.add(this.getCardTwo().getFace().getValue());
        cardList.add(river.getRiver().get(0).getFace().getValue());
        cardList.add(river.getRiver().get(1).getFace().getValue());
        cardList.add(river.getRiver().get(2).getFace().getValue());
        if (river.getSize() > 3) {
            cardList.add(river.getRiver().get(3).getFace().getValue());
        }
        if (river.getSize() > 4) {
            cardList.add(river.getRiver().get(4).getFace().getValue());
        }

        int toReturn = 0;
        for (int i = 0; i < cardList.size(); i++) { //for every card in list
            for (int j = i + 1; j < cardList.size(); j++) { //for each card after the card
                if (Objects.equals(cardList.get(i), cardList.get(j))) { //if we have a mastch
                    toReturn += 10; //add some
                }
            }
        }
        return toReturn;
    }

    /**
     * checks if the AI is close to getting a flush or has a flush
     *
     * @param river
     * @return num to determine odds
     */
    public int hasFlush(River river) {
        List<String> cardList = new ArrayList<>();
        cardList.add(this.getCardOne().getSuit().getName());
        cardList.add(this.getCardTwo().getSuit().getName());
        cardList.add(river.getRiver().get(0).getSuit().getName());
        cardList.add(river.getRiver().get(1).getSuit().getName());
        cardList.add(river.getRiver().get(2).getSuit().getName());
        if (river.getSize() > 3) {
            cardList.add(river.getRiver().get(3).getSuit().getName());
        }
        if (river.getSize() > 4) {
            cardList.add(river.getRiver().get(4).getSuit().getName());
        }
        //make a list to hold the card suit types
        int numHeart = 0;
        int numSpade = 0;
        int numDiamond = 0;
        int numClub = 0;
        //varaibles for holding num of each suit
        //add values to the varaibles
        for (String cardList1 : cardList) {
            if (cardList1.equals("Spades")) {
                numSpade++;
            }
            if (cardList1.equals("Hearts")) {
                numHeart++;
            }
            if (cardList1.equals("Clubs")) {
                numClub++;
            }
            if (cardList1.equals("Diamonds")) {
                numDiamond++;
            }
        }
        //for the different river sizes
        if (river.getSize() == 3) {
            return checkFlush3(numHeart, numSpade, numDiamond, numClub);
        } else if (river.getSize() == 4) {
            return checkFlush4(numHeart, numSpade, numDiamond, numClub);
        } else {
            return checkFlush5(numHeart, numSpade, numDiamond, numClub);
        }
    }

    /**
     * method to check possibility of a flush if only 3 cards in river
     *
     * @param numHeart
     * @param numSpade
     * @param numDiamond
     * @param numClub
     * @return num to determine odds
     */
    public int checkFlush3(int numHeart, int numSpade, int numDiamond, int numClub) {
        if (numHeart > 4 || numSpade > 4 || numDiamond > 4 || numClub > 4) {
            return 10; //if there is a flush
        } else if (numHeart > 3 || numSpade > 3 || numDiamond > 3 || numClub > 3) {
            return 5; //if one card off from a flush
        } else if (numHeart > 2 || numSpade > 2 || numDiamond > 2 || numClub > 2) {
            return 1; //if two cards off from a flush
        } else {
            return 0;
        }
    }

    /**
     * method to check possibility of a flush if only 4 cards in river
     *
     * @param numHeart
     * @param numSpade
     * @param numDiamond
     * @param numClub
     * @return num to determine odds
     */
    public int checkFlush4(int numHeart, int numSpade, int numDiamond, int numClub) {

        if (numHeart > 4 || numSpade > 4 || numDiamond > 4 || numClub > 4) {
            return 20; //if we have a flush
        } else if (numHeart > 3 || numSpade > 3 || numDiamond > 3 || numClub > 3) {
            return 10; //if we are 1 card off a flush
        } else {

            return 0;
        }

    }

    /**
     * method to check possibility of a flush if 5 cards in river
     *
     * @param numHeart
     * @param numSpade
     * @param numDiamond
     * @param numClub
     * @return num to determine odds
     */
    public int checkFlush5(int numHeart, int numSpade, int numDiamond, int numClub) {
        if (numHeart > 4 || numSpade > 4 || numDiamond > 4 || numClub > 4) {
            return 30; //if we have a flush
        } else {
            return 0;
        }
    }

    /**
     * checks to see if the AI has a straight or is close to one not intended to
     * be the most smart algorithm. this has flaws where it thinks it has a
     * straight if cards are in some bounds of the card it is checking against,
     * or if it has pairs. however, it works well enough for our purposes
     *
     * @param river
     * @return num to determine odds
     */
    public int hasStraight(River river) {
        List<Integer> cardList = new ArrayList<>();
        cardList.add(this.getCardOne().getFace().getValue());
        cardList.add(this.getCardTwo().getFace().getValue());
        cardList.add(river.getRiver().get(0).getFace().getValue());
        cardList.add(river.getRiver().get(1).getFace().getValue());
        cardList.add(river.getRiver().get(2).getFace().getValue());
        if (river.getSize() > 3) {
            cardList.add(river.getRiver().get(3).getFace().getValue());
        }
        if (river.getSize() > 4) {
            cardList.add(river.getRiver().get(4).getFace().getValue());

        }
        //above makes an array of all of the cards on the table

        if (river.getSize() == 3) {
            return checkStraight3(cardList);
        } else if (river.getSize() == 4) {
            return checkStraight4(cardList);
        } else {
            return checkStraight5(cardList);
        }
    }

    /**
     * helper method for straight checker checks for when there are 3 cards in
     * river
     *
     * @param cardList
     * @return num to determine odds
     */
    public int checkStraight3(List<Integer> cardList) {
        int checker = 0;
        for (int j = 0; j < 5; j++) { //for each card in list
            for (int i = 0; i < 5; i++) { //for each card in list
                if (cardList.get(j) - 2 <= cardList.get(i) && cardList.get(j) + 2 >= cardList.get(i)) {
                    checker += 1; //if card in range, add one to the checker
                }
            }
            if (checker > 1) { //if we have at least three cards in range
                return checker;
            }
            checker = 0;
        }
        return 0;
    }

    /**
     * helper method for straight checker checks for when there are 4 cards in
     * river
     *
     * @param cardList
     * @return num to determine odds
     */
    public int checkStraight4(List<Integer> cardList) {
        int checker = 0;
        for (int j = 0; j < 6; j++) { //for each card in list
            for (int i = 0; i < 6; i++) { //for each card in list
                if (cardList.get(j) - 2 <= cardList.get(i) && cardList.get(j) + 2 >= cardList.get(i)) {
                    checker += 1; //if card in range, add one to the checker
                }
            }
            if (checker > 3) {
                return checker + 3;
            } else if (checker > 2) { //if we have at least 4 cards in range
                return checker;
            }
            checker = 0;
        }
        return 0;
    }

    /**
     * helper method for straight checker checks for when there are 5 cards in
     * river
     *
     * @param cardList
     * @return num to determine odds
     */
    public int checkStraight5(List<Integer> cardList) {
        int checker = 0;
        for (int j = 0; j < 7; j++) { //for each card in list
            for (int i = 0; i < 7; i++) { //for each card in list
                if (cardList.get(j) - 2 <= cardList.get(i) && cardList.get(j) + 2 >= cardList.get(i)) {
                    checker += 1; //if card in range, add one to the checker
                }
            }
            if (checker > 4) { //if we have at least 5 cards in range
                return checker + 20;

            }
            checker = 0;
        }
        return 0;
    }
}
