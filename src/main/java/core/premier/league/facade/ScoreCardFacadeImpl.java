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
    private List<Player> team1BowlingList;
    private List<Player> team2BowlingList;

    @Override
    public Map<String, List<Player>> getFirstInningResults(String path) throws FileDataCollectionException {
        init(path);
        Map<String, List<Player>> firstInning = new HashMap<>();
        firstInning.put(Constants.BATTING, team1BattingList);
        firstInning.put(Constants.BOWLING, team2BowlingList);
        return firstInning;
    }

    @Override
    public Map<String, List<Player>> getSecondInningResults(String path) throws FileDataCollectionException {
        init(path);
        Map<String, List<Player>> secondInning = new HashMap<>();
        secondInning.put(Constants.BATTING, team2BattingList);
        secondInning.put(Constants.BOWLING, team1BowlingList);
        return secondInning;
    }

    private void init(String path) throws FileDataCollectionException {
        if (team1BattingList == null && team2BattingList == null) {
            createTeam(path);
        }
    }

    private void createTeam(String path) throws FileDataCollectionException {
        List<RowScoreData> rowScoreDataList = CSVFileDataCollectorFacadeImpl.getInstance().collectData(path);
        setTeam1(rowScoreDataList);
        setTeam2(rowScoreDataList);
    }

    private void setTeam1(List<RowScoreData> rowScoreDataList) {
        List<RowScoreData> team1Data = rowScoreDataList.stream().filter(data -> data.getInningNumber() == 1).collect(Collectors.toList());
        int runOfBatByTeam1 = team1Data.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
        int extrasByTeam1 = team1Data.stream().mapToInt(RowScoreData::getExtras).sum();
        String team1Name = team1Data.get(0).getBattingTeamName();
        int wickets = (int) team1Data.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
        team1 = Team.builder()
                .teamName(team1Name)
                .extras(extrasByTeam1)
                .wickets(wickets)
                .overs(team1Data.get(team1Data.size() - 1).getOverAndBall())
                .totalRuns(extrasByTeam1 + runOfBatByTeam1)
                .build();
        setTeam1BattingResult(team1Data);
        setTeam2BowlingResult(team1Data);
    }

    private void setTeam2(List<RowScoreData> rowScoreDataList) {
        List<RowScoreData> team2Data = rowScoreDataList.stream().filter(data -> data.getInningNumber() == 2).collect(Collectors.toList());
        int runOfBatByTeam2 = team2Data.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
        int extrasByTeam2 = team2Data.stream().mapToInt(RowScoreData::getExtras).sum();
        String team2Name = team2Data.get(0).getBattingTeamName();
        int wickets = (int) team2Data.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
        team2 = Team.builder()
                .teamName(team2Name)
                .wickets(wickets)
                .totalRuns(extrasByTeam2 + runOfBatByTeam2)
                .overs(team2Data.get(team2Data.size() - 1).getOverAndBall())
                .build();
        setTeam2BattingResult(team2Data);
        setTeam1BowlingResult(team2Data);
    }

    private void setTeam1BowlingResult(List<RowScoreData> teamData) {
        Set<String> bowllingSet = new HashSet<>();
        teamData.forEach(data -> bowllingSet.add(data.getBowler()));
        team1BowlingList = new ArrayList<>();
        for (String name : bowllingSet) {
            List<RowScoreData> bowlerData = teamData.stream().filter(player -> player.getBowler().equals(name)).collect(Collectors.toList());
            int runs = bowlerData.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
            int buys = bowlerData.stream().mapToInt(RowScoreData::getByes).sum();
            int legBuy = bowlerData.stream().mapToInt(RowScoreData::getLegByes).sum();
            int wickets = (int) bowlerData.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
            int wides = (int) bowlerData.stream().filter(ball -> ball.getWides() != 0).count();
            int no = (int) bowlerData.stream().filter(ball -> ball.getNoBolls() != 0).count();
            int balls = bowlerData.size() - wides - no;
            double overs = getOvers(balls);
            double eco = (double) (runs + buys + legBuy) / overs;
            double economy = Math.round(eco * 100.0) / 100.0;
            Player bowler = Player.builder()
                    .team(team1)
                    .name(name)
                    .bowledRuns(runs + buys + legBuy)
                    .isBowled(true)
                    .overs(overs)
                    .wickets(wickets)
                    .economy(economy)
                    .build();
            team1BowlingList.add(bowler);
        }
    }

    private void setTeam2BowlingResult(List<RowScoreData> teamData) {
        Set<String> bowllingSet = new HashSet<>();
        teamData.forEach(data -> bowllingSet.add(data.getBowler()));
        team2BowlingList = new ArrayList<>();
        for (String name : bowllingSet) {
            List<RowScoreData> bowlerData = teamData.stream().filter(player -> player.getBowler().equals(name)).collect(Collectors.toList());
            int runs = bowlerData.stream().mapToInt(RowScoreData::getRunsOffBat).sum();
            int buys = bowlerData.stream().mapToInt(RowScoreData::getByes).sum();
            int legBuy = bowlerData.stream().mapToInt(RowScoreData::getLegByes).sum();
            int wickets = (int) bowlerData.stream().filter(data -> !data.getDismissedPlayed().equals("")).count();
            int wides = (int) bowlerData.stream().filter(ball -> ball.getWides() != 0).count();
            int no = (int) bowlerData.stream().filter(ball -> ball.getNoBolls() != 0).count();
            int balls = bowlerData.size() - wides - no;
            double overs = getOvers(balls);
            double eco = (double) (runs + buys + legBuy) / overs;
            double economy = Math.round(eco * 100.0) / 100.0;
            Player bowler = Player.builder()
                    .team(team2)
                    .name(name)
                    .bowledRuns(runs + buys + legBuy)
                    .isBowled(true)
                    .overs(overs)
                    .wickets(wickets)
                    .economy(economy)
                    .build();
            team2BowlingList.add(bowler);
        }
    }

    private double getOvers(int balls) {
        int fullOvers = balls / 6;
        int remain = balls % 6;
        return (double) fullOvers + (remain * 0.1);
    }

    private void setTeam1BattingResult(List<RowScoreData> teamData) {
        Set<String> battingSet = new HashSet<>();
        teamData.forEach(data -> battingSet.add(data.getBatsman()));
        team1BattingList = new ArrayList<>();
        for (String name : battingSet) {
            int runs = teamData.stream().filter(player -> player.getBatsman().equals(name)).mapToInt(RowScoreData::getRunsOffBat).sum();
            List<RowScoreData> bowler = teamData.stream().filter(player -> player.getBatsman().equals(name)).filter(data -> !data.getDismissedPlayed().equals("")).collect(Collectors.toList());
            String outBy = bowler.isEmpty() ? "not out" : bowler.get(0).getBowler();
            int bolls = (int) teamData.stream().filter(player -> player.getBatsman().equals(name)).count();
            double sr = (double) runs / bolls;
            double strikeRate = Math.round(sr * 100.0) / 100.0;
            Player batsman = Player.builder()
                    .team(team1)
                    .name(name)
                    .outBy(outBy)
                    .battedRuns(runs)
                    .balls(bolls)
                    .strikeRate(strikeRate * 100)
                    .isBatted(true)
                    .build();
            team1BattingList.add(batsman);
        }
    }

    private void setTeam2BattingResult(List<RowScoreData> teamData) {
        Set<String> battingSet = new HashSet<>();
        teamData.forEach(data -> battingSet.add(data.getBatsman()));
        team2BattingList = new ArrayList<>();
        for (String name : battingSet) {
            List<RowScoreData> bowler = teamData.stream().filter(player -> player.getBatsman().equals(name)).filter(data -> !data.getDismissedPlayed().equals("")).collect(Collectors.toList());
            String outBy = bowler.isEmpty() ? "not out" : bowler.get(0).getBowler();
            int runs = teamData.stream().filter(player -> player.getBatsman().equals(name)).mapToInt(RowScoreData::getRunsOffBat).sum();
            int bolls = (int) teamData.stream().filter(player -> player.getBatsman().equals(name)).count();
            double sr = (double) runs / bolls;
            double strikeRate = Math.round(sr * 100.0) / 100.0;
            Player batsman = Player.builder()
                    .team(team2)
                    .name(name)
                    .outBy(outBy)
                    .battedRuns(runs)
                    .balls(bolls)
                    .strikeRate(strikeRate * 100)
                    .isBatted(true)
                    .build();
            team2BattingList.add(batsman);
        }
    }
}
