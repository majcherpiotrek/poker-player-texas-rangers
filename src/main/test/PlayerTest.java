import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.leanpoker.models.CardModel;
import org.leanpoker.player.Player;

public class PlayerTest {

	@Test
	public void shouldReturnOnePair() {
		
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
//		assertTrue(foundFourOfAKind);
	}
	
}
