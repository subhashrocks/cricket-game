package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "MatchStats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class MatchStats {
    @Id
    private String matchId;
    private String teamOne;
    private String teamTwo;
    private int runsScoredByTeamOne;
    private int runsScoredByTeamTwo;
    private int overs;
    private String winner;
}
