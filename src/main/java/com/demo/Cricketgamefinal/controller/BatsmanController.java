package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.Batsman;
import com.demo.Cricketgamefinal.service.BatsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Component
@RequestMapping("/batsmen")
public class BatsmanController {

    @Autowired
    private BatsmanService batsmanService;

    @PostMapping
    public Batsman createBatsman(Batsman batsman){
        return batsmanService.addBatsman(batsman);
    }

    @GetMapping("/{matchId}")
    public List<Batsman> getAllBatsmen(@PathVariable String matchId){
        return batsmanService.getAllBatsman(matchId);
    }

    @PutMapping
    public Batsman updateBatsman(Batsman batsman){
        return batsmanService.updateBatsman(batsman);
    }

    @GetMapping("/singleBatsman/{id}")
    public Batsman getBatsmanById(@PathVariable  String id){
        return batsmanService.getBatsmanByID(id);
    }

    @DeleteMapping("/{matchId}")
    public void deleteAll(@PathVariable String matchId){
        batsmanService.deleteBatsman(matchId);
    }

    @GetMapping("/{teamName}/{matchId}")
    public List<Batsman> getBatsmenByTeamOfAMatch(@PathVariable  String teamName, @PathVariable String matchId){
        return batsmanService.getBatsmenByTeamOfAMatch(teamName,matchId);
    }

}
