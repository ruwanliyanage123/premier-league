package core.premier.league.facade;

import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class CSVFileDataCollectorFacadeImpl implements FileDataCollectorFacade {
    private final List<RowScoreData> rowScoreDataList = new ArrayList<>();

    private CSVFileDataCollectorFacadeImpl() {
    }

    private static class CSVFileDataCollectorFacadeImplLazyLoader {
        public static final CSVFileDataCollectorFacadeImpl INSTANCE = new CSVFileDataCollectorFacadeImpl();
    }

    public static CSVFileDataCollectorFacadeImpl getInstance() {
        return CSVFileDataCollectorFacadeImplLazyLoader.INSTANCE;
    }

    @Override
    public List<RowScoreData> collectData(String filePath) throws FileDataCollectionException {
        return !rowScoreDataList.isEmpty() ? rowScoreDataList : getRowScoreDataList(filePath);
    }

    private List<RowScoreData> getRowScoreDataList(String filePath) throws FileDataCollectionException {
        try {
            int rowNumber = 0;
            String splitBy = ",";
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                if (rowNumber != 0) {//to ignore the title related row in the .csv file
                    String[] record = line.split(splitBy);
                    RowScoreData rowScoreData = RowScoreData.builder()
                            .inningNumber(Integer.parseInt(record[0]))
                            .overAndBall(Double.parseDouble(record[1]))
                            .battingTeamName(record[2])
                            .batsman(record[3])
                            .nonStriker(record[4])
                            .bowler(record[5])
                            .runsOffBat(validateNumberScore(record, 6))
                            .extras(validateNumberScore(record, 7))
                            .wides(validateNumberScore(record, 8))
                            .noBolls(validateNumberScore(record, 9))
                            .byes(validateNumberScore(record, 10))
                            .legByes(validateNumberScore(record, 11))
                            .kindOfWickets(validateStringScore(record, 12))
                            .dismissedPlayed(validateStringScore(record, 13))
                            .build();
                    rowScoreDataList.add(rowScoreData);
                }
                rowNumber++;
            }
        } catch (Exception exception) {
            throw new FileDataCollectionException("Exception occur when row data collecting", exception);
        }
        return rowScoreDataList;
    }

    private Integer validateNumberScore(String[] score, int index) {
        if (index >= score.length) {
            return 0;
        }
        return score[index].equals("") ? 0 : Integer.parseInt(score[index]);
    }

    private String validateStringScore(String[] score, int index) {
        return index < score.length ? score[index] : "";
    }
}
