package core.premier.league.facade;

import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;

import java.util.List;

public interface FileDataCollectorFacade {

    /**
     * To collect the row data as a list from a given file path
     *
     * @param filePath file path to the row data file
     * @return collected row data
     */
    List<RowScoreData> collectData(String filePath) throws FileDataCollectionException;
}
