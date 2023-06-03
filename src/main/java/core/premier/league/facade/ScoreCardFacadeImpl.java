package core.premier.league.facade;

import core.premier.league.entity.Player;
import core.premier.league.entity.RowScoreData;
import core.premier.league.entity.Team;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.util.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ScoreCardFacadeImpl implements ScoreCardFacade {
    private Team team1;
    private Team team2;

    private List<Player> team1BattingList;
    private List<Player> team2BattingList;

    @Override
    public Map<String, List<Player>> getFirstInningResults(String path) throws FileDataCollectionException {
        init(path);
        Map<String, List<Player>> firstInning = new HashMap<>();
        firstInning.put(Constants.BATTING, team1BattingList);
        return firstInning;
    }

    @Override
    public Map<String, List<Player>> getSecondInningResults(String path) throws FileDataCollectionException {
        init(path);
        Map<String, List<Player>> secondInning = new HashMap<>();
        secondInning.put(Constants.BATTING, team2BattingList);
        return secondInning;
    }

    private void init(String path) throws FileDataCollectionException {
        if(team1BattingList == null && team2BattingList == null) {
            createTeam(path);
        }
    }

    private void createTeam(String path) throws FileDataCollectionException {
        List<RowScoreData> rowScoreDataList = CSVFileDataCollectorFacadeImpl.getInstance().collectData(path);
        setTeam1(rowScoreDataList);
        setTeam2(rowScoreDataList);
    }

    private void setTeam1(List<RowScoreData> rowScoreDataList){
        List<RowScoreData> team1Data = rowScoreDataList.stream().filter(data -> data.getInningNumber() == 1).collect(Collectors.toList());
        int runOfBatByTeam1 = team1Data.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
        int extrasByTeam1 = team1Data.stream().mapToInt(RowScoreData::getExtras).sum();
        String team1Name = team1Data.get(0).getBattingTeamName();
        int wickets = (int) team1Data.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
        team1 = Team.builder()
                .teamName(team1Name)
                .extras(extrasByTeam1)
                .wickets(wickets)
                .overs(team1Data.get(team1Data.size()-1).getOverAndBall())
                .totalRuns(extrasByTeam1 + runOfBatByTeam1)
                .build();

        Set<String> battingSet = new HashSet<>();
        team1Data.forEach(data-> battingSet.add(data.getBatsman()));
        team1BattingList = new ArrayList<>();
        for (String name: battingSet) {
            int runs = team1Data.stream().filter(player->player.getBatsman().equals(name)).mapToInt(RowScoreData::getRunsOffBat).sum();
            List<RowScoreData> bowler = team1Data.stream().filter(player->player.getBatsman().equals(name)).filter(data->!data.getDismissedPlayed().equals("")).collect(Collectors.toList());
            String outBy = bowler.isEmpty() ? "not out" : bowler.get(0).getBowler();
            int bolls = (int) team1Data.stream().filter(player -> player.getBatsman().equals(name)).count();
            double sr = (double) runs/bolls;
            double strikeRate = Math.round(sr * 100.0) / 100.0;
            Player team1Batsman = Player.builder()
                    .team(team1)
                    .name(name)
                    .outBy(outBy)
                    .battedRuns(runs)
                    .numberOfBalls(bolls)
                    .strikeRate(strikeRate* 100)
                    .isBatted(true)
                    .build();
            team1BattingList.add(team1Batsman);
        }
    }

    private void setTeam2(List<RowScoreData> rowScoreDataList){
        List<RowScoreData> team2Data = rowScoreDataList.stream().filter(data -> data.getInningNumber() == 2).collect(Collectors.toList());
        int runOfBatByTeam2 = team2Data.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
        int extrasByTeam2 = team2Data.stream().mapToInt(RowScoreData::getExtras).sum();
        String team2Name = team2Data.get(0).getBattingTeamName();
        int wickets = (int) team2Data.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
        team2 = Team.builder()
                .teamName(team2Name)
                .wickets(wickets)
                .totalRuns(extrasByTeam2 + runOfBatByTeam2)
                .overs(team2Data.get(team2Data.size()-1).getOverAndBall())
                .build();

        Set<String> battingSet = new HashSet<>();
        team2Data.forEach(data-> battingSet.add(data.getBatsman()));
        team2BattingList = new ArrayList<>();
        for (String name: battingSet) {
            List<RowScoreData> bowler = team2Data.stream().filter(player->player.getBatsman().equals(name)).filter(data->!data.getDismissedPlayed().equals("")).collect(Collectors.toList());
            String outBy = bowler.isEmpty() ? "not out" : bowler.get(0).getBowler();
            int runs = team2Data.stream().filter(player->player.getBatsman().equals(name)).mapToInt(RowScoreData::getRunsOffBat).sum();
            int bolls = (int) team2Data.stream().filter(player -> player.getBatsman().equals(name)).count();
            double sr = (double) runs/bolls;
            double strikeRate = Math.round(sr * 100.0) / 100.0;
            Player team1Batsman = Player.builder()
                    .team(team2)
                    .name(name)
                    .outBy(outBy)
                    .battedRuns(runs)
                    .numberOfBalls(bolls)
                    .strikeRate(strikeRate*100)
                    .isBatted(true)
                    .build();
            team2BattingList.add(team1Batsman);
        }
    }
}
