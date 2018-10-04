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
        
        int numCardsOnTheTable = gameStateModel.getCommunity_cards().size();
        
        if (numCardsOnTheTable == 0) {
        	// no cards on the table
        	int moneyToBet = checkIfMoneyEnough(playermodel, gameStateModel.getCurrent_buy_in() - playermodel.getBet() + 2 * gameStateModel.getMinimum_raise());
        	if (moneyToBet > 0.3 * playermodel.getStack()) {return 0;}
        	if (gameStateModel.getCurrent_buy_in() != playermodel.getBet()) {
        		return gameStateModel.getCurrent_buy_in();
        	}
        	return moneyToBet;
        	
        } else if (numCardsOnTheTable > 0 && numCardsOnTheTable <= 3) {
        	// three cards on the table
        	if (chanceForFlush(cardModelAll) >= 3) {
        		return checkIfMoneyEnough(playermodel,gameStateModel.getCurrent_buy_in() - playermodel.getBet() + 4 * gameStateModel.getMinimum_raise());
        	}
        	
        	if (fullHouse(listsWithTheSameCards)) {
        		// all in
                return playermodel.getStack();
            }
        	
        	if (numberOfKind(listsWithTheSameCards) == 4) {
        		// all in
        		return playermodel.getStack();
        	}
        	
        	if (numberOfKind(listsWithTheSameCards) == 1 ) { return 0; }
        	return checkIfMoneyEnough(playermodel, gameStateModel.getCurrent_buy_in() - playermodel.getBet() + 4 * gameStateModel.getMinimum_raise()); 
        } else if (numCardsOnTheTable >= 4) {
        	// four cards on the table
        	if (chanceForFlush(cardModelAll) >= 3) {
        		if (chanceForFlush(cardModelAll) == 4) {
        			// all in
        			return playermodel.getStack();
        		}
        		return  checkIfMoneyEnough(playermodel, gameStateModel.getCurrent_buy_in() - playermodel.getBet() + 5 * gameStateModel.getMinimum_raise());
        	}
        	
        	if (fullHouse(listsWithTheSameCards)) {
        		// all in
                return playermodel.getStack();
            }
        	
        	if (numberOfKind(listsWithTheSameCards) == 1 ) { return 0; }
        	return checkIfMoneyEnough(playermodel, gameStateModel.getCurrent_buy_in() - playermodel.getBet() + 2 * gameStateModel.getMinimum_raise());
        }


        return 0;
    }

    public static void showdown(JsonElement game) {
    }


    public static boolean fullHouse(List<List<CardModel>> listsWithTheSameCards) {
        if (listsWithTheSameCards.size() == 2 && findThreeOfAKind(listsWithTheSameCards) != null && findTwoOfAKind(listsWithTheSameCards) != null) {
            return true;
        }
        return false;
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

    public static int numberOfKind(List<List<CardModel>> listsWithTheSameCards) {
        if (findFourOfAKind(listsWithTheSameCards) != null) {
            return 4;
        } else if (findThreeOfAKind(listsWithTheSameCards) != null) {
            return 3;
        }
        if (findTwoOfAKind(listsWithTheSameCards) != null) {
            return 2;
        } else {
            return 1;
        }
    }

    public static String findFourOfAKind(List<List<CardModel>> sortedCards) {
        for (List<CardModel> cards : sortedCards) {
            if (cards.size() == 4) {
                return cards.get(0).getRank();
            }
        }
        return null;
    }

    public static String findThreeOfAKind(List<List<CardModel>> sortedCards) {
        for (List<CardModel> cards : sortedCards) {
            if (cards.size() == 3) {
                return cards.get(0).getRank();
            }
        }
        return null;
    }

    public static String findTwoOfAKind(List<List<CardModel>> sortedCards) {
        for (List<CardModel> cards : sortedCards) {
            if (cards.size() == 2) {
                return cards.get(0).getRank();
            }
        }
        return null;
    }

    public static List<String> findTwoPairs(List<List<CardModel>> sortedCards) {
        int pairCounter = 0;
        List<String> foundRanks = new ArrayList<>();
        for (List<CardModel> cards : sortedCards) {
            if (cards.size() == 2) {
                pairCounter++;
                foundRanks.add(cards.get(0).getRank());
                if (pairCounter == 2) {
                    return foundRanks;
                }
            }
        }
        return null;
    }

    public static List<String> findThreePairs(List<List<CardModel>> sortedCards) {
        int pairCounter = 0;
        List<String> foundRanks = new ArrayList<>();
        for (List<CardModel> cards : sortedCards) {
            if (cards.size() == 2) {
                pairCounter++;
                foundRanks.add(cards.get(0).getRank());
                if (pairCounter == 3) {
                    return foundRanks;
                }
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

    public static int chanceForFlush(List<CardModel> cards) {
        List<List<CardModel>> cardsSortedBySuits = sortCardsBySuit(cards);
        int biggestFlushchance = 0;
        for (List<CardModel> sameSuitCards : cardsSortedBySuits) {
            if (sameSuitCards.size() >= biggestFlushchance) {
            	biggestFlushchance = sameSuitCards.size();
            }
        }
        return biggestFlushchance;
    }

    public static List<List<CardModel>> sortCardsBySuit(List<CardModel> cardList) {
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

                    if (!currentCard.equals(nextCard) && currentCard.getSuit().equals(nextCard.getSuit())) {
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

    public static int checkIfMoneyEnough(PlayerModel playermodel, int money){
        if(playermodel.getStack()>money)
        {
            return money;
        }
        else{
            return playermodel.getStack();
        }
    }
}
