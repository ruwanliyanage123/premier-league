package core.premier.league.facade;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import core.premier.league.entity.RowScoreData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class CSVFileDataCollectorFacadeImpl implements FileDataCollectorFacade {
    private List<RowScoreData> rowScoreDataList = new ArrayList<>();

    private CSVFileDataCollectorFacadeImpl() {
    }

    private static class CSVFileDataCollectorFacadeImplLazyLoader {
        public static final CSVFileDataCollectorFacadeImpl INSTANCE = new CSVFileDataCollectorFacadeImpl();
    }

    public static CSVFileDataCollectorFacadeImpl getInstance() {
        return CSVFileDataCollectorFacadeImplLazyLoader.INSTANCE;
    }

    @Override
    public List<RowScoreData> collectData(String filePath) {
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
                            .runsOffBat(Integer.parseInt(record[6]))
                            .extras(Integer.parseInt(record[7]))
                            .wides(Integer.parseInt(record[8]))
                            .noBolls(Integer.parseInt(record[9]))
                            .byes(Integer.parseInt(record[10]))
                            .legByes(Integer.parseInt(record[11]))
                            .kindOfWickets(record[12])
                            .dismissedPlayed(record[13])
                            .build();
                    rowScoreDataList.add(rowScoreData);
                }
                rowNumber++;
            }
        } catch (Exception e) {

        }
        return rowScoreDataList;
    }
}
