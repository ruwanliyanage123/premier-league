package core.premier.league.controller;

import core.premier.league.entity.RowScoreData;
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
    public ResponseEntity<List<String>> getFirstBatScore(){
        System.out.println("the controller");
        return new ResponseEntity<>(scoreCardService.getSecondBowledScore(), HttpStatus.OK);
    }
}
