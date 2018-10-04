package org.leanpoker.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.leanpoker.models.CardModel;
import org.leanpoker.models.GameStateModel;
import org.leanpoker.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class CardJsonParser {

    public CardJsonParser() {
    }

    public PlayerModel parseRequest(JsonElement request) {
        PlayerModel playerModel = new PlayerModel();
        JsonObject jsonObject = request.getAsJsonObject();


        GameStateModel gameStateModel = new GameStateModel();

        //community_cards
        JsonArray jsonArray = jsonObject.getAsJsonArray("community_cards");
        List<CardModel> cardList = parseCardList(jsonArray);
        gameStateModel.setCommunity_cards(cardList);


        //players
        //community_cards
        JsonArray jsonArrayPlayers = jsonObject.getAsJsonArray("players");
        List<PlayerModel> players = parsePlayers(jsonArrayPlayers);
        gameStateModel.setPlayers(players);


        return playerModel;
    }

    private List<PlayerModel> parsePlayers(JsonArray jsonArray) {
        List<PlayerModel> playerlist = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            PlayerModel playerModel = parsePlayer(jsonArray.get(i));
            playerlist.add(playerModel);
        }
        return playerlist;
    }

    private PlayerModel parsePlayer(JsonElement jsonElement) {
        Gson gson = new Gson();
        PlayerModel playerModel = gson.fromJson(jsonElement, PlayerModel.class);
        return playerModel;

    }


    private List<CardModel> parseCardList(JsonArray jsonArray) {
        List<CardModel> cardList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            CardModel card = parseCard(jsonArray.get(i));
            cardList.add(card);
        }
        return cardList;
    }

    private CardModel parseCard(JsonElement jsonElement) {
        Gson gson = new Gson();
        CardModel cardModel = gson.fromJson(jsonElement, CardModel.class);
        return cardModel;

    }


}
