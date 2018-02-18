package com.icm.constant;

/**
 * @author Glen
 * Rank Constant gives value of all the poker hand combinations in
 * ascending order
 *
 */
public enum RankConstant {
	NOT_WON(0),
	HIGH_CARD(1),
	PAIR(2),
	TWO_PAIRS(3),
	THREE_OF_A_KIND(4),
	STRAIGHT(5),
	FLUSH(6),
	FULL_HOUSE(7),
	FOUR_OF_A_KIND(8),
	STRAIGHT_FLUSH(9),
	ROYAL_FLUSH(10);
	
	private final int rankValue;
	private RankConstant(int value) {
		this.rankValue = value;
	}
	
	public int getValue() {
        return rankValue;
    }

}
