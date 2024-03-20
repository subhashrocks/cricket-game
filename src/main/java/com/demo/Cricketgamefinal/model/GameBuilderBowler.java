package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class GameBuilderBowler {
    private int bowlersIndex;
    private int runsGivenInThisOver;
    private int extrasGivenInThisOver;
    private int wikTakenInThisOver;
    private String overContents;


}
