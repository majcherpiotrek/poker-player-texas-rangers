package org.leanpoker.models;

import java.util.List;

/*
 * "tournament_id":"550d1d68cd7bd10003000003",     // Id of the current tournament

  "game_id":"550da1cb2d909006e90004b1",           // Id of the current sit'n'go game. You can use this to link a
                                                  // sequence of game states together for logging purposes, or to
                                                  // make sure that the same strategy is played for an entire game

  "round":0,                                      // Index of the current round within a sit'n'go

  "bet_index":0,                                  // Index of the betting opportunity within a round

  "small_blind": 10,                              // The small blind in the current round. The big blind is twice the
                                                  //     small blind

  "current_buy_in": 320,                          // The amount of the largest current bet from any one player

  "pot": 400,                                     // The size of the pot (sum of the player bets)

  "minimum_raise": 240,                           // Minimum raise amount. To raise you have to return at least:
                                                  //     current_buy_in - players[in_action][bet] + minimum_raise

  "dealer": 1,                                    // The index of the player on the dealer button in this round
                                                  //     The first player is (dealer+1)%(players.length)

  "orbits": 7,                                    // Number of orbits completed. (The number of times the dealer
                                                  //     button returned to the same player.)

  "in_action": 1,                                 // The index of your player, in the players array

  "players": []                                    // An array of the players. The order stays the same during the
                                                 //     entire tournament
  "community_cards": []                            // Finally the array of community cards.
                                                
 */
public class GameStateModel {
	
	private String tournament_id;
	private String game_id;
	private int round;
	private int bet_index;
	private int small_blind;
	private int current_buy_in;
	private int pot;
	private int minimum_raise;
	private int dealer;
	private int orbits;
	private int in_action;
	private List<PlayerModel> players;
	private List<CardModel> community_cards;
	public String getTournament_id() {
		return tournament_id;
	}
	public void setTournament_id(String tournament_id) {
		this.tournament_id = tournament_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getBet_index() {
		return bet_index;
	}
	public void setBet_index(int bet_index) {
		this.bet_index = bet_index;
	}
	public int getSmall_blind() {
		return small_blind;
	}
	public void setSmall_blind(int small_blind) {
		this.small_blind = small_blind;
	}
	public int getCurrent_buy_in() {
		return current_buy_in;
	}
	public void setCurrent_buy_in(int current_buy_in) {
		this.current_buy_in = current_buy_in;
	}
	public int getPot() {
		return pot;
	}
	public void setPot(int pot) {
		this.pot = pot;
	}
	public int getMinimum_raise() {
		return minimum_raise;
	}
	public void setMinimum_raise(int minimum_raise) {
		this.minimum_raise = minimum_raise;
	}
	public int getDealer() {
		return dealer;
	}
	public void setDealer(int dealer) {
		this.dealer = dealer;
	}
	public int getOrbits() {
		return orbits;
	}
	public void setOrbits(int orbits) {
		this.orbits = orbits;
	}
	public int getIn_action() {
		return in_action;
	}
	public void setIn_action(int in_action) {
		this.in_action = in_action;
	}
	public List<PlayerModel> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerModel> players) {
		this.players = players;
	}
	public List<CardModel> getCommunity_cards() {
		return community_cards;
	}
	public void setCommunity_cards(List<CardModel> community_cards) {
		this.community_cards = community_cards;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bet_index;
		result = prime * result + ((community_cards == null) ? 0 : community_cards.hashCode());
		result = prime * result + current_buy_in;
		result = prime * result + dealer;
		result = prime * result + ((game_id == null) ? 0 : game_id.hashCode());
		result = prime * result + in_action;
		result = prime * result + minimum_raise;
		result = prime * result + orbits;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + pot;
		result = prime * result + round;
		result = prime * result + small_blind;
		result = prime * result + ((tournament_id == null) ? 0 : tournament_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameStateModel other = (GameStateModel) obj;
		if (bet_index != other.bet_index)
			return false;
		if (community_cards == null) {
			if (other.community_cards != null)
				return false;
		} else if (!community_cards.equals(other.community_cards))
			return false;
		if (current_buy_in != other.current_buy_in)
			return false;
		if (dealer != other.dealer)
			return false;
		if (game_id == null) {
			if (other.game_id != null)
				return false;
		} else if (!game_id.equals(other.game_id))
			return false;
		if (in_action != other.in_action)
			return false;
		if (minimum_raise != other.minimum_raise)
			return false;
		if (orbits != other.orbits)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (pot != other.pot)
			return false;
		if (round != other.round)
			return false;
		if (small_blind != other.small_blind)
			return false;
		if (tournament_id == null) {
			if (other.tournament_id != null)
				return false;
		} else if (!tournament_id.equals(other.tournament_id))
			return false;
		return true;
	}
}
