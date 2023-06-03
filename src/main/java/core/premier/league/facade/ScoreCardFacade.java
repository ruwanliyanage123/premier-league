package core.premier.league.facade;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;

import java.util.List;

public interface ScoreCardFacade {

    /**
     * Result list of First Batted Team Results
     *
     * @param path file path
     * @return Last Batted Team Results
     * @throws FileDataCollectionException exception
     */
    List<Player> getFirstBattedTeamResults(String path) throws FileDataCollectionException;

    /**
     * Result list of Last Batted Team Results
     *
     * @param path file path
     * @return Last Batted Team Results
     * @throws FileDataCollectionException exception
     */
    List<Player> getLastBattedTeamResults(String path) throws FileDataCollectionException;

    /**
     * Result list of First Bowled Team Results
     *
     * @param path file path
     * @return Last Batted Team Results
     * @throws FileDataCollectionException exception
     */
    List<Player> getFirstBowledTeamResults(String path) throws FileDataCollectionException;

    /**
     * Result list of Last Bowled Team Results
     *
     * @param path file path
     * @return Last Batted Team Results
     * @throws FileDataCollectionException exception
     */
    List<Player> getLastBowledTeamResults(String path) throws FileDataCollectionException;
}
