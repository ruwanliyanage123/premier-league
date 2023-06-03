package core.premier.league.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RowScoreData {
    private int inningNumber;
    private double overAndBall;
    private String battingTeamName;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private int runsOffBat;
    private int extras;
    private int wides;
    private int noBolls;
    private int byes;
    private int legByes;
    private String kindOfWickets;
    private String dismissedPlayed;
}
