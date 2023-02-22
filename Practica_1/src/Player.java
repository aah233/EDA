import java.util.ArrayList;
import java.util.List;

public class Player{

    private String playerName;
    private List<String> teams;
    private List<String> positions;
    private int score;
    public Player(String playerName, String team, String position,double fg,int pts){
        this.playerName = playerName;
        this.teams = new ArrayList<String>();
        this.teams.add(team);
        this.positions = new ArrayList<String>();
        this.positions.add(position);
        this.score = this.score_normal(fg,pts);
    }
    
    public String getPlayerName(){
        return this.playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public List<String> getTeams(){
        return this.teams;
    }
    public void setTeams(List<String> teams){
        this.teams = teams;
    }

    public List<String> getPositions(){
        return this.positions;
    }
    public void setPositions(List<String> positions){
        this.positions = positions;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int score_normal(double fg,int pts){
    return (int)(fg*pts)/100;
    }
   public int tratamiento_score(Player jugador){
        this.score = (this.score+this.score_normal(jugador.getScore(), jugador.getScore()))/2;
       return this.score; 
   }
   public  boolean equals(Player P){
    if(this.playerName.compareTo(P.playerName)==0) return true;
    return false;
   }
}