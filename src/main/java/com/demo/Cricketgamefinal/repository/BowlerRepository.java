package com.demo.Cricketgamefinal.repository;

import com.demo.Cricketgamefinal.model.Bowler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BowlerRepository extends MongoRepository<Bowler,String> {
    
    List<Bowler> findAllByMatchId(String matchId);

    List<Bowler> findByTeamNameAndMatchId(String teamName, String matchId);

    String deleteAllByMatchId(String matchId);
}
