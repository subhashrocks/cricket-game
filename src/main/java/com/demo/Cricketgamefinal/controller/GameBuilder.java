package com.demo.Cricketgamefinal.controller;

import com.demo.Cricketgamefinal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Component
public class GameBuilder {
    @Autowired
    private BatsmanController batsmanController;
    @Autowired
    private BowlerController bowlerController;
    @Autowired
    private OverInformationController overInformationController;
    @Autowired
    private GameBuilderBatsman gameBuilderBatsman;
    @Autowired
    private GameBuilderBowler gameBuilderBowler;
    @Autowired
    private LiveScore liveScore;

    private final int[] battingSkill={9,8,10,8,9,8,8,4,2,3,2};
    private final int[] bowlingSkills={2,3,3,4,2,7,8,10,9,8,8};
    private HashMap<Integer, Bowler> bowlerValidity=new HashMap<>();

    public void clearHashMap(){
       bowlerValidity.clear();
   }

    public void updateBatsmanAfterOver(Batsman p1,Batsman p2){
        p1.setRunsScored(gameBuilderBatsman.getRunsScoredByp1());
        p1.setBallsFaced(gameBuilderBatsman.getBallsFacedByp1()+1);
        p1.setSixes(gameBuilderBatsman.getSixesByp1());
        p1.setFours(gameBuilderBatsman.getFoursByp1());
        batsmanController.updateBatsman(p1);
        p2.setRunsScored(gameBuilderBatsman.getRunsScoredByp2());
        p2.setBallsFaced(gameBuilderBatsman.getBallsFacedByp2()+1);
        p2.setSixes(gameBuilderBatsman.getSixesByp2());
        p2.setFours(gameBuilderBatsman.getFoursByp2());
        batsmanController.updateBatsman(p2);
    }
    public int getBatsmanIndex(int strike){
        if(strike==0){
            return gameBuilderBatsman.getP1ind();
        }
        return gameBuilderBatsman.getP2ind();
    }
    public void processLiveScore(int totalRuns,int wikFallenTillNow,int overs,String teamName,int currentBall,String s1,String s2){
        liveScore.setCurrentOver(overs+"."+currentBall);
        liveScore.setTotalRunsScoredTillNow(totalRuns);
        liveScore.setWikFallenTillNow(wikFallenTillNow);
        liveScore.setTeamName(teamName);
        liveScore.setSecondBatsMan(s2);
        liveScore.setFirstBatsMan(s1);
    }
    public void processGameBuilderBowler(){
        gameBuilderBowler.setOverContents("");
        gameBuilderBowler.setExtrasGivenInThisOver(0);
        gameBuilderBowler.setWikTakenInThisOver(0);
        gameBuilderBowler.setRunsGivenInThisOver(0);
    }
    public void processBowler(Bowler b1){
        b1.setNumberOfWikTaken(b1.getNumberOfWikTaken()+gameBuilderBowler.getWikTakenInThisOver());
        b1.setRunsGiven(b1.getRunsGiven()+gameBuilderBowler.getRunsGivenInThisOver());
        b1.setOversBowled(b1.getOversBowled()+1);
        b1.setExtrasGiven(b1.getExtrasGiven()+gameBuilderBowler.getExtrasGivenInThisOver());
        bowlerController.updateBowler(b1);
        gameBuilderBowler.setBowlersIndex((gameBuilderBowler.getBowlersIndex()+1)%6);
    }
    public void processRunsScoredInThisBall(int runsScoredInThisBall,int strike){
        gameBuilderBowler.setRunsGivenInThisOver(gameBuilderBowler.getRunsGivenInThisOver()+runsScoredInThisBall);
        gameBuilderBowler.setOverContents(gameBuilderBowler.getOverContents()+" "+runsScoredInThisBall);
        if(strike==0) {
            gameBuilderBatsman.setRunsScoredByp1(gameBuilderBatsman.getRunsScoredByp1() + runsScoredInThisBall);
            gameBuilderBatsman.setBallsFacedByp1(gameBuilderBatsman.getBallsFacedByp1() + 1);
            if(runsScoredInThisBall==4){
                gameBuilderBatsman.setFoursByp1(gameBuilderBatsman.getFoursByp1()+1);
            }
            if(runsScoredInThisBall==6){
                gameBuilderBatsman.setSixesByp1(gameBuilderBatsman.getSixesByp1()+1);
            }
        }
        else{
            gameBuilderBatsman.setRunsScoredByp2(gameBuilderBatsman.getRunsScoredByp2()+runsScoredInThisBall);
            gameBuilderBatsman.setBallsFacedByp2(gameBuilderBatsman.getBallsFacedByp2()+1);
                if(runsScoredInThisBall==4) {
                    gameBuilderBatsman.setFoursByp2(gameBuilderBatsman.getFoursByp2() + 1);
                }
                if(runsScoredInThisBall==6){
                    gameBuilderBatsman.setSixesByp2(gameBuilderBatsman.getSixesByp2()+1);
                }
        }


    }
    public void processGameBuilderBatsmen(int strike){
        if(strike==0){
            gameBuilderBatsman.setBallsFacedByp1(0);
            gameBuilderBatsman.setFoursByp1(0);
            gameBuilderBatsman.setSixesByp1(0);
            gameBuilderBatsman.setRunsScoredByp1(0);
        }
        else{
            gameBuilderBatsman.setBallsFacedByp2(0);
            gameBuilderBatsman.setFoursByp2(0);
            gameBuilderBatsman.setSixesByp2(0);
            gameBuilderBatsman.setRunsScoredByp2(0);
        }
    }
    public void processWicketFallen(int strike,Batsman p1,Batsman p2){
        gameBuilderBowler.setWikTakenInThisOver(gameBuilderBowler.getWikTakenInThisOver()+1);
        gameBuilderBowler.setOverContents(gameBuilderBowler.getOverContents()+" W");
        if(strike==0){
            p1.setRunsScored(gameBuilderBatsman.getRunsScoredByp1());
            p1.setBallsFaced(gameBuilderBatsman.getBallsFacedByp1()+1);
            p1.setSixes(gameBuilderBatsman.getSixesByp1());
            p1.setFours(gameBuilderBatsman.getFoursByp1());
            batsmanController.updateBatsman(p1);

        }
        else{
            p2.setRunsScored(gameBuilderBatsman.getRunsScoredByp2());
            p2.setBallsFaced(gameBuilderBatsman.getBallsFacedByp2()+1);
            p2.setSixes(gameBuilderBatsman.getSixesByp2());
            p2.setFours(gameBuilderBatsman.getFoursByp2());
            batsmanController.updateBatsman(p2);
        }

    }
    public int getRunsScoredInThisBall(int x){
        return switch (x) {
            case -10, -9, -8, -7, -6, -5 -> 7;
            case -4, -3, -2, -1, 0 -> 0;
            case 1, 2 -> 1;
            case 3, 4 -> 2;
            case 5, 6 -> 3;
            case 7, 8 -> 4;
            case 9, 10 -> 5;
            default -> 6;
        };
    }
    public int getBatsmanSkill(int strike){
        if(strike==0){
            return battingSkill[gameBuilderBatsman.getP1ind()];
        }
        return battingSkill[gameBuilderBatsman.getP2ind()];
    }
    public String processInvalidBall(int invalidBall){
        gameBuilderBowler.setExtrasGivenInThisOver(gameBuilderBowler.getExtrasGivenInThisOver()+1);
        if(invalidBall==7){
            gameBuilderBowler.setOverContents(gameBuilderBowler.getOverContents()+" 1WD");
            return " Well it is a wide.";
        }
        else{
            gameBuilderBowler.setOverContents(gameBuilderBowler.getOverContents()+" 1NB");
            return " No Ball it is.And it is a chance for free hit.";
        }
    }
    public Batsman buildBatsman(int index,String matchId,String teamName){
        Batsman batsman= Batsman.builder()
                .ballsFaced(0)
                .runsScored(0)
              //  .fours(0)
              //  .sixes(0)
                .matchId(matchId)
                .build();
        batsman.setTeamName(teamName);
        index++;
        batsman.setName("p"+index);
        batsmanController.createBatsman(batsman);
        return batsman;
    }
    public Bowler buildBowler(int index, String matchId, String teamName){
        Bowler bowler= Bowler.builder()
                .oversBowled(0)
                .runsGiven(0)
                .extrasGiven(0)
                .numberOfWikTaken(0)
                .matchId(matchId)
                .build();
        bowler.setTeamName(teamName);
        bowler.setName("b"+index);
        bowlerController.addBowler(bowler);
        return bowler;

    }
    public void buildOverInformation(int numberOfOversDoneTillNow,String matchId,String s1,String s2,ArrayList<String>con,int totalRuns,int wikFallenTillNow,String teamOneName){
        OverInformation overInformation= OverInformation.builder()
                .overNumber(numberOfOversDoneTillNow)
                .matchId(matchId)
                .firstBatsman(s1)
                .secondBatsman(s2)
                .contents(gameBuilderBowler.getOverContents())
                .contentOfEveryBall(con)
                .runsScoredTillNow(totalRuns)
                .wikFallenTillNow(wikFallenTillNow)
                .teamName(teamOneName)
                .build();
        overInformationController.createOver(overInformation);
    }
    public void buildgameBuilderBowler(){
        gameBuilderBowler.setBowlersIndex(0);
        gameBuilderBowler.setExtrasGivenInThisOver(0);
        gameBuilderBowler.setWikTakenInThisOver(0);
        gameBuilderBowler.setRunsGivenInThisOver(0);
        gameBuilderBowler.setOverContents("");
    }
    public void buildgameBuilderBatsman(){
        gameBuilderBatsman.setP1ind(0);
        gameBuilderBatsman.setP2ind(0);
        gameBuilderBatsman.setCurrBatsMan(2);
        gameBuilderBatsman.setBallsFacedByp1(0);
        gameBuilderBatsman.setBallsFacedByp2(0);
        gameBuilderBatsman.setRunsScoredByp1(0);
        gameBuilderBatsman.setRunsScoredByp2(0);
    }

    public int batting(String teamOneName,String teamTwoName,int overs,int target,String matchId){
        int totalRuns=0;
        int runsScoredInThisBall;
        int strike=0;
        int oversDoneTillNow=0;
        int wikFallenTillNow=0;
        int ballsDoneInThisOver=0;
        boolean freeHit=false;
        ArrayList<String> overInfo= new ArrayList<>();
        buildgameBuilderBatsman();
        buildgameBuilderBowler();
        Batsman p1=buildBatsman(0,matchId,teamOneName);
        Batsman p2=buildBatsman(1,matchId,teamOneName);
        while(totalRuns<target && wikFallenTillNow<10 && oversDoneTillNow<overs){
            int checkForInvalidBall = (int)(Math.random()*100)%9;
            if(checkForInvalidBall==7 || checkForInvalidBall==8){
                overInfo.add(oversDoneTillNow+"."+ballsDoneInThisOver+processInvalidBall(checkForInvalidBall));
                totalRuns++;
                if(checkForInvalidBall==8){ freeHit=true; }
            }
            else{
                int batsManAggression = ((int)(Math.random()*100))%11;
                int batsManSkill=getBatsmanSkill(getBatsmanIndex(strike));
                int bowlingSkill=bowlingSkills[gameBuilderBowler.getBowlersIndex()+5];
                int valObtained= (((int)(Math.random()*100)))%(batsManAggression+batsManSkill)-(((int)(Math.random()*100))%bowlingSkill);
                runsScoredInThisBall=getRunsScoredInThisBall(valObtained);
                if(freeHit && runsScoredInThisBall==7){
                    runsScoredInThisBall=0;
                }
               if(runsScoredInThisBall==7){
                    processWicketFallen(strike,p1,p2);
                    overInfo.add(oversDoneTillNow+"."+ballsDoneInThisOver+" What a beauty,It is a Wicket");
                    wikFallenTillNow++;
                    if(strike==0){
                        overInfo.add(p1.getName()+":"+gameBuilderBatsman.getRunsScoredByp1()+"("+gameBuilderBatsman.getBallsFacedByp1()+") goes down!!");
                        if(wikFallenTillNow==10){

                            String s1=p1.getName()+":"+gameBuilderBatsman.getRunsScoredByp1()+"("+gameBuilderBatsman.getBallsFacedByp1()+")";
                            String s2=p2.getName()+":"+gameBuilderBatsman.getRunsScoredByp2()+"("+gameBuilderBatsman.getBallsFacedByp2()+")";
                            buildOverInformation(oversDoneTillNow,matchId,s1,s2,overInfo,totalRuns,wikFallenTillNow,teamOneName);
                        }
                        p1=buildBatsman(gameBuilderBatsman.getCurrBatsMan(),matchId,teamOneName);
                     //   updateGameBuilderBatsman(0);

                    }
                    else{
                        overInfo.add(p2.getName()+":"+gameBuilderBatsman.getRunsScoredByp2()+"("+gameBuilderBatsman.getBallsFacedByp2()+") goes down!!");
                        if(wikFallenTillNow==10) {
                            String s1=p1.getName()+":"+gameBuilderBatsman.getRunsScoredByp1()+"("+gameBuilderBatsman.getBallsFacedByp1()+")";
                            String s2=p2.getName()+":"+gameBuilderBatsman.getRunsScoredByp2()+"("+gameBuilderBatsman.getBallsFacedByp2()+")";
                            buildOverInformation(oversDoneTillNow, matchId, s1, s2, overInfo, totalRuns, wikFallenTillNow, teamOneName);
                        }
                        p2=buildBatsman(gameBuilderBatsman.getCurrBatsMan(),matchId,teamOneName);
                      //  updateGameBuilderBatsman(1);
                    }
                    processGameBuilderBatsmen(strike);
                    gameBuilderBatsman.setCurrBatsMan(gameBuilderBatsman.getCurrBatsMan()+1);
                }
                else{
                    freeHit=false;
                    overInfo.add(oversDoneTillNow+"."+ballsDoneInThisOver+" runs scored in this ball are "+runsScoredInThisBall);
                    totalRuns+=runsScoredInThisBall;
                    processRunsScoredInThisBall(runsScoredInThisBall,strike);
                    if(runsScoredInThisBall%2==1){
                        strike^=1;
                    }
                }
                ballsDoneInThisOver++;
               if(ballsDoneInThisOver==6 || wikFallenTillNow==10 || totalRuns>=target){
                    oversDoneTillNow++;
                    ballsDoneInThisOver=0;
                    strike^=1;
                    Bowler b1;
                    if(bowlerValidity.containsKey(gameBuilderBowler.getBowlersIndex())){
                        b1=bowlerValidity.get(gameBuilderBowler.getBowlersIndex());
                    }
                    else{
                        b1=buildBowler(gameBuilderBowler.getBowlersIndex(),matchId,teamTwoName);
                        bowlerValidity.put(gameBuilderBowler.getBowlersIndex(),b1);
                    }
                    updateBatsmanAfterOver(p1,p2);
                    processBowler(b1);
                    String s1=p1.getName()+":"+gameBuilderBatsman.getRunsScoredByp1()+"("+gameBuilderBatsman.getBallsFacedByp1()+")";
                    String s2=p2.getName()+":"+gameBuilderBatsman.getRunsScoredByp2()+"("+gameBuilderBatsman.getBallsFacedByp2()+")";
                    buildOverInformation(oversDoneTillNow,matchId,s1,s2,overInfo,totalRuns,wikFallenTillNow,teamOneName);
                    overInfo.clear();
                    processGameBuilderBowler();
                }
            }
            String s1=p1.getName()+":"+gameBuilderBatsman.getRunsScoredByp1()+"("+gameBuilderBatsman.getBallsFacedByp1()+")";
            String s2=p2.getName()+":"+gameBuilderBatsman.getRunsScoredByp2()+"("+gameBuilderBatsman.getBallsFacedByp2()+")";
            processLiveScore(totalRuns,wikFallenTillNow,oversDoneTillNow,teamOneName,ballsDoneInThisOver,s1,s2);
        }

     return totalRuns;
    }

    @GetMapping("/getLiveScore")
    public LiveScore getLiveScore(){
        return liveScore;
    }
}
