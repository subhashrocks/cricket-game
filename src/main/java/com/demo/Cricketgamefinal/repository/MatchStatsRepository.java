package com.demo.Cricketgamefinal.repository;

import com.demo.Cricketgamefinal.model.MatchStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MatchStatsRepository extends MongoRepository<MatchStats,String> {

    MatchStats findAllByMatchId(String matchId);
}
