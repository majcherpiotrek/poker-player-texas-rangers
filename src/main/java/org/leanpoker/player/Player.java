package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import org.leanpoker.models.CardModel;
import org.leanpoker.models.GameStateModel;
import org.leanpoker.models.PlayerModel;
import org.leanpoker.parser.CardJsonParser;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        Gson gson = new Gson();
        GameStateModel gameStateModel = gson.fromJson(request, GameStateModel.class);
        List<CardModel> cardModelCommunity = gameStateModel.getCommunity_cards();

        int idPlayer = gameStateModel.getIn_action();
        PlayerModel playermodel = gameStateModel.getPlayers().get(idPlayer);
        List<CardModel> playerCard = playermodel.getHole_cards();

        List<CardModel> cardModelAll = new ArrayList<>();
        cardModelAll.addAll(cardModelCommunity);
        cardModelAll.addAll(playerCard);

        List<List<CardModel>> listsWithTheSameCards = sortCardsByRank(cardModelAll);


        if(gameStateModel.getCommunity_cards().size() <= 3){
            return 2 * gameStateModel.getCurrent_buy_in() - playermodel.getBet()+ 2* gameStateModel.getMinimum_raise();
        }

        if(!listsWithTheSameCards.isEmpty()){
            return numberOfKind(listsWithTheSameCards) * (gameStateModel.getCurrent_buy_in() - playermodel.getBet()+ 2* gameStateModel.getMinimum_raise());
        }

    	// Check if we have any figure
    	
    	// Check how much money we have
    	
    	// Check or bet minimum raise
    	
    	
        return 0;
    }
    
    
    public static void showdown(JsonElement game) {
    }
    
    public static List<List<CardModel>> sortCardsByRank(List<CardModel> cardList) {
    	List<List<CardModel>> result = new ArrayList<>();
    	List<String> ranksAlreadyChecked = new ArrayList();
    	
    	for (int i = 0; i < cardList.size(); i++) {
    		CardModel currentCard = cardList.get(i);
    		if (!ranksAlreadyChecked.contains(currentCard.getRank())) {
    			
    			ranksAlreadyChecked.add(currentCard.getRank());
    			List<CardModel> foundFigure = new ArrayList<>();
    			foundFigure.add(currentCard);
    			
        		for (int k = 0; k < cardList.size(); k++) {
        			CardModel nextCard = cardList.get(k);
        			
        			if (!currentCard.equals(nextCard) && currentCard.getRank().equals(nextCard.getRank())) {
        				foundFigure.add(nextCard);
        			}
        		}
        		
        		if (foundFigure.size() > 1) {
    				result.add(foundFigure);
    			}
    		}
    	}
    	return result;

}
    public static int numberOfKind(List<List<CardModel>> listsWithTheSameCards){
        if(findFourOfAKind(listsWithTheSameCards)!=null){
            return 4;
        }else  if(findThreeOfAKind(listsWithTheSameCards)!=null){
            return 3;
        }
        if(findTwoOfAKind(listsWithTheSameCards)!=null){
            return 2;
        }
        else {
            return 1;
        }
    }
    
    public static String findFourOfAKind(List<List<CardModel>> sortedCards ) {
    	for(List<CardModel> cards : sortedCards) {
    		if(cards.size() == 4) {
    			return cards.get(0).getRank();
    		}
    	}
    	return null;
    }
    
    public static String findThreeOfAKind(List<List<CardModel>> sortedCards) {
    	for(List<CardModel> cards : sortedCards) {
    		if(cards.size() == 3) {
    			return cards.get(0).getRank();
    		}
    	}
    	return null;
    }
    
    public static String findTwoOfAKind(List<List<CardModel>> sortedCards) {
    	for(List<CardModel> cards : sortedCards) {
    		if(cards.size() == 2) {
    			return cards.get(0).getRank();
    		}
    	}
    	return null;
    }
    
    public static boolean shouldFold(List<CardModel> cardsInHand) {
    	List<String> ranksInHand = new ArrayList<>();
    	ranksInHand.add(cardsInHand.get(0).getRank());
    	ranksInHand.add(cardsInHand.get(1).getRank());
    	return ranksInHand.contains("7") && ranksInHand.contains("2");
    }
}
