package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Batsmen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Batsman extends Player {
    private int ballsFaced;
    private int runsScored;
    private int sixes;
    private int fours;
    private double strikeRate;
    private String matchId;

}
