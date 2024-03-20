package com.demo.Cricketgamefinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bowlers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bowler extends Player{

   private int oversBowled;
   private int numberOfWikTaken;
   private int extrasGiven;
   private int runsGiven;
   private String matchId;

}
