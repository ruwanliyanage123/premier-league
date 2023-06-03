package core.premier.league.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private String winner;
    private String team1Runs;
    private String team2Runs;
    private String team1Wickets;
    private String team2Wickets;
    private String team1Overs;
    private String team2Overs;
    private int runGap;
    private int remainBalls;
    private int remainedWickets;
}
