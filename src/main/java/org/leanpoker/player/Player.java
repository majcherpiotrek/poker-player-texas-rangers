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
    
    public static List<List<CardModel>> findPairs(List<CardModel> cardList) {
    	// TODO implement
    	return null;
    }
}
