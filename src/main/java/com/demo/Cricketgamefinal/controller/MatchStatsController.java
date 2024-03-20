package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.MatchStats;
import com.demo.Cricketgamefinal.service.MatchStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Component
@RequestMapping("matchStats")
public class MatchStatsController {
    @Autowired
    private MatchStatsService matchStatsService;

    @PostMapping
    public MatchStats createMatchStat(MatchStats matchStats){
        return matchStatsService.addMatchStats(matchStats);
    }
    @PutMapping
    public MatchStats updateMatchStats(MatchStats matchStats){
        return matchStatsService.updateMatchStat(matchStats);
    }
    @GetMapping
    public List<MatchStats> getAllMatchStats(){
        return matchStatsService.getAllMatchStats();
    }
    @GetMapping("/{matchId}")
    public MatchStats getMatchStat(@PathVariable String matchId){
        return matchStatsService.getMatchStatOfAMatch(matchId);
    }
}
