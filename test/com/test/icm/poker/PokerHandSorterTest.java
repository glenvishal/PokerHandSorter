/**
 * 
 */
package com.test.icm.poker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.icm.constant.DeckConstant;
import com.icm.poker.Player;
import com.icm.poker.PokerHandSorter;
import com.icm.poker.PokerHandSorterImpl;

/**
 * @author Vishitha
 *
 */
public class PokerHandSorterTest {
	Player player;
	PokerHandSorter findWinner;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player();
		findWinner = new PokerHandSorterImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findRoyalFlush(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindRoyalFlushWithCardsFromSameSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("QC");
		cards.add("10C");
		cards.add("JC");
		cards.add("AC");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findRoyalFlush(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindRoyalFlushWithCardsFromDifferentSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("QC");
		cards.add("10D");
		cards.add("JD");
		cards.add("AS");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findRoyalFlush(player);
		assertFalse(isWinner);
	}
	
	@Test
	public void testFindRoyalFlushWithDifferntValuesFromSameSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("QC");
		cards.add("2C");
		cards.add("7C");
		cards.add("AC");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findRoyalFlush(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findStrighFlush(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindStrighFlushFromSameSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("7C");
		cards.add("6C");
		cards.add("9C");
		cards.add("8C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findStrighFlush(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindStrighFlushFromDifferentSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("7C");
		cards.add("6S");
		cards.add("9H");
		cards.add("8D");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findStrighFlush(player);
		assertFalse(isWinner);
	}
	
	@Test
	public void testFindStrighFlushWithDifferntValuesFromSameSuit() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("9C");
		cards.add("8C");
		cards.add("KC");
		cards.add("QC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findStrighFlush(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findFourOfAKind(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindFourOfAKindWithSameValue() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("10D");
		cards.add("10H");
		cards.add("10S");
		cards.add("8C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFourOfAKind(player);
		assertTrue(isWinner);
		assertEquals(player.getHighestRankCard(),10);
	}
	
	@Test
	public void testFindFourOfAKindWithSameValueDifferntCard() {
		List<String> cards = new ArrayList<String>();
		cards.add("JC");
		cards.add("JD");
		cards.add("JH");
		cards.add("JS");
		cards.add("AC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFourOfAKind(player);
		assertTrue(isWinner);
		assertEquals(player.getHighestRankCard(),11);
	}
	
	@Test
	public void testFindFourOfAKindWithDifferentValue() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("4D");
		cards.add("3H");
		cards.add("JS");
		cards.add("AC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFourOfAKind(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findFullHouse(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindFullHouseWithFirstPairAndThenThreeOfAKind() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("10D");
		cards.add("KH");
		cards.add("KS");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFullHouse(player);
		assertTrue(isWinner);
		assertEquals(player.getHighestRankCard(), 13);
	}
	
	@Test
	public void testFindFullHouseWithFirstThreeOfAKindAndThenPair() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("10D");
		cards.add("10H");
		cards.add("KS");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFullHouse(player);
		assertTrue(isWinner);
		assertEquals(player.getHighestRankCard(), 10);
	}
	
	@Test
	public void testFindFullHouseWithThreeOfAKindAndNoPair() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("10D");
		cards.add("10H");
		cards.add("2S");
		cards.add("4C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFullHouse(player);
		assertFalse(isWinner);
	}
	
	@Test
	public void testFindFullHouseWithPairAndNoThreeOfAKind() {
		List<String> cards = new ArrayList<String>();
		cards.add("KC");
		cards.add("KD");
		cards.add("10H");
		cards.add("2S");
		cards.add("4C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFullHouse(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findFlush(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindFlush() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("9C");
		cards.add("8C");
		cards.add("7C");
		cards.add("2C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFlush(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindFlushWith4SameSuits() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("9C");
		cards.add("8H");
		cards.add("7C");
		cards.add("2C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFlush(player);
		assertFalse(isWinner);
	}
	
	@Test
	public void testFindFlushWithNoSameSuits() {
		List<String> cards = new ArrayList<String>();
		cards.add("10C");
		cards.add("9S");
		cards.add("8H");
		cards.add("7D");
		cards.add("2C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findFlush(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findStraight(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindStraight() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("5S");
		cards.add("4H");
		cards.add("3D");
		cards.add("6C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findStraight(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindStraightWithDifferentValues() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("5S");
		cards.add("10H");
		cards.add("3D");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findStraight(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findThreeOfAKind(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindThreeOfAKind() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("7C");
		cards.add("TD");
		cards.add("TS");
		cards.add("TC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findThreeOfAKind(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindThreeOfAKindWith4SameValues() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("3H");
		cards.add("3D");
		cards.add("3C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findThreeOfAKind(player);
		assertFalse(isWinner);
	}
	
	@Test
	public void testFindThreeOfAKindWithDifferentValues() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("4H");
		cards.add("8D");
		cards.add("7C");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findThreeOfAKind(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findTwoPairs(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindTwoPairs() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("2S");
		cards.add("3H");
		cards.add("KD");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findTwoPairs(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindTwoPairsWithDifferentCombination1() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("3H");
		cards.add("KD");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findTwoPairs(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindTwoPairsWithDifferentCombination2() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("2S");
		cards.add("3H");
		cards.add("3D");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findTwoPairs(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindTwoPairsWithDifferentValues() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("4H");
		cards.add("4D");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findTwoPairs(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findPair(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindPair() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("3H");
		cards.add("KD");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findPair(player);
		assertTrue(isWinner);
	}
	
	@Test
	public void testFindPairWIthDifferentValues() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("4H");
		cards.add("AD");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		boolean isWinner = findWinner.findPair(player);
		assertFalse(isWinner);
	}

	/**
	 * Test method for {@link com.icm.poker.PokerHandSorter#findHighCard(com.icm.poker.Player)}.
	 */
	@Test
	public void testFindHighCard() {
		List<String> cards = new ArrayList<String>();
		cards.add("2C");
		cards.add("3S");
		cards.add("4H");
		cards.add("AD");
		cards.add("KC");
		
		player.setCards(cards);
		player.sort();
		findWinner.findHighCard(player);
		assertEquals("A", DeckConstant.getEnumName(player.getHighestRankCard()));
	}

}
