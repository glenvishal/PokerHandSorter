/**
 * 
 */
package com.icm.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.icm.poker.Player;
import com.icm.poker.PokerHandSorter;
import com.icm.poker.PokerHandSorterImpl;

/**
 * @author Glen
 * This is the main class which starts the contest between both players
 *
 */
public class PokerContest {
	private int numOfHandPlayer1Won = 0;
	private int numOfHandPlayer2Won = 0;
	
	/**
	 * allPlayerList is used as a temp object to store all the player objects
	 */
	private List<Player> allPlayerList = new ArrayList<Player>();
	
	/**
	 * @param player
	 * getPokerHandSorted checks which rank the player has won
	 */
	public void getPokerHandSorted(Player player){
		PokerHandSorter pokerHandSorter = new PokerHandSorterImpl();
		boolean winner = pokerHandSorter.findRoyalFlush(player);
		
		if(!winner){
			winner = pokerHandSorter.findStrighFlush(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findFourOfAKind(player);
		}	
		
		if(!winner){
			winner = pokerHandSorter.findFullHouse(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findFlush(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findStraight(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findThreeOfAKind(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findTwoPairs(player);
		}
		
		if(!winner){
			winner = pokerHandSorter.findPair(player);
		}
		
		if(!winner){
			pokerHandSorter.findHighCard(player);
		}		
	}
	
	/**
	 * @param player1
	 * @param player2
	 * startContest starts the contest between both players
	 */
	public void startContest(Player player1, Player player2){
		
		if(player1.getWonWhichRank() == player2.getWonWhichRank()){
			if(player1.getHighestRankCard() > player2.getHighestRankCard()){
				playerOneWon();	
			}
			else if(player1.getHighestRankCard() < player2.getHighestRankCard()){
				playerTwoWon();
			}
			else{
				findNextHighestValueCard(player1, player2, player1.getHighestRankCard());
			}			
		}
		else if(player1.getWonWhichRank() > player2.getWonWhichRank()){
			playerOneWon();
		}
		else if(player1.getWonWhichRank() < player2.getWonWhichRank()){
			playerTwoWon();
		}
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @param highRankValueCard
	 * Checks for the next highest valued card
	 */
	public void findNextHighestValueCard(Player p1, Player p2, int highRankValueCard){
		List<String> p1CardList = p1.getCardList();
		List<String> p2CardList = p2.getCardList();
		
		for(int i= p1CardList.size()-1; i > 0; i--){
			int p1CardValue = Integer.parseInt(p1.getCardValue(p1CardList.get(i)));
			int p2CardValue = Integer.parseInt(p2.getCardValue(p2CardList.get(i)));
			
			if(p1CardValue == highRankValueCard && p2CardValue == highRankValueCard){
				continue;
			}
			else if(p1CardValue > p2CardValue){
				playerOneWon();
				break;
			}
			else if(p1CardValue < p2CardValue){
				playerTwoWon();
				break;
			}
		}		
	}
	
	private void playerOneWon(){
		numOfHandPlayer1Won++;
	}
	
	private void playerTwoWon(){
		numOfHandPlayer2Won++;
	}
	
	/**
	 * @param data
	 * @param name
	 * Adds data to Player object
	 */
	public void addPlayerData(String data, String name){
		Player player = new Player();
		List<String> playerCardRankList = new ArrayList<String>();
		player.setName(name);
		String[] splitData = data.split(" ");
		
		for(String eachData: splitData){
			playerCardRankList.add(eachData);			
		}
		player.setCards(playerCardRankList);
		player.sort();
		
		allPlayerList.add(player);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PokerContest contest = new PokerContest();
		//String fileName = "C://icm_workspace//poker-hands.txt";
		//read file into stream, try-with-resources
		//Paths.get
		try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {

			stream.forEach(line -> {
				String player1Data = line.substring(0, 15);
				contest.addPlayerData(player1Data, "player1");
				
				String player2Data = line.substring(15);
				contest.addPlayerData(player2Data, "player2");
			});

		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		for(int i=0; i < contest.allPlayerList.size();i+=2){
			contest.getPokerHandSorted(contest.allPlayerList.get(i));
			contest.getPokerHandSorted(contest.allPlayerList.get(i+1));
			
			contest.startContest(contest.allPlayerList.get(i), 
					contest.allPlayerList.get(i+1));
		}
		
		System.out.println("Player1 "+contest.numOfHandPlayer1Won);
		System.out.println("Player2 "+contest.numOfHandPlayer2Won);
	}

}
