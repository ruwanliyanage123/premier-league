package core.premier.league.service;

import core.premier.league.entity.Player;
import core.premier.league.entity.Summary;
import core.premier.league.exception.SummaryNotReadyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SummaryService {
    Summary findMatchSummary() throws SummaryNotReadyException;

    Map<String, List<Player>> findSummaryResult() throws SummaryNotReadyException;
}
