package com.demo.Cricketgamefinal.service;

import com.demo.Cricketgamefinal.model.MatchStats;
import com.demo.Cricketgamefinal.repository.MatchStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MatchStatsService {
    @Autowired
    private MatchStatsRepository matchStatsRepository;

    public MatchStats addMatchStats(MatchStats matchStats){
        return matchStatsRepository.save(matchStats);
    }
    public List<MatchStats> getAllMatchStats(){
        return matchStatsRepository.findAll();
    }
    public MatchStats getMatchStatOfAMatch(String matchId){
        return matchStatsRepository.findAllByMatchId(matchId);
    }
    public MatchStats updateMatchStat(MatchStats matchStats){
        MatchStats existingMatchStat= matchStatsRepository.findById(matchStats.getMatchId()).get();
        existingMatchStat.setWinner(matchStats.getWinner());
        existingMatchStat.setRunsScoredByTeamOne(matchStats.getRunsScoredByTeamOne());
        existingMatchStat.setRunsScoredByTeamTwo(matchStats.getRunsScoredByTeamTwo());
        return matchStatsRepository.save(existingMatchStat);
    }
}
