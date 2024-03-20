package com.demo.Cricketgamefinal.service;

import com.demo.Cricketgamefinal.model.OverInformation;
import com.demo.Cricketgamefinal.repository.OverInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@Component

public class OverInformationService {

    @Autowired
    private OverInformationRepository repository;
    public OverInformation addInformation(OverInformation overInformation){
      //  overInformation.setId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(overInformation);
    }

    public List<OverInformation> getInfoOfAllOversOfAMatch(String matchId){
        return repository.findAllByMatchId(matchId);
    }
    public List<OverInformation> getInfoOfAllOversOfATeam(String teamName,String matchId){
        return repository.findByTeamNameAndMatchId(teamName,matchId);
    }
    public OverInformation getInfoOfAnOverOfAMatch(int over,String teamName,String matchId){
        return repository.findByOverNumberAndTeamNameAndMatchId(over,teamName,matchId);
    }

    public void deleteInfo(String matchId){
        repository.deleteAllByMatchId(matchId);
    }
}
