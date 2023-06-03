package core.premier.league.facade;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.exception.SummaryNotReadyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ScoreCardFacade {

    /**
     * Get First Inning Results
     *
     * @param path file path
     * @return First Inning Results
     * @throws FileDataCollectionException exception
     */
    Map<String, List<Player>> getFirstInningResults(String path) throws FileDataCollectionException;

    /**
     * Get Second Inning Results
     *
     * @param path file path
     * @return Second Inning Results
     * @throws FileDataCollectionException exception
     */
    Map<String, List<Player>> getSecondInningResults(String path) throws FileDataCollectionException;

    /**
     * Return match summary result
     *
     * @return match summary
     */
    Map<String, List<Player>> getMatchSummaryResult() throws SummaryNotReadyException;
}
