package core.premier.league.service;

import core.premier.league.entity.Player;
import core.premier.league.entity.Summary;
import core.premier.league.exception.SummaryNotReadyException;
import core.premier.league.facade.ScoreCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SummaryServiceImpl implements SummaryService{
    @Autowired
    private ScoreCardFacade scoreCardFacade;
    @Override
    public Summary findMatchSummary() {
        return null;
    }

    @Override
    public Map<String, List<Player>> findSummaryResult() throws SummaryNotReadyException {
        return scoreCardFacade.getMatchSummaryResult();
    }
}
