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
	
}
