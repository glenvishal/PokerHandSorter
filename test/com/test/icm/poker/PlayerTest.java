package com.test.icm.poker;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.icm.poker.Player;

public class PlayerTest {
	Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<String> cards = new ArrayList<String>();		
		cards.add("9C");
		cards.add("QD");
		cards.add("KD");
		cards.add("JC");
		cards.add("AH");
		cards.add("3C");
		cards.add("KC");
		
		List<String> expected = Arrays.asList("3C", "9C", "JC","QD", "KD", "KC", "AH");
		
		player.setCards(cards);
		player.sort();
		assertThat(player.getCards(), is(expected));
	}

}
