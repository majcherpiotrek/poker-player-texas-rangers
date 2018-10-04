package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import org.leanpoker.models.CardModel;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
    	// Check if we have any figure
    	
    	// Check how much money we have
    	
    	// Check or bet minimum raise
    	
    	
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
    
    public static boolean howManyPairs(List<CardModel> cardList) {
    	boolean hasPair = false;
    	for (int i = 0; i < cardList.size(); i++) {
    		
    		CardModel currentCard = cardList.get(i);
    		for (int j = i + 1; j < cardList.size(); j++) {
    			if (currentCard.equals(cardList.get(j))) {
    				hasPair = true;
    			}
    		}
    	}
    	
    	return hasPair;
    }
}
