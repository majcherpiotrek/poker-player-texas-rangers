import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.leanpoker.models.CardModel;
import org.leanpoker.player.Player;


public class PlayerTest {

	@Test
	public void shouldReturnOnePair() {
		List<CardModel> cardList = new ArrayList<>();
		cardList.add(new CardModel("A", "B"));
		cardList.add(new CardModel("B", "B"));
		cardList.add(new CardModel("C", "B"));
		cardList.add(new CardModel("D", "B"));
		cardList.add(new CardModel("E", "B"));
		cardList.add(new CardModel("F", "B"));
		cardList.add(new CardModel("C", "D"));
		
		List<List<CardModel>> result = Player.sortCardsByRank(cardList);
		
		assertEquals(1, result.size());
		assertTrue(result.get(0).contains(new CardModel("C", "B")));
		assertTrue(result.get(0).contains(new CardModel("C", "D")));
	}
	
	@Test
	public void shouldReturnTwoPairs() {
		List<CardModel> cardList = new ArrayList<>();
		cardList.add(new CardModel("A", "B"));
		cardList.add(new CardModel("B", "B"));
		cardList.add(new CardModel("C", "B"));
		cardList.add(new CardModel("H", "B"));
		cardList.add(new CardModel("E", "B"));
		cardList.add(new CardModel("C", "E"));
		cardList.add(new CardModel("H", "D"));
		
		List<List<CardModel>> result = Player.sortCardsByRank(cardList);
		
		assertEquals(2, result.size());
		assertTrue(result.get(0).contains(new CardModel("C", "B")));
		assertTrue(result.get(0).contains(new CardModel("C", "E")));
		assertTrue(result.get(1).contains(new CardModel("H", "B")));
		assertTrue(result.get(1).contains(new CardModel("H", "D")));
	}
	
	@Test
	public void shouldReturnOneThreeWhenThreeofType() {
		List<CardModel> cardList = new ArrayList<>();
		cardList.add(new CardModel("A", "B"));
		cardList.add(new CardModel("B", "B"));
		cardList.add(new CardModel("C", "B"));
		cardList.add(new CardModel("D", "B"));
		cardList.add(new CardModel("E", "B"));
		cardList.add(new CardModel("C", "E"));
		cardList.add(new CardModel("C", "D"));
		
		List<List<CardModel>> result = Player.sortCardsByRank(cardList);
		
		assertEquals(1, result.size());
		assertEquals(3, result.get(0).size());
		assertTrue(result.get(0).contains(new CardModel("C", "B")));
		assertTrue(result.get(0).contains(new CardModel("C", "E")));
		assertTrue(result.get(0).contains(new CardModel("C", "D")));
	}
	
	@Test
	public void testFindFourOfAKind_find() {
		// given
		List<List<CardModel>> sortedCards = new ArrayList<>();
		List<CardModel> cardList1 = new ArrayList<>();
		cardList1.add(new CardModel("K", "Heart"));
		cardList1.add(new CardModel("K", "Caro"));
		cardList1.add(new CardModel("K", "Cross"));
		cardList1.add(new CardModel("K", "Shield"));
		sortedCards.add(cardList1);
		
		// when
		String foundFourOfAKind = Player.findFourOfAKind(sortedCards);
		
		// then
		assertEquals("K", foundFourOfAKind);
	}
	
	@Test
	public void testFindTwoPairs_find() {
		// given
		List<List<CardModel>> sortedCards = new ArrayList<>();
		List<CardModel> cardList1 = new ArrayList<>();
		List<CardModel> cardList2 = new ArrayList<>();
		cardList1.add(new CardModel("K", "Heart"));
		cardList1.add(new CardModel("K", "Caro"));
		cardList2.add(new CardModel("10", "Cross"));
		cardList2.add(new CardModel("10", "Shield"));
		sortedCards.add(cardList1);
		sortedCards.add(cardList2);
		
		// when
		List<String> pairs = Player.findTwoPairs(sortedCards);
		
		// then
		assertEquals(2, pairs.size());
		assertTrue(pairs.contains("K"));
		assertTrue(pairs.contains("10"));
	}
	
	@Test
	public void testFindTwoPairs_noTwoPairs() {
		// given
		List<List<CardModel>> sortedCards = new ArrayList<>();
		List<CardModel> cardList1 = new ArrayList<>();
		cardList1.add(new CardModel("K", "Heart"));
		cardList1.add(new CardModel("K", "Caro"));
		sortedCards.add(cardList1);
		
		// when
		List<String> pairs = Player.findTwoPairs(sortedCards);
		
		// then
		assertTrue(pairs == null);
	}
	
	@Test
	public void testFindThreePairs_find() {
		// given
		List<List<CardModel>> sortedCards = new ArrayList<>();
		List<CardModel> cardList1 = new ArrayList<>();
		List<CardModel> cardList2 = new ArrayList<>();
		List<CardModel> cardList3 = new ArrayList<>();
		cardList1.add(new CardModel("K", "Heart"));
		cardList1.add(new CardModel("K", "Caro"));
		cardList2.add(new CardModel("10", "Cross"));
		cardList2.add(new CardModel("10", "Shield"));
		cardList3.add(new CardModel("A", "Cross"));
		cardList3.add(new CardModel("A", "Shield"));
		sortedCards.add(cardList1);
		sortedCards.add(cardList2);
		sortedCards.add(cardList3);
		
		// when
		List<String> pairs = Player.findThreePairs(sortedCards);
		
		// then
		assertEquals(3, pairs.size());
		assertTrue(pairs.contains("K"));
		assertTrue(pairs.contains("10"));
		assertTrue(pairs.contains("A"));
	}
	
	@Test
	public void testFindThreePairs_noThreePairs() {
		// given
		List<List<CardModel>> sortedCards = new ArrayList<>();
		List<CardModel> cardList1 = new ArrayList<>();
		List<CardModel> cardList3 = new ArrayList<>();
		cardList1.add(new CardModel("K", "Heart"));
		cardList1.add(new CardModel("K", "Caro"));
		cardList3.add(new CardModel("A", "Cross"));
		cardList3.add(new CardModel("A", "Shield"));
		sortedCards.add(cardList1);
		sortedCards.add(cardList3);
		
		// when
		List<String> pairs = Player.findThreePairs(sortedCards);
		
		// then
		assertTrue(pairs == null);
	}
}
