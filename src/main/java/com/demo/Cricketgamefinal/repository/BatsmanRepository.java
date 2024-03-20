package com.demo.Cricketgamefinal.repository;

import com.demo.Cricketgamefinal.model.Batsman;
import com.demo.Cricketgamefinal.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BatsmanRepository extends MongoRepository<Batsman,String> {

    List<Batsman> findByMatchId(String matchId);

    List<Batsman> findByTeamNameAndMatchId(String teamName, String matchId);

    String deleteAllByMatchId(String matchId);
}
