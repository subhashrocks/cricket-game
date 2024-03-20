package com.demo.Cricketgamefinal.service;

import com.demo.Cricketgamefinal.model.Bowler;
import com.demo.Cricketgamefinal.repository.BowlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
@Service
public class BowlerService {
    @Autowired
    private BowlerRepository bowlerRepository;

    public Bowler addBowler(Bowler bowler){
      //  bowler.setId(UUID.randomUUID().toString().split("-")[0]);
        return bowlerRepository.save(bowler);
    }

    public List<Bowler> getAllBowlersOfAMatch(String matchId){
        return bowlerRepository.findAllByMatchId(matchId);
    }

    public List<Bowler> getAllBowlersOfATeamOfAMatch(String teamName,String matchId){
        return bowlerRepository.findByTeamNameAndMatchId(teamName,matchId);
    }

    public Bowler getBowlerById(String id){
        return bowlerRepository.findById(id).get();
    }
    public Bowler updateBowler(Bowler bowler){
        Bowler exixtingBowler= bowlerRepository.findById(bowler.getId()).get();
        exixtingBowler.setNumberOfWikTaken(bowler.getNumberOfWikTaken());
        exixtingBowler.setRunsGiven(bowler.getRunsGiven());
        exixtingBowler.setExtrasGiven(bowler.getExtrasGiven());
        exixtingBowler.setOversBowled(bowler.getOversBowled());
        return bowlerRepository.save(exixtingBowler);
    }

    public String deleteBowlerOfAMatch(String matchId){
        bowlerRepository.deleteAllByMatchId(matchId);
        return "All bowlers of a particular match are deleted";
    }
}
