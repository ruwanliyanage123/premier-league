package core.premier.league.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ScoreCardServiceImpl implements ScoreCardService{
    @Override
    public List<String> getFirstBatScore() {
        return null;
    }

    @Override
    public List<String> getSecondBatScore() {
        return null;
    }

    @Override
    public List<String> getSecondBowledScore() {
        System.out.println("The second bolling score");
        return Arrays.asList("The second bolling score");
    }

    @Override
    public List<String> getFirstBowledScore() {
        return null;
    }
}
