package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GameBuilderBatsman {
    private int p1ind;
    private int p2ind;
    private int currBatsMan;
    private int runsScoredByp1;
    private int ballsFacedByp1;
    private int runsScoredByp2;
    private int ballsFacedByp2;
    private int sixesByp1;
    private int sixesByp2;
    private int foursByp1;
    private int foursByp2;

}
