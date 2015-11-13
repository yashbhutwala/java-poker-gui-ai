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
 * Description:  Generic card abstraction
 *
 * **************************************** */
package model;

/**
 * Abstraction for a single card
 *
 * @author brk009; Used by ymb002; Morgan Eckenroth, edited by Devon Wasson
 *
 */
public class Card implements Comparable {

    @Override
    public int compareTo(Object o) {
        if (this.face.getValue() == ((Card) o).face.getValue()) {
            return 0;
        } else if (this.face.getValue() > ((Card) o).face.getValue()) {
            return -1;
        } else {
            return 1;
        }

    }

    /**
     * Enumeration for the face of a card
     */
    public enum Face {

        TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE(
                "5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE(
                        "9", 9), TEN("10", 10), JACK("J", 11), QUEEN("Q", 12), KING(
                        "K", 13), ACE("1", 14);

        private final String name;
        private final int value;
        private final String imageNamePart;

        Face(String name, int value) {
            this.name = name;
            this.value = value;
            this.imageNamePart = name;
        }

        public String getName() {
            return this.name;
        }

        public int getValue() {
            return this.value;
        }

        public String getImageNamePart() {
            return this.imageNamePart;
        }
    }

    /**
     * Enumeration representing the suit of a card
     */
    public enum Suit {

        CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades"), HEARTS("Hearts");

        String name;
        String imageNamePart;

        Suit(String name) {
            this.name = name;
            this.imageNamePart = name.substring(0, 1);
        }

        public String getName() {
            return this.name;
        }

        public String getImageNamePart() {
            return this.imageNamePart;
        }
    }

    // The suit of a card
    private final Suit suit;

    // The face value of a card
    private final Face face;

    public Card(Suit s, Face f) {
        this.suit = s;
        this.face = f;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getSuitName() {
        return suit.getName();
    }

    public Face getFace() {
        return face;
    }

    public String getFaceName() {
        return face.getName();
    }

    @Override
    public String toString() {
        return getFaceName() + getSuitName();
    }

    public String getImage() {
        return suit.getImageNamePart() + face.getImageNamePart() + ".png";
    }

}
