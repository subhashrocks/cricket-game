package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.OverInformation;
import com.demo.Cricketgamefinal.service.OverInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component

public class OverInformationController {
    @Autowired
    private OverInformationService service;

    @PostMapping("/overs")
    public OverInformation createOver(OverInformation overInformation){
       return service.addInformation(overInformation);
    }

    @GetMapping("/overs/{matchId}")
    public List<OverInformation> getAllOversInfoOfAMatch(@PathVariable String matchId){
        return service.getInfoOfAllOversOfAMatch(matchId);
    }
    @GetMapping("/overs/{teamName}/{matchId}")
    public List<OverInformation> getAllOversInfo(@PathVariable String teamName,@PathVariable String matchId){
        return service.getInfoOfAllOversOfATeam(teamName,matchId);
    }
    @GetMapping("/overs/{over}/{teamName}/{matchId}")
    public OverInformation getInfoOfAnOver(@PathVariable int over,@PathVariable String teamName,@PathVariable String matchId){
        return service.getInfoOfAnOverOfAMatch(over,teamName,matchId);
    }
    @DeleteMapping("/overs/{matchId}")
    public String deleteAll(@PathVariable String matchId){
        service.deleteInfo(matchId);
        return "all are deleted";
    }
}
