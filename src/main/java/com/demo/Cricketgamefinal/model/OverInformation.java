package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
// import java.util.List;

@Document(collection = "thingsHappenedInOver")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OverInformation {

    @Id
    private String id;
    private String matchId;
    private String firstBatsman;
    private String secondBatsman;
    private String contents;
    private String teamName;
    private int overNumber;
    @Field
    private ArrayList<String> contentOfEveryBall;
    private int wikFallenTillNow;
    private int runsScoredTillNow;
}
