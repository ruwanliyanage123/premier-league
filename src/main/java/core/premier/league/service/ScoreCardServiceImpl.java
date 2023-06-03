package core.premier.league.service;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.facade.ScoreCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreCardServiceImpl implements ScoreCardService{
    @Autowired
    private ScoreCardFacade scoreCardFacade;

    @Override
    public Map<String, List<Player>> getFirstInning() throws FileDataCollectionException {
        return scoreCardFacade.getFirstInningResults("/Users/ruwan/Desktop/LPL/match_result.csv");
    }

    @Override
    public Map<String, List<Player>> getSecondInning() throws FileDataCollectionException{
        return scoreCardFacade.getSecondInningResults("/Users/ruwan/Desktop/LPL/match_result.csv");
    }
}
