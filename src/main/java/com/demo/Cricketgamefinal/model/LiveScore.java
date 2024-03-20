package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LiveScore {
    private String firstBatsMan;
    private String SecondBatsMan;
    private int totalRunsScoredTillNow;
    private int wikFallenTillNow;
    private String currentOver;
    private String teamName;

}

