/**
 * 
 */
package com.icm.poker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.icm.constant.DeckConstant;
import com.icm.constant.RankConstant;

/**
 * @author Glen
 *
 * This class represents a player
 */
public class Player {
	private final String matchString = "[a-zA-Z]";
	private int wonWhichRank = RankConstant.NOT_WON.getValue();
	private int highestRankCard;
	private List<String> cards;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWonWhichRank() {
		return wonWhichRank;
	}
	
	public void setWonWhichRank(int wonWhichRank) {
		this.wonWhichRank = wonWhichRank;
	}
	
	public int getHighestRankCard() {
		return highestRankCard;
	}
	
	public void setHighestRankCard(int highestRankCard) {
		this.highestRankCard = highestRankCard;
	}
	
	public List<String> getCards() {
		List<String> tempCards = new ArrayList<String>();
		for(int i=0; i< cards.size(); i++){
			String cardValue = getCardValue(cards.get(i));
			String cardSuit = getCardSuit(cards.get(i));
			if(DeckConstant.getEnumName(Integer.parseInt(cardValue)) !=null){
				String value = DeckConstant.getEnumName(Integer.parseInt(cardValue))+cardSuit;
				tempCards.add(i, value);
			}
			else{
				tempCards.add(cards.get(i));
			}
		}
		return tempCards;
	}
	
	public List<String> getCardList(){
		return cards;
	}

	public void setCards(List<String> cards) {
		
		for(int i=0; i< cards.size(); i++){
			String cardValue = getCardValue(cards.get(i));
			String cardSuit = getCardSuit(cards.get(i));
			if(cardValue.matches(matchString)){
				cards.remove(i);
				String value = String.valueOf(DeckConstant.valueOf(cardValue).getValue())+cardSuit;
				cards.add(i,value);
			}
		}
		
		this.cards = cards;
	}
	
	/**
	 * sorts the list in ascending order
	 */
	public void sort(){
		cards.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				Integer temp1 = Integer.parseInt(getCardValue(o1));
				Integer temp2 = Integer.parseInt(getCardValue(o2));
				return temp1.compareTo(temp2);
			}
		});
	}
	
	/**
	 * @param card
	 * @return the value of the card
	 */
	public String getCardValue(String card){
		if(card.length() == 3){
			return card.substring(0, 2);
		}
		else{
			return card.substring(0, 1);
		}
	}
	
	/**
	 * @param card
	 * @return the suit of the card
	 */
	public String getCardSuit(String card){
		if(card.length() == 3){
			return card.substring(2);
		}
		else{
			return card.substring(1);
		}
	}
	
	
	
	
	
}
