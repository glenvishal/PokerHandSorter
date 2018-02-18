/**
 * 
 */
package com.icm.poker;

/**
 * @author Glen
 *
 */
public interface PokerHandSorter {
	public boolean findRoyalFlush(Player player);
	public boolean findStrighFlush(Player player);
	public boolean findFourOfAKind(Player player);
	public boolean findFullHouse(Player player);
	public boolean findFlush(Player player);
	public boolean findStraight(Player player);
	public boolean findThreeOfAKind(Player player);
	public boolean findTwoPairs(Player player);
	public boolean findPair(Player player);
	public void findHighCard(Player player);

}
