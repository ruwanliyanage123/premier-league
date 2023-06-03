package core.premier.league.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
   private String name;
   private Team team;
   private boolean isBowled;
   private int getNumberOfWickets;
   private double numberOfBowledOvers;
   private boolean isBatted;
   private int battedRuns;
   private int numberOfBalls;
   private double strikeRate;
   private int numberOfSixes;
   private int getNumberOfFours;
   private String outBy;
}
