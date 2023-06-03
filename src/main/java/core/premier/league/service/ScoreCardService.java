package core.premier.league.service;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ScoreCardService {
    Map<String, List<Player>> getFirstInning() throws FileDataCollectionException;
    Map<String, List<Player>> getSecondInning();
}
