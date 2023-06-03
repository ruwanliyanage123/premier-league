package core.premier.league.service;

import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreCardService {
    List<String> getFirstBatScore();
    List<String> getSecondBatScore();
    List<RowScoreData> getSecondBowledScore() throws FileDataCollectionException;
    List<String> getFirstBowledScore();
}
