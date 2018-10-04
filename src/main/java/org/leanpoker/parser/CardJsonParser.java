package org.leanpoker.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.leanpoker.models.CardModel;
import org.leanpoker.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class CardJsonParser {

    public CardJsonParser() {}

    public  PlayerModel parseRequest(JsonElement request){
        PlayerModel playerModel = new PlayerModel();
        JsonObject jsonObject = request.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("community_cards");
        List<CardModel> cardList = parseCardList(jsonArray);
        playerModel.setHole_cards(cardList);
        return playerModel;
    }

    private List<CardModel> parseCardList(JsonArray jsonArray){
        List<CardModel> cardList = new ArrayList<>();
        for(int i=0; i<jsonArray.size(); i++){
          CardModel card = parseCard(jsonArray.get(i));
          cardList.add(card);
        }
        return cardList;
    }

    private CardModel parseCard(JsonElement jsonElement){
        Gson gson = new Gson();
        CardModel cardModel = gson.fromJson(jsonElement,CardModel.class);
        return cardModel;

    }


}
