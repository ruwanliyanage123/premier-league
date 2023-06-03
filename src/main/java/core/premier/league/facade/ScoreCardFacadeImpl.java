package core.premier.league.facade;

import core.premier.league.entity.Player;
import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;

import java.util.List;

public class ScoreCardFacadeImpl implements ScoreCardFacade{
    @Override
    public List<Player> getFirstBattedTeamResults(String path) throws FileDataCollectionException {
        List<RowScoreData> rowScoreDataList = CSVFileDataCollectorFacadeImpl.getInstance().collectData(path);
        return null;
    }

    @Override
    public List<Player> getLastBattedTeamResults(String path) throws FileDataCollectionException{
        return null;
    }

    @Override
    public List<Player> getFirstBowledTeamResults(String path) throws FileDataCollectionException {
        return null;
    }

    @Override
    public List<Player> getLastBowledTeamResults(String path) throws FileDataCollectionException {
        return null;
    }
}
