Need to create singleton facade class to read the file. and meanwhile may be a rest api request to the score, but need time to 
prepare the data collection and preparations. so need to do a validator to verify file is ready. otherwise need to wait until the file preparation


#Important of builder pattern

If we have high amount of instance variables, it is difficult to add to the constructor. because we need to make sure the order of the parameters.
instead of using the constructor, we can use the builder pattern to avoid this problem.

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

used the SOLID principle to provide the single responsibility to the data collector.

Used design patterns

-singleton
-facade
-builder


shortcomings of previous version.

-there are no support to provide the difference row data collecting ways (.excl, pdf, txt) 
    -violated the SOLID  - Open to extend and close to modify
    -violated the SOLID  - Single Responsibility
    -If we need to change the way of data collecting, need to find the usages of the everyWhere and need to update the all of the places