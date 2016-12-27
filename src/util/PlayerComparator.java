package util;

import model.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

    private static final int GREATER_THAN = -1;
    private static final int SMALLER_THAN = 1;
    private static final int EQUAL = 0;

    @Override
    public int compare(Player playerOne, Player playerTwo) {
        if (playerOne.getScore().getPoints()
                == playerTwo.getScore().getPoints()) {
            if (playerOne.getScore().getTime().getHours()
                    == playerTwo.getScore().getTime().getHours()) {
                if (playerOne.getScore().getTime().getMinutes()
                        == playerTwo.getScore().getTime().getMinutes()) {
                    if (playerOne.getScore().getTime().getSeconds()
                            == playerTwo.getScore().getTime().getSeconds()) {
                        return playerOne.getPlayerName().compareTo(playerTwo.getPlayerName());
                    } else {
                        if (playerOne.getScore().getTime().getSeconds()
                                > playerTwo.getScore().getTime().getSeconds()) {
                            return SMALLER_THAN;
                        } else {
                            return GREATER_THAN;
                        }
                    }
                } else {
                    if (playerOne.getScore().getTime().getMinutes()
                            > playerTwo.getScore().getTime().getMinutes()) {
                        return SMALLER_THAN;
                    } else {
                        return GREATER_THAN;
                    }
                }
            } else {
                if (playerOne.getScore().getTime().getHours()
                        > playerTwo.getScore().getTime().getHours()) {
                    return SMALLER_THAN;
                } else {
                    return GREATER_THAN;
                }
            }
        } else {
            if (playerOne.getScore().getPoints()
                    > playerTwo.getScore().getPoints()) {
                return GREATER_THAN;
            } else {
                return SMALLER_THAN;
            }
        }
    }
}
