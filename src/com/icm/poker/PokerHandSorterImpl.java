/**
 * 
 */
package com.icm.poker;

import java.util.List;

import com.icm.constant.RankConstant;

/**
 * @author Glen
 *
 */
public class PokerHandSorterImpl implements PokerHandSorter {	
	private final int ROYAL_FLUSH_TOTAL_CARD_VALUE = 60;
	private final int FOUR_OF_A_KIND_VALUE = 4;
	private final int THREE_OF_A_KIND_VALUE = 3;

	/**
	 * @param Player
	 * @return true if Royal Flush is found in the rank
	 * else return false
	 */
	@Override
	public boolean findRoyalFlush(Player player) {
		List<String> cards = player.getCardList();
		boolean sameSuit = true;
		int totalCardValue = 0;
		
		for(int i=0; i< cards.size()-1; i++){
			String cardValue = player.getCardValue(cards.get(i));
			String cardSuit = player.getCardSuit(cards.get(i));
			
			if(!cardSuit.equals(player.getCardSuit(cards.get(i+1)))){
				sameSuit = false;
				break;
			}
			totalCardValue += Integer.parseInt(cardValue);
		}
		
		
		if(sameSuit){
			String cardValue = player.getCardValue(cards.get(cards.size()-1)); 
			totalCardValue += Integer.parseInt(cardValue);

			if(totalCardValue == ROYAL_FLUSH_TOTAL_CARD_VALUE){
				player.setWonWhichRank(RankConstant.ROYAL_FLUSH.getValue());
				return true;
			}			
		}
		return false;
	}

	/**
	 * @param Player
	 * @return true if Straight Flush is found in the rank
	 * else return false
	 */
	@Override
	public boolean findStrighFlush(Player player) {
		List<String> cards = player.getCardList();
		boolean sameSuit = true;
		boolean consecutiveValues = true;
		
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i)));
			String cardSuit = player.getCardSuit(cards.get(i));
			
			if(!cardSuit.equals(player.getCardSuit(cards.get(i+1)))){
				sameSuit = false;
				break;
			}
			
			if(!(Integer.parseInt(player.getCardValue(cards.get(i+1)))- cardValue == 1)){
				consecutiveValues = false;
				break;
			}			
		}
		
		if(sameSuit && consecutiveValues){
			player.setWonWhichRank(RankConstant.STRAIGHT_FLUSH.getValue());
			player.setHighestRankCard(Integer.parseInt(player.getCardValue(cards.get(4))));
			return true;
		}
		
		return false;

	}

	/**
	 * @param Player
	 * @return true if Four cards of same value is found in the rank
	 * else return false
	 */
	@Override
	public boolean findFourOfAKind(Player player) {
		List<String> cards = player.getCardList();
		int numberOfRecurringCardValues = 1;
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i+1)));
			
			if(Integer.parseInt(player.getCardValue(cards.get(i)))== cardValue){
				numberOfRecurringCardValues++;
				player.setHighestRankCard(Integer.parseInt(player.getCardValue(cards.get(i))));
			}
		}
		
		if(numberOfRecurringCardValues == FOUR_OF_A_KIND_VALUE){
			player.setWonWhichRank(RankConstant.FOUR_OF_A_KIND.getValue());
			return true;
		}
		return false;
	}

	/**
	 * @param Player
	 * @return true if Three of a kind and a Pair is found in the rank
	 * else return false
	 */
	@Override
	public boolean findFullHouse(Player player) {
		List<String> cards = player.getCardList();
		boolean threeOfAKind = false;
		boolean pair = false;
		
		int cardValue1 = Integer.parseInt(player.getCardValue(cards.get(0)));
		int cardValue2 = Integer.parseInt(player.getCardValue(cards.get(1)));
		int cardValue3 = Integer.parseInt(player.getCardValue(cards.get(2)));
		int cardValue4 = Integer.parseInt(player.getCardValue(cards.get(3)));
		int cardValue5 = Integer.parseInt(player.getCardValue(cards.get(4)));
		
		if(cardValue1 == cardValue2 && cardValue2 == cardValue3){
			threeOfAKind = true;
			player.setHighestRankCard(cardValue1);
		}
		else if(cardValue3 == cardValue4 && cardValue4 == cardValue5){
			threeOfAKind = true;
			player.setHighestRankCard(cardValue3);
		}
		
		if(cardValue1 == cardValue2 && cardValue2 != cardValue3){
			pair = true;
		}
		else if(cardValue4 == cardValue5 && cardValue4 != cardValue3){
			pair = true;
		}
		
		if(pair && threeOfAKind){
			player.setWonWhichRank(RankConstant.FULL_HOUSE.getValue());
			return true;
		}
		
		return false;
		

	}

	/**
	 * @param Player
	 * @return true if All 5 cards having same suit is found in the rank
	 * else return false
	 */
	@Override
	public boolean findFlush(Player player) {
		List<String> cards = player.getCardList();
		boolean sameSuit = true;
		
		for(int i=0; i< cards.size()-1; i++){
			String cardSuit = player.getCardSuit(cards.get(i));
			
			if(!cardSuit.equals(player.getCardSuit(cards.get(i+1)))){
				sameSuit = false;
				break;
			}
		}
		
		if(sameSuit){
			player.setWonWhichRank(RankConstant.FLUSH.getValue());
			player.setHighestRankCard(Integer.parseInt(player.getCardValue(cards.get(4))));
			return true;
		}
		
		return false;

	}

	/**
	 * @param Player
	 * @return true if All 5 cards in consecutive value order is found in the rank
	 * else return false
	 */
	@Override
	public boolean findStraight(Player player) {
		List<String> cards = player.getCardList();
		boolean consecutiveValues = true;
		
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i)));
						
			if(!(Integer.parseInt(player.getCardValue(cards.get(i+1)))- cardValue == 1)){
				consecutiveValues = false;
				break;
			}			
		}
		
		if(consecutiveValues){
			player.setWonWhichRank(RankConstant.STRAIGHT.getValue());
			player.setHighestRankCard(Integer.parseInt(player.getCardValue(cards.get(4))));
			return true;
		}
		
		return false;

	}

	/**
	 * @param Player
	 * @return true if 3 cards of the same value is found in the rank
	 * else return false
	 */
	@Override
	public boolean findThreeOfAKind(Player player) {
		List<String> cards = player.getCardList();
		int numberOfRecurringCardValues = 1;
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i)));
			
			if(Integer.parseInt(player.getCardValue(cards.get(i+1)))== cardValue){
				numberOfRecurringCardValues++;
				player.setHighestRankCard(cardValue);
			}
		}
		
		if(numberOfRecurringCardValues == THREE_OF_A_KIND_VALUE){
			player.setWonWhichRank(RankConstant.THREE_OF_A_KIND.getValue());			
			return true;
		}
		return false;

	}

	/**
	 * @param Player
	 * @return true if 2 different pairs are found in the rank
	 * else return false
	 */
	@Override
	public boolean findTwoPairs(Player player) {
		List<String> cards = player.getCardList();
		boolean pair1 = false;
		boolean pair2 = false;
		int firstPairCardValue = 0;
		
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i)));
			
			if(!pair1 && Integer.parseInt(player.getCardValue(cards.get(i+1)))== cardValue){
				pair1 = true;
				firstPairCardValue = cardValue;
				continue;
			}
			
			if(pair1 && firstPairCardValue != cardValue 
					&& Integer.parseInt(player.getCardValue(cards.get(i+1)))== cardValue){
				pair2 = true;
				if(firstPairCardValue > cardValue){
					player.setHighestRankCard(firstPairCardValue);
				}
				else{
					player.setHighestRankCard(cardValue);
				}
				break;
			}
		}
		
		if(pair1 && pair2){
			player.setWonWhichRank(RankConstant.TWO_PAIRS.getValue());			
			return true;
		}
		return false;

	}

	/**
	 * @param Player
	 * @return true if 2 cards of same value are found in the rank
	 * else return false
	 */
	@Override
	public boolean findPair(Player player) {
		List<String> cards = player.getCardList();
		boolean pair1 = false;
		
		for(int i=0; i< cards.size()-1; i++){
			int cardValue = Integer.parseInt(player.getCardValue(cards.get(i)));
			
			if(Integer.parseInt(player.getCardValue(cards.get(i+1)))== cardValue){
				pair1 = true;
				player.setHighestRankCard(cardValue);
				break;
			}
		}
		
		if(pair1){
			player.setWonWhichRank(RankConstant.PAIR.getValue());			
			return true;
		}
		
		return false;

	}

	
	/**
	 * @param Player
	 * This method gets the high card in the rank
	 * 
	 */
	@Override
	public void findHighCard(Player player) {
		List<String> cards = player.getCardList();
		player.setHighestRankCard(Integer.parseInt(player.getCardValue(cards.get(4))));
		player.setWonWhichRank(RankConstant.HIGH_CARD.getValue());

	}
}
