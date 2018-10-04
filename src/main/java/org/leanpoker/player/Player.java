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
            return 3 * gameStateModel.getCurrent_buy_in() - playermodel.getBet()+ 2* gameStateModel.getMinimum_raise();
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
}
