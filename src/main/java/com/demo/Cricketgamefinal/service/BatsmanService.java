package com.demo.Cricketgamefinal.service;

import com.demo.Cricketgamefinal.model.Batsman;
import com.demo.Cricketgamefinal.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class BatsmanService {

    @Autowired
    private BatsmanRepository batsmanRepository;

    public Batsman addBatsman(Batsman batsman){
     //   player.setId(UUID.randomUUID().toString().split("-")[0]);
        return batsmanRepository.save(batsman);
    }

    public List<Batsman> getAllBatsman(String matchId){
        return batsmanRepository.findByMatchId(matchId);
    }
    public List<Batsman>getBatsmenByTeamOfAMatch(String teamName,String matchId){
        return batsmanRepository.findByTeamNameAndMatchId(teamName,matchId);
    }
    public Batsman getBatsmanByID(String id){
        return batsmanRepository.findById(id).get();
    }
    public Batsman updateBatsman(Batsman batsman){
        Batsman existingBatsman = batsmanRepository.findById(batsman.getId()).get();
        existingBatsman.setBallsFaced(batsman.getBallsFaced());
        existingBatsman.setRunsScored(batsman.getRunsScored());
        existingBatsman.setFours(batsman.getFours());
        existingBatsman.setSixes(batsman.getSixes());
        double x=0.0;
        if(batsman.getBallsFaced()!=0 && batsman.getRunsScored()!=0){
            int runs= batsman.getRunsScored()*100;
            x=(double)runs/batsman.getBallsFaced();
        }
        existingBatsman.setStrikeRate(x);
        return batsmanRepository.save(existingBatsman);
    }

    public String deleteBatsman(String matchId){
        batsmanRepository.deleteAllByMatchId(matchId);
        return "All batsmen of the given match are deleted";
    }

}
