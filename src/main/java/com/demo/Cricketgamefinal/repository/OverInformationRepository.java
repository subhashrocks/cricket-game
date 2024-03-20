package com.demo.Cricketgamefinal.repository;

import com.demo.Cricketgamefinal.model.OverInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OverInformationRepository extends MongoRepository<OverInformation,String> {
    List<OverInformation> findAllByMatchId(String matchId);
    List<OverInformation> findByTeamNameAndMatchId(String teamName, String matchId);
    OverInformation findByOverNumberAndTeamNameAndMatchId(int over, String teamName, String matchId);

    void deleteAllByMatchId(String matchId);
}
