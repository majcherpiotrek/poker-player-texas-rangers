package org.leanpoker.models;

import java.util.List;

/*
      {                                       

          "id": 0,                                // Id of the player (same as the index)
          "name": "Albert",                       // Name specified in the tournament config
          "status": "active",                     // Status of the player:
                                                  //   - active: the player can make bets, and win the current pot
                                                  //   - folded: the player folded, and gave up interest in
                                                  //       the current pot. They can return in the next round.
                                                  //   - out: the player lost all chips, and is out of this sit'n'go
          "version": "Default random player",     // Version identifier returned by the player
          "stack": 1010,                          // Amount of chips still available for the player. (Not including
                                                  //     the chips the player bet in this round.)
          "bet": 320                              // The amount of chips the player put into the pot
          "hole_cards": [                         // The cards of the player. This is only visible for your own player
                                                  //     except after showdown, when cards revealed are also included.
              {
                  "rank": "6",                    // Rank of the card. Possible values are numbers 2-10 and J,Q,K,A
                  "suit": "hearts"                // Suit of the card. Possible values are: clubs,spades,hearts,diamonds
              },
              {
                  "rank": "K",
                  "suit": "spades"
              }
          ]
      }
 */
public class PlayerModel {
	private String id;
	private String name;
	private String status;
	private String version;
	private int stack;
	private int bet;
	private List<CardModel> hole_cards;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public List<CardModel> getHole_cards() {
		return hole_cards;
	}
	public void setHole_cards(List<CardModel> hole_cards) {
		this.hole_cards = hole_cards;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bet;
		result = prime * result + ((hole_cards == null) ? 0 : hole_cards.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + stack;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		PlayerModel other = (PlayerModel) obj;
		if (bet != other.bet)
			return false;
		if (hole_cards == null) {
			if (other.hole_cards != null)
				return false;
		} else if (!hole_cards.equals(other.hole_cards))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stack != other.stack)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
