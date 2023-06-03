package core.premier.league.facade;

import core.premier.league.entity.Player;

import java.util.List;

public interface ScoreCardFacade {

    /**
     * Result list of First Batted Team Results
     *
     * @return First Batted Team Results
     */
    List<Player> getFirstBattedTeamResults();

    /**
     * Result list of Last Batted Team Results
     *
     * @return Last Batted Team Results
     */
    List<Player> getLastBattedTeamResults();

    /**
     * Result list of First Bowled Team Results
     *
     * @return First Bowled Team Results
     */
    List<Player> getFirstBowledTeamResults();

    /**
     * Result list of Last Bowled Team Results
     *
     * @return Last Bowled Team Results
     */
    List<Player> getLastBowledTeamResults();
}
