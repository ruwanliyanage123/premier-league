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
   private int wickets;
   private int bowledRuns;
   private double overs;
   private boolean isBatted;
   private int battedRuns;
   private int balls;
   private double economy;
   private double strikeRate;
   private int sixes;
   private int fours;
   private String outBy;
}
