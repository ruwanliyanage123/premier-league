package core.premier.league.controller;

import core.premier.league.entity.Player;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.service.ScoreCardService;
import core.premier.league.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/scorecard")
public class ScoreCardController {
    @Autowired
    private ScoreCardService scoreCardService;
    @GetMapping(value = "/first")
    public ResponseEntity<List<Player>> getFirstBatScore(){
        try {
            return new ResponseEntity<>(scoreCardService.getFirstInning().get(Constants.BATTING), HttpStatus.OK);
        } catch (FileDataCollectionException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/second")
    public ResponseEntity<List<Player>> getSecondBatScore(){
        try {
            return new ResponseEntity<>(scoreCardService.getSecondInning().get(Constants.BATTING), HttpStatus.OK);
        } catch (FileDataCollectionException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
