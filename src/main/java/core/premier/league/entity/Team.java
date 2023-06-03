package core.premier.league.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String teamName;
    private int totalRuns;
    private double overs;
    private int wickets;
    private int extras;
    private int wides;
    private int noBalls;
}
