package core.premier.league.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreCardService {
    List<String> getFirstBatScore();
    List<String> getSecondBatScore();
    List<String> getSecondBowledScore();
    List<String> getFirstBowledScore();
}
