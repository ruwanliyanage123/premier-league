package core.premier.league.facade;

import core.premier.league.entity.Player;
import core.premier.league.entity.Team;
import core.premier.league.exception.FileDataCollectionException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScoreCardFacadeImpl implements ScoreCardFacade{
    private Team team1;
    private Team team2;

    @Override
    public Map<String, List<Player>> getFirstInningResults(String path) throws FileDataCollectionException {
        return null;
    }

    @Override
    public Map<String, List<Player>> getSecondInningResults(String path) throws FileDataCollectionException {
        return null;
    }
}
