package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.MatchStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class StartController {
    @Autowired
    private GameBuilder gameBuilder;

    @Autowired
    private MatchStatsController matchStatsController;

    public void setWinner(MatchStats matchStats,int runsScoredByTeamOne,int runsScoredByTeamTwo,String teamOneName,String teamTwoName){
        if(runsScoredByTeamOne>runsScoredByTeamTwo){
            matchStats.setWinner(teamOneName);
        }
        else if(runsScoredByTeamOne<runsScoredByTeamTwo){
            matchStats.setWinner(teamTwoName);
        }
        else{
            matchStats.setWinner("Draw");
        }
        matchStats.setRunsScoredByTeamOne(runsScoredByTeamOne);
        matchStats.setRunsScoredByTeamTwo(runsScoredByTeamTwo);

    }
    public MatchStats buildMatchStats(String teamOneName,String teamTwoName,int overs){
        MatchStats matchStats= MatchStats.builder()
                .overs(overs)
                .teamOne(teamOneName)
                .teamTwo(teamTwoName)
                .build();

        return matchStats;
    }
    @PostMapping("/giveTeamName")
    public MatchStats setTeamNames(@RequestParam String teamOneName, @RequestParam String teamTwoName , @RequestParam int overs){
        MatchStats matchStats=buildMatchStats(teamOneName,teamTwoName,overs);
        matchStatsController.createMatchStat(matchStats);
        String matchId=matchStats.getMatchId();
        int runsScoredByTeamOne= gameBuilder.batting(teamOneName,teamTwoName,overs,Integer.MAX_VALUE-1,matchId);
        gameBuilder.clearHashMap();
        int runsScoredByTeamTwo=gameBuilder.batting(teamTwoName,teamOneName,overs,runsScoredByTeamOne+1,matchId);
        setWinner(matchStats,runsScoredByTeamOne,runsScoredByTeamTwo,teamOneName,teamTwoName);
        matchStatsController.updateMatchStats(matchStats);
        return matchStats;
    }

}
