package core.premier.league.service;

import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.facade.CSVFileDataCollectorFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ScoreCardServiceImpl implements ScoreCardService{
    @Override
    public List<String> getFirstBatScore() {
        return null;
    }

    @Override
    public List<String> getSecondBatScore() {
        return null;
    }

    @Override
    public List<RowScoreData> getSecondBowledScore() throws FileDataCollectionException {
        return CSVFileDataCollectorFacadeImpl.getInstance().collectData("/Users/ruwan/Desktop/LPL/match_result.csv");
    }

    @Override
    public List<String> getFirstBowledScore() {
        return null;
    }
}
