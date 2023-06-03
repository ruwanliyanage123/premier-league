package core.premier.league.service;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.facade.ScoreCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreCardServiceImpl implements ScoreCardService{
    @Autowired
    private ScoreCardFacade scoreCardFacade;
    @Override
    public List<String> getFirstBatScore() {
        return null;
    }

    @Override
    public List<String> getSecondBatScore() {
        return null;
    }

    @Override
    public List<Player> getSecondBowledScore() throws FileDataCollectionException {
        return scoreCardFacade.getFirstBattedTeamResults("/Users/ruwan/Desktop/LPL/match_result.csv");
    }

    @Override
    public List<String> getFirstBowledScore() {
        return null;
    }
}
