package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.Bowler;
import com.demo.Cricketgamefinal.service.BowlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/bowler")
public class BowlerController {
    @Autowired
    private BowlerService bowlerService;
    @PostMapping
    public Bowler addBowler(Bowler bowler){
        return bowlerService.addBowler(bowler);
    }
    @GetMapping("/{matchId}")
    public List<Bowler> getAllBowlersOfAMatch(@PathVariable String matchId){
        return bowlerService.getAllBowlersOfAMatch(matchId);
    }
    @GetMapping("/singleBowler/{id}")
    public Bowler getBowlerById(@PathVariable String id){
        return  bowlerService.getBowlerById(id);
    }
    @GetMapping("/team/{teamName}/{matchId}")
    public List<Bowler> getBowlers(@PathVariable String teamName,@PathVariable String matchId){
        return bowlerService.getAllBowlersOfATeamOfAMatch(teamName,matchId);
    }
    @PutMapping
    public Bowler updateBowler(Bowler bowler){
        return bowlerService.updateBowler(bowler);
    }
    @DeleteMapping("/{matchId}")
    public String deleteBowlerOfAMatch(@PathVariable String matchId){
        return bowlerService.deleteBowlerOfAMatch(matchId);
    }

}
