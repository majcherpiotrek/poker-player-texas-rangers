package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

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

		//CardJsonParser cardJsonParser  = new CardJsonParser();
		//PlayerModel playerModel = cardJsonParser.parseRequest(request);

        Gson gson = new Gson();
        GameStateModel gameStateModel = gson.fromJson(request, GameStateModel.class);
        gameStateModel.getCommunity_cards();
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
