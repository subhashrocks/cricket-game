package com.demo.Cricketgamefinal.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

// @Document(collection = "playerScores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
// @Builder
public class Player {

    @Id
    String id;
    String name;
    String teamName;

}
