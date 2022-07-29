package src.main.models;

import java.util.HashMap;

public class Game {
    private HashMap<Team,Integer> scoreBoard;
    private static int gameCount;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINTS = 150;
    public Game(Team home,Team away){
        this.scoreBoard = new HashMap<Team,Integer>();
        this.scoreBoard.put(new Team(home), 0);
        this.scoreBoard.put(new Team(away), 0);
        gameCount++;
    }

    public Integer getScore(Team team){
        return scoreBoard.get(team);
    }

    public Integer setScore(Team team,Integer score){
        if(team == null){
            throw new IllegalArgumentException("Cannot add a null to the scoreboard");
        }
        return this.scoreBoard.put(team, score);
    }

    public static int getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public static int getSnitchPoints() {
        return SNITCH_POINTS;
    }

    public Team getTeam(String name){
        return new Team(scoreBoard.keySet().stream()
            .filter(key -> key.getHouse().equals(name))
            .findFirst()
            .orElse(null));
    }

    public static int getGameCount(){
        return gameCount;
    }

    public String getPlaceHolder(String play){
        return play.substring(play.indexOf("<")+1,play.indexOf(">"));
    }

    public String replacePlaceHolder(String play,String placeHolder,String player){
        return play.replace("<"+placeHolder+">", player);
    }

    public void quaffleScore(Team team){
        setScore(team, getScore(team)+QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team){
        setScore(team, getScore(team)+SNITCH_POINTS);
    }

    public int random(int range){
        return (int)(Math.random()*range);
    }

    public Team getRandomTeam(){
        Object[] teams = this.scoreBoard.keySet().toArray();
        return (Team)teams[random(teams.length)];
    }

    public String simulate(String play){
        String placeHolder = getPlaceHolder(play);
        Team team = getRandomTeam();
        String player = "";
        if(placeHolder.equals(Team.getPositionChaser())){
            quaffleScore(team);
            player = team.getChasers()[random(team.getChasers().length)];
        }else if(placeHolder.equals(Team.getPositionSeeker())){
            catchSnitch(team);
            player = team.getSeeker();
        }else if(placeHolder.equals(Team.getPositionKeeper())){
            player = team.getKeeper();
        }

        return replacePlaceHolder(play, placeHolder, player);
    }
}
