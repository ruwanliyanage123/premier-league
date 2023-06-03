package core.premier.league.controller;

import core.premier.league.entity.Player;
import core.premier.league.entity.Summary;
import core.premier.league.exception.FileDataCollectionException;
import core.premier.league.exception.SummaryNotReadyException;
import core.premier.league.service.ScoreCardService;
import core.premier.league.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    @GetMapping(value = "/result")
    public ResponseEntity<Map<String, List<Player>>> getSummaryResult() {
        try {
            return new ResponseEntity<>(summaryService.findSummaryResult(), HttpStatus.OK);
        } catch (SummaryNotReadyException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/sum")
    public ResponseEntity<Summary> getMatchSummary() {
        try {
            return new ResponseEntity<>(summaryService.findMatchSummary(), HttpStatus.OK);
        } catch (SummaryNotReadyException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
