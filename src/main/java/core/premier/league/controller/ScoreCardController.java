package core.premier.league.controller;

import core.premier.league.entity.RowScoreData;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.service.ScoreCardService;
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
    @GetMapping(value = "/firstbat")
    public ResponseEntity<List<RowScoreData>> getFirstBatScore(){
        System.out.println("the controller");
        try {
            return new ResponseEntity<>(scoreCardService.getSecondBowledScore(), HttpStatus.OK);
        } catch (FileDataCollectionException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
